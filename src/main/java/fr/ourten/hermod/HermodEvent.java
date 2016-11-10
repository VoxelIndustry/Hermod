package fr.ourten.hermod;

public class HermodEvent
{
    private final IEventEmitter source;

    public HermodEvent(final IEventEmitter source)
    {
        this.source = source;
    }

    public IEventEmitter getSource()
    {
        return this.source;
    }
}