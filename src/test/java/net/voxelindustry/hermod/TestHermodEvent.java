package net.voxelindustry.hermod;

import org.junit.Assert;
import org.junit.Test;

public class TestHermodEvent
{
    @Test
    public void testInit()
    {
        final IEventEmitter expected = () -> null;

        final HermodEvent event = new HermodEvent(expected)
        {
            @Override
            public HermodEvent copy(final IEventEmitter source)
            {
                return this;
            }
        };

        final IEventEmitter actual = event.getSource();

        Assert.assertEquals("Source should be the same!", expected, actual);
    }
}
