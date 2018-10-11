package net.voxelindustry.hermod;

import java.util.ArrayList;
import java.util.List;

public class EventHandlerWrapper<T extends HermodEvent>
{
    private final List<EventHandler<? super T>> eventHandlers;

    public EventHandlerWrapper()
    {
        this.eventHandlers = new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    public void handle(final HermodEvent event)
    {
        final T internalEvent = (T) event;
        this.eventHandlers.forEach(handler -> handler.handle(internalEvent));
    }

    public void addHandler(final EventHandler<? super T> handler)
    {
        this.eventHandlers.add(handler);
    }

    public void removeHandler(final EventHandler<? super T> handler)
    {
        this.eventHandlers.remove(handler);
    }

    public boolean containsHandler(final EventHandler<? super T> handler)
    {
        return this.eventHandlers.contains(handler);
    }
}