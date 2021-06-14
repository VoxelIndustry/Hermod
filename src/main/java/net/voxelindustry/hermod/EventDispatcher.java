package net.voxelindustry.hermod;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class EventDispatcher
{
    private final Map<EventType<? extends HermodEvent>, EventHandlerWrapper<? extends HermodEvent>> handlers = new ConcurrentHashMap<>();
    private final Map<EventType<? extends HermodEvent>, EventHandlerWrapper<? extends HermodEvent>> filters  = new ConcurrentHashMap<>();

    private EventQueue singletonQueue;

    private final IEventEmitter source;

    public EventDispatcher(final IEventEmitter source)
    {
        this.source = source;
    }

    public <T extends HermodEvent> T dispatchEvent(final EventType<T> type, final T event, final EventQueue stack)
    {
        final Map<EventType<? extends HermodEvent>, EventHandlerWrapper<? extends HermodEvent>> handlersForContext = event.isFiltered() ? handlers : filters;

        event.setSource(source);

        for (final Entry<EventType<? extends HermodEvent>, EventHandlerWrapper<? extends HermodEvent>> entry : handlersForContext.entrySet())
        {
            final EventType<? extends HermodEvent> eventType = entry.getKey();
            final EventHandlerWrapper<? extends HermodEvent> handler = entry.getValue();
            if (type == eventType || type.isSubType(eventType))
                handler.handle(event);

            if (event.isConsumed())
                break;
        }

        if (!event.isConsumed())
        {
            final EventDispatcher next = stack.next(this);
            if (next != null)
                next.dispatchEvent(type, event, stack);
        }

        return event;
    }

    public <T extends HermodEvent> void addHandler(final EventType<T> type, final EventHandler<? super T> handler)
    {
        final EventHandlerWrapper<T> wrapperHandler = internalGetHandler(type);
        wrapperHandler.addHandler(handler);
    }

    @SuppressWarnings("unchecked")
    public <T extends HermodEvent> void removeHandler(final EventType<T> type, final EventHandler<? super T> handler)
    {
        final EventHandlerWrapper<T> wrapperHandler = (EventHandlerWrapper<T>) handlers.get(type);

        if (wrapperHandler != null)
            wrapperHandler.removeHandler(handler);
    }

    @SuppressWarnings("unchecked")
    private <T extends HermodEvent> EventHandlerWrapper<T> internalGetHandler(final EventType<T> type)
    {
        EventHandlerWrapper<T> wrapperHandler = (EventHandlerWrapper<T>) handlers.get(type);
        if (wrapperHandler == null)
        {
            wrapperHandler = new EventHandlerWrapper<>();
            handlers.put(type, wrapperHandler);
        }
        return wrapperHandler;
    }

    public <T extends HermodEvent> void addFilter(final EventType<T> type, final EventHandler<? super T> filter)
    {
        final EventHandlerWrapper<T> wrapperFilter = internalGetFilter(type);
        wrapperFilter.addHandler(filter);
    }

    @SuppressWarnings("unchecked")
    public <T extends HermodEvent> void removeFilter(final EventType<T> type, final EventHandler<? super T> filter)
    {
        final EventHandlerWrapper<T> wrapperFilter = (EventHandlerWrapper<T>) filters.get(type);

        if (wrapperFilter != null)
            wrapperFilter.removeHandler(filter);
    }

    @SuppressWarnings("unchecked")
    private <T extends HermodEvent> EventHandlerWrapper<T> internalGetFilter(final EventType<T> type)
    {
        EventHandlerWrapper<T> wrapperFilter = (EventHandlerWrapper<T>) filters.get(type);
        if (wrapperFilter == null)
        {
            wrapperFilter = new EventHandlerWrapper<>();
            filters.put(type, wrapperFilter);
        }
        return wrapperFilter;
    }

    public EventQueue singletonQueue()
    {
        if (singletonQueue == null)
            singletonQueue = new EventQueue().addDispatcher(this);

        return singletonQueue;
    }
}