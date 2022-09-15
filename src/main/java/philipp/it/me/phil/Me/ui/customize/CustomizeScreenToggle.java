package philipp.it.me.phil.Me.ui.customize;

import net.minecraft.client.Minecraft;
import philipp.it.me.phil.Me.module.Category;
import philipp.it.me.phil.Me.module.Module;

import java.awt.*;

public class CustomizeScreenToggle extends Module {
    public CustomizeScreenToggle() {
        super("Customize Screen", "Customize what you want", Category.CLIENT, false, "None", "None");
        this.setDelaySetting(false);
        this.setModeSetting(false);
        this.setRangeSetting(false);
        this.setShowSettings(false);
    }


    @Override
    public void onEnable() throws AWTException {

        Minecraft.getMinecraft().displayGuiScreen(new CustomizeScreen());
        setToggled(false);
    }
}
