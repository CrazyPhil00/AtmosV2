package philipp.it.me.phil.Me.module.render;

import net.minecraftforge.common.MinecraftForge;
import philipp.it.me.phil.Me.module.Category;
import philipp.it.me.phil.Me.module.Module;

public class TabGUIToggle extends Module {

    public TabGUIToggle() {
        super("Gui", "Graphical interface", Category.RENDER, false, "Test" , "Vanilla", "Test");
    }

    @Override
    public void onEnable() {
        MinecraftForge.EVENT_BUS.register(new TabGUI());
    }

    @Override
    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister(new TabGUI());
    }
}
