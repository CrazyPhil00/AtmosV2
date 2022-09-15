package philipp.it.me.phil.Me.module.render;

import org.lwjgl.input.Keyboard;
import philipp.it.me.phil.Me.events.Events;
import philipp.it.me.phil.Me.module.Category;
import philipp.it.me.phil.Me.module.Module;

public class Fullbright extends Module {
    public Fullbright() {
        super("Fullbright" , "Fullbright" , Category.RENDER, false, "Test" , "Vanilla", "Test");
        this.setKey(Keyboard.KEY_2);
    }

    @Override
    public void onEnable() {
        mc.gameSettings.gammaSetting = 100;
    }

    @Override
    public void onDisable() {
        mc.gameSettings.gammaSetting = 1;
    }
}
