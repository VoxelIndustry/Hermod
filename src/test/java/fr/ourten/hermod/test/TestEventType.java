package fr.ourten.hermod.test;

import fr.ourten.hermod.EventType;
import fr.ourten.hermod.HermodEvent;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestEventType
{
    EventType<HermodEvent> type;
    
    @Before
    public void setup()
    {
        type = new EventType<>(EventType.ROOT, "test");
    }
    
    @Test
    public void testInit1()
    {
        String excepted1 = "test";
        EventType<HermodEvent> excepted2 = EventType.ROOT;
        
        String actual1 = type.getName();
        EventType<?> actual2 = type.getParent();
        
        Assert.assertEquals("name not correctly initialize", excepted1, actual1);
        Assert.assertEquals("parent not correctly initialize", excepted2, actual2);
    }
    
    @Test
    public void testInit2()
    {
        String excepted1 = "test";
        EventType<HermodEvent> excepted2 = EventType.ROOT;
        
        EventType<HermodEvent> typel = new EventType<>("test");
        
        String actual1 = typel.getName();
        EventType<?> actual2 = typel.getParent();
        
        Assert.assertEquals("name not correctly initialize", excepted1, actual1);
        Assert.assertEquals("parent not correctly initialize", excepted2, actual2);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testInit3()
    {
        new EventType<>(null, "test");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testInit4()
    {
        new EventType<>(null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testInit5()
    {
        new EventType<>("");
    }
    
    @Test
    public void testToString()
    {
        String rootString = "EventType [name=ROOT, parent=none]";
        String excepted = "EventType [name=test, parent=" + rootString + "]";
        
        String actual = type.toString();
        
        Assert.assertEquals("toString should be equals", excepted, actual);
    }
    
    @Test
    public void testEquals()
    {
        boolean excepted1 = true;
        boolean excepted2 = false;
        boolean excepted3 = true;
        boolean excepted4 = false;
        boolean excepted5 = false;
        boolean excepted6 = false;
        
        EventType<HermodEvent> type2 = new EventType<>(EventType.ROOT, "test");;
        EventType<HermodEvent> type3 = new EventType<>(EventType.ROOT, "test2");;
        
        boolean actual1 = type.equals(type);
        boolean actual2 = type.equals(null);
        boolean actual3 = type.equals(type2);
        boolean actual4 = type.equals("");
        boolean actual5 = type.equals(type3);
        boolean actual6 = type.equals(EventType.ROOT);
        
        Assert.assertEquals("Shoud be equals", excepted1, actual1);
        Assert.assertEquals("Shoud be equals", excepted2, actual2);
        Assert.assertEquals("Shoud be equals", excepted3, actual3);
        Assert.assertEquals("Shoud be equals", excepted4, actual4);
        Assert.assertEquals("Shoud be equals", excepted5, actual5);
        Assert.assertEquals("Shoud be equals", excepted6, actual6);
    }
    
    @Test
    public void testIsSubType()
    {
        boolean excepted1 = true;
        boolean excepted2 = true;
        boolean excepted3 = true;
        boolean excepted4 = false;
        boolean excepted5 = true;
        
        EventType<HermodEvent> type2 = new EventType<>(type, "test2");;
        EventType<HermodEvent> type3 = new EventType<>(type2, "test3");;
        
        boolean actual1 = type.isSubType(type);
        boolean actual2 = type.isSubType(EventType.ROOT);
        boolean actual3 = type2.isSubType(type);
        boolean actual4 = EventType.ROOT.isSubType(type);
        boolean actual5 = type3.isSubType(type);
        
        Assert.assertEquals("Shoud be equals", excepted1, actual1);
        Assert.assertEquals("Shoud be equals", excepted2, actual2);
        Assert.assertEquals("Shoud be equals", excepted3, actual3);
        Assert.assertEquals("Shoud be equals", excepted4, actual4);
        Assert.assertEquals("Shoud be equals", excepted5, actual5);
    }
    
    @Test
    public void testHashCode()
    {
        int excepted = type.hashCode();
        
        EventType<HermodEvent> type2 = new EventType<>(EventType.ROOT, "test");
        
        int actual = type2.hashCode();
        
        Assert.assertEquals("Shoud be equals", excepted, actual);
    }
}
