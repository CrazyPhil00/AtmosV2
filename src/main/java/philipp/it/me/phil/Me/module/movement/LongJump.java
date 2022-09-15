package philipp.it.me.phil.Me.module.movement;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;
import philipp.it.me.phil.Me.events.Events;
import philipp.it.me.phil.Me.module.Category;
import philipp.it.me.phil.Me.module.Module;

import java.awt.*;

public class LongJump extends Module {

    public LongJump() {
        super("LongJump" , "You can jump very height" , Category.MOVEMENT, false, "Vanilla" , "Vanilla", "NCP");
        this.setKey(Keyboard.KEY_Y);

    }

    public void onEnable() throws AWTException {
        if (this.getMode().equalsIgnoreCase("Vanilla")) VanillaMode();
        if (this.getMode().equalsIgnoreCase("NCP")) NCPMode();

    }

    public void onDisable() {

    }



    public void VanillaMode() throws AWTException {
        EntityPlayer player = Minecraft.getMinecraft().player;

        double x = player.getLookVec().x;
        double z = player.getLookVec().z;

        player.addVelocity(x,0.2,z);

        this.setToggled(false);
    }

    public void NCPMode() throws AWTException {
        EntityPlayer player = Minecraft.getMinecraft().player;


        double x = player.getLookVec().x / 8;
        double z = player.getLookVec().z / 8;

        player.addVelocity(x, 0, z);

        this.setToggled(false);
    }

}
