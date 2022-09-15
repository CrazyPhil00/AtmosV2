package philipp.it.me.phil.Me.module.movement;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.input.Keyboard;
import philipp.it.me.phil.Me.module.Category;
import philipp.it.me.phil.Me.module.Module;

public class FlySpeed extends Module {
    public FlySpeed() {
        super("FlySpeed" , "You can fly" , Category.MOVEMENT, false, "Test" , "Vanilla", "Test");
        this.setKey(Keyboard.KEY_1);
    }

    public void onEnable() {
        EntityPlayer player = Minecraft.getMinecraft().player;

        player.capabilities.setFlySpeed(1);
    }

    public void onDisable() {
        EntityPlayer player = Minecraft.getMinecraft().player;

        player.capabilities.setFlySpeed(0.5f);
    }
}
