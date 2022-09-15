package philipp.it.me.phil.Me.listener;

import philipp.it.me.phil.Me.events.Events;

public class EventKey extends Events<EventKey> {

    public int code;

    public EventKey(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
