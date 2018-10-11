package net.voxelindustry.hermod.test;

import org.junit.Assert;
import org.junit.Test;
import net.voxelindustry.hermod.EventDispatcher;
import net.voxelindustry.hermod.HermodEvent;
import net.voxelindustry.hermod.IEventEmitter;

public class TestHermodEvent
{
    @Test
    public void testInit()
    {
        IEventEmitter expected = new IEventEmitter()
        {
            
            @Override
            public EventDispatcher getEventDispatcher()
            {
                return null;
            }
        };
        
        HermodEvent event = new HermodEvent(expected);
        
        IEventEmitter actual = event.getSource();
        
        Assert.assertEquals("Source should be the same!", expected, actual);
    }
}
