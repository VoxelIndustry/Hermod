package net.voxelindustry.hermod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EventQueue
{
    private final List<EventDispatcher> stack = new ArrayList<>();

    public EventQueue addDispatcher(final EventDispatcher dispatcher)
    {
        stack.add(dispatcher);
        return this;
    }

    public EventQueue addDispatcherFirst(final EventDispatcher dispatcher)
    {
        stack.add(0, dispatcher);
        return this;
    }

    public <T extends HermodEvent> HermodEvent dispatch(final EventType<T> type, final T event)
    {
        final HermodEvent filtered = stack.get(0).dispatchEvent(type, event, this);
        filtered.setFiltered();

        if (filtered.isConsumed())
            return filtered;

        Collections.reverse(stack);

        return stack.get(0).dispatchEvent(type, event, this);
    }

    public EventDispatcher next(final EventDispatcher current)
    {
        final int index = stack.indexOf(current);
        if (index < stack.size() - 1)
            return stack.get(index + 1);
        return null;
    }
}
