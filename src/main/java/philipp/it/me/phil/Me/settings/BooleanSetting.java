package philipp.it.me.phil.Me.settings;

public class BooleanSetting extends Setting{

    public Boolean enabled;

    public BooleanSetting(String name , boolean enabled) {
        this.name = name;
        this.enabled = enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public void toggle() {
        enabled = !enabled;
    }
}
