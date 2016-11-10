package fr.ourten.hermod.test;

import fr.ourten.hermod.EventDispatcher;
import fr.ourten.hermod.HermodEvent;
import fr.ourten.hermod.IEventEmitter;

import org.junit.Assert;
import org.junit.Test;

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
