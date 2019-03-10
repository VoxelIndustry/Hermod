package net.voxelindustry.hermod;

public abstract class HermodEvent
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

    public HermodEvent copy()
    {
        return this.copy(source);
    }

    public abstract HermodEvent copy(IEventEmitter source);
}