package net.voxelindustry.hermod;

public abstract class HermodEvent
{
    public static final EventHandler<?> CONSUME_HANDLER = HermodEvent::consume;

    private       IEventEmitter source;
    private final IEventEmitter target;

    private boolean consumed;
    private boolean filtered;

    public HermodEvent(final IEventEmitter target)
    {
        this.target = target;
    }

    public void consume()
    {
        consumed = true;
    }

    public boolean isConsumed()
    {
        return consumed;
    }

    void setFiltered()
    {
        filtered = true;
    }

    public boolean isFiltered()
    {
        return filtered;
    }

    public IEventEmitter getTarget()
    {
        return target;
    }

    public IEventEmitter getSource()
    {
        return source;
    }

    public HermodEvent setSource(final IEventEmitter source)
    {
        this.source = source;
        return this;
    }

    public HermodEvent copy()
    {
        final HermodEvent copy = copy(source);
        if (isConsumed())
            copy.consume();

        return copy;
    }

    public abstract HermodEvent copy(IEventEmitter source);
}