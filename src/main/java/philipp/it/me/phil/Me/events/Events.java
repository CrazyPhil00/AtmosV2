package philipp.it.me.phil.Me.events;

public class Events<T> {

    public boolean cancelled;
    public EventType type;
    public EventDirection direction;

    public EventDirection getDirection() {
        return direction;
    }

    public void setDirection(EventDirection direction) {
        this.direction = direction;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public boolean isPost() {
        if (type == null) return false;

        return type == EventType.POST;
    }

    public boolean isPre() {
        if (type == null) return false;

        return type == EventType.PRE;
    }


    public boolean isIncoming() {
        if (direction == null) return false;

        return direction == EventDirection.INCOMING;
    }

    public boolean isOutgoing() {
        if (direction == null) return false;

        return direction == EventDirection.OUTGOING;
    }

}
