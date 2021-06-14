package net.voxelindustry.hermod;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestEventDispatcher
{
    EventDispatcher           dispatcher;
    EventHandler<HermodEvent> handler1, handler2;
    boolean handle1, handle2;
    EventType<HermodEvent> typel;

    @Before
    public void setup()
    {
        dispatcher = new EventDispatcher(null);
        handle1 = false;
        handle2 = false;
        handler1 = (event) ->
        {
            handle1 = true;
        };
        handler2 = (event) ->
        {
            handle2 = true;
        };
        dispatcher.addHandler(EventType.ROOT, handler1);

        typel = new EventType<>("type1");
        dispatcher.addHandler(typel, handler2);
    }

    @Test
    public void dispatchEvent1()
    {
        final boolean expected1 = false;
        final boolean expected2 = true;

        final boolean actual1 = handle1;

        final HermodEvent event = new HermodEvent(null)
        {
            @Override
            public HermodEvent copy(final IEventEmitter source)
            {
                return this;
            }
        };
        event.setFiltered();

        dispatcher.dispatchEvent(EventType.ROOT, event, new EventQueue().addDispatcher(dispatcher));

        final boolean actual2 = handle1;

        assertEquals("Should be equals", expected1, actual1);
        assertEquals("Should be equals", expected2, actual2);
    }

    @Test
    public void dispatchEvent2()
    {
        final boolean expected1 = false;
        final boolean expected2 = false;

        final boolean actual1 = handle1;
        dispatcher.removeHandler(EventType.ROOT, handler1);
        dispatcher.dispatchEvent(EventType.ROOT, new HermodEvent(null)
        {
            @Override
            public HermodEvent copy(final IEventEmitter source)
            {
                return this;
            }
        }, new EventQueue().addDispatcher(dispatcher));

        final boolean actual2 = handle1;

        assertEquals("Should be equals", expected1, actual1);
        assertEquals("Should be equals", expected2, actual2);
    }

    @Test
    public void dispatchEvent3()
    {
        final boolean expected1a = false;
        final boolean expected1b = false;

        final boolean expected2a = true;
        final boolean expected2b = true;

        final boolean actual1a = handle1;
        final boolean actual1b = handle2;

        final HermodEvent event = new HermodEvent(null)
        {
            @Override
            public HermodEvent copy(final IEventEmitter source)
            {
                return this;
            }
        };
        event.setFiltered();

        dispatcher.dispatchEvent(typel, event, new EventQueue().addDispatcher(dispatcher));

        final boolean actual2a = handle1;
        final boolean actual2b = handle2;

        assertEquals("Should be equals", expected1a, actual1a);
        assertEquals("Should be equals", expected1b, actual1b);
        assertEquals("Should be equals", expected2a, actual2a);
        assertEquals("Should be equals", expected2b, actual2b);
    }

}
