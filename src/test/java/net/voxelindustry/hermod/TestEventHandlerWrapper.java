package net.voxelindustry.hermod;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestEventHandlerWrapper
{
    EventHandlerWrapper<HermodEvent> wrapper;
    EventHandler<HermodEvent>        handler;
    boolean                          handle = false;

    @Before
    public void setup()
    {
        wrapper = new EventHandlerWrapper<>();
        handler = (event) ->
        {
            handle = true;
        };
        wrapper.addHandler(handler);
    }

    @Test
    public void testContains()
    {
        final boolean expected1 = true;
        final boolean expected2 = false;

        final boolean actual1 = wrapper.containsHandler(handler);

        Assert.assertEquals("Should be equals", expected1, actual1);

        wrapper.removeHandler(handler);

        final boolean actual2 = wrapper.containsHandler(handler);

        Assert.assertEquals("Should be equals", expected2, actual2);
    }

    @Test
    public void testhandle()
    {
        final boolean expected1 = false;
        final boolean expected2 = true;

        final boolean actual1 = handle;

        wrapper.handle(new HermodEvent(null)
        {
            @Override
            public HermodEvent copy(final IEventEmitter source)
            {
                return this;
            }
        });

        final boolean actual2 = handle;

        Assert.assertEquals("Should be equals", expected1, actual1);
        Assert.assertEquals("Should be equals", expected2, actual2);
    }
}
