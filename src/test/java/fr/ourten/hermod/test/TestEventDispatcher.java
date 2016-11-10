package fr.ourten.hermod.test;

import fr.ourten.hermod.EventDispatcher;
import fr.ourten.hermod.EventHandler;
import fr.ourten.hermod.EventType;
import fr.ourten.hermod.HermodEvent;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestEventDispatcher
{
    EventDispatcher dispatcher;
    EventHandler<HermodEvent> handler1, handler2;
    boolean handle1, handle2;
    EventType<HermodEvent> typel;
    
    @Before
    public void setup()
    {
        dispatcher = new EventDispatcher();
        handle1 = false;
        handle2 = false;
        handler1 = (event) -> {
            handle1 = true;
        };
        handler2 = (event) -> {
            handle2 = true;
        };
        dispatcher.addHandler(EventType.ROOT, handler1);
        
        typel = new EventType<>("type1");
        dispatcher.addHandler(typel, handler2);
        
    }
    
    @Test
    public void dispatchEvent1()
    {
        boolean excepted1 = false;
        boolean excepted2 = true;
        
        boolean actual1 = handle1;
        
        dispatcher.dispatchEvent(EventType.ROOT, new HermodEvent(null));
        
        boolean actual2 = handle1;
        
        Assert.assertEquals("Shoud be equals", excepted1, actual1);
        Assert.assertEquals("Shoud be equals", excepted2, actual2);
    }
    
    @Test
    public void dispatchEvent2()
    {
        boolean excepted1 = false;
        boolean excepted2 = false;
        
        boolean actual1 = handle1;
        dispatcher.removeHandler(EventType.ROOT, handler1);
        dispatcher.dispatchEvent(EventType.ROOT, new HermodEvent(null));
        
        boolean actual2 = handle1;
        
        Assert.assertEquals("Shoud be equals", excepted1, actual1);
        Assert.assertEquals("Shoud be equals", excepted2, actual2);
    }
    
    @Test
    public void dispatchEvent3()
    {
        boolean excepted1a = false;
        boolean excepted1b = false;
        
        boolean excepted2a = true;
        boolean excepted2b = true;
        
        boolean actual1a = handle1;
        boolean actual1b = handle2;
        
        dispatcher.dispatchEvent(typel, new HermodEvent(null));
        
        boolean actual2a = handle1;
        boolean actual2b = handle2;
        
        Assert.assertEquals("Shoud be equals", excepted1a, actual1a);
        Assert.assertEquals("Shoud be equals", excepted1b, actual1b);
        Assert.assertEquals("Shoud be equals", excepted2a, actual2a);
        Assert.assertEquals("Shoud be equals", excepted2b, actual2b);
    }
    
}
