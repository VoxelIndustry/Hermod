package net.voxelindustry.hermod;

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
        final String expected1 = "test";
        final EventType<HermodEvent> expected2 = EventType.ROOT;

        final String actual1 = type.getName();
        final EventType<?> actual2 = type.getParent();

        Assert.assertEquals("name not correctly initialize", expected1, actual1);
        Assert.assertEquals("parent not correctly initialize", expected2, actual2);
    }

    @Test
    public void testInit2()
    {
        final String expected1 = "test";
        final EventType<HermodEvent> expected2 = EventType.ROOT;

        final EventType<HermodEvent> typel = new EventType<>("test");

        final String actual1 = typel.getName();
        final EventType<?> actual2 = typel.getParent();

        Assert.assertEquals("name not correctly initialize", expected1, actual1);
        Assert.assertEquals("parent not correctly initialize", expected2, actual2);
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
        final String rootString = "EventType [name=ROOT, parent=none]";
        final String expected = "EventType [name=test, parent=" + rootString + "]";

        final String actual = type.toString();

        Assert.assertEquals("toString should be equals", expected, actual);
    }

    @Test
    public void testEquals()
    {
        final boolean expected1 = true;
        final boolean expected2 = false;
        final boolean expected3 = true;
        final boolean expected4 = false;
        final boolean expected5 = false;
        final boolean expected6 = false;

        final EventType<HermodEvent> type2 = new EventType<>(EventType.ROOT, "test");
        final EventType<HermodEvent> type3 = new EventType<>(EventType.ROOT, "test2");

        final boolean actual1 = type.equals(type);
        final boolean actual2 = type.equals(null);
        final boolean actual3 = type.equals(type2);
        final boolean actual4 = type.equals("");
        final boolean actual5 = type.equals(type3);
        final boolean actual6 = type.equals(EventType.ROOT);

        Assert.assertEquals("Should be equals", expected1, actual1);
        Assert.assertEquals("Should be equals", expected2, actual2);
        Assert.assertEquals("Should be equals", expected3, actual3);
        Assert.assertEquals("Should be equals", expected4, actual4);
        Assert.assertEquals("Should be equals", expected5, actual5);
        Assert.assertEquals("Should be equals", expected6, actual6);
    }

    @Test
    public void testIsSubType()
    {
        final boolean expected1 = true;
        final boolean expected2 = true;
        final boolean expected3 = true;
        final boolean expected4 = false;
        final boolean expected5 = true;

        final EventType<HermodEvent> type2 = new EventType<>(type, "test2");
        final EventType<HermodEvent> type3 = new EventType<>(type2, "test3");

        final boolean actual1 = type.isSubType(type);
        final boolean actual2 = type.isSubType(EventType.ROOT);
        final boolean actual3 = type2.isSubType(type);
        final boolean actual4 = EventType.ROOT.isSubType(type);
        final boolean actual5 = type3.isSubType(type);

        Assert.assertEquals("Should be equals", expected1, actual1);
        Assert.assertEquals("Should be equals", expected2, actual2);
        Assert.assertEquals("Should be equals", expected3, actual3);
        Assert.assertEquals("Should be equals", expected4, actual4);
        Assert.assertEquals("Should be equals", expected5, actual5);
    }

    @Test
    public void testHashCode()
    {
        final int expected = type.hashCode();

        final EventType<HermodEvent> type2 = new EventType<>(EventType.ROOT, "test");

        final int actual = type2.hashCode();

        Assert.assertEquals("Should be equals", expected, actual);
    }
}
