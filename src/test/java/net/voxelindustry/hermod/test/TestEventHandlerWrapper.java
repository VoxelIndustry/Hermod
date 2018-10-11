package net.voxelindustry.hermod.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import net.voxelindustry.hermod.EventHandler;
import net.voxelindustry.hermod.EventHandlerWrapper;
import net.voxelindustry.hermod.HermodEvent;

public class TestEventHandlerWrapper
{
    EventHandlerWrapper<HermodEvent> wrapper;
    EventHandler<HermodEvent> handler;
    boolean handle = false;
    
    @Before
    public void setup()
    {
        wrapper = new EventHandlerWrapper<>();
        handler = (event) -> {
            handle = true;
        };
        wrapper.addHandler(handler);
    }
    
    @Test
    public void testContains()
    {
        boolean excepted1 = true;
        boolean excepted2 = false;
        
        boolean actual1 = wrapper.containsHandler(handler);
        
        Assert.assertEquals("Shoud be equals", excepted1, actual1);
        
        wrapper.removeHandler(handler);
        
        boolean actual2 = wrapper.containsHandler(handler);
        
        Assert.assertEquals("Shoud be equals", excepted2, actual2);
    }
    
    @Test
    public void testhandle()
    {
        boolean excepted1 = false;
        boolean excepted2 = true;
        
        boolean actual1 = handle;
        
        wrapper.handle(new HermodEvent(null));
        
        boolean actual2 = handle;
        
        Assert.assertEquals("Shoud be equals", excepted1, actual1);
        Assert.assertEquals("Shoud be equals", excepted2, actual2);
    }
}
