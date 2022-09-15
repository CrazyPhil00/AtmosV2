package philipp.it.me.phil.Me.module.movement;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.input.Keyboard;
import philipp.it.me.phil.Me.module.Category;
import philipp.it.me.phil.Me.module.Module;
import philipp.it.me.phil.Me.settings.ModeSetting;

import javax.swing.plaf.SpinnerUI;

public class Fly extends Module {

    Minecraft mc = Minecraft.getMinecraft();

    int speed = 2;
    public Fly() {
        super("Fly" , "You can fly" , Category.MOVEMENT, false, "Vanilla" , "Vanilla", "NCP");
        setModeSetting(true);
        this.setKey(Keyboard.KEY_F);
    }

    public void onEnable() {

    }

    public void onDisable() {
        super.onDisable();
        mc.player.capabilities.allowFlying = false;
        mc.player.capabilities.isFlying = false;

    }



    @Override
    public void onRenderWorldLast(float partialTicks) {

        if (!this.isToggled()) return;
        if (getMode().equalsIgnoreCase("NCP")) NcpFly();
        else if (getMode().equalsIgnoreCase("Vanilla")) vanillaFly();
    }

    private void vanillaFly() {
        mc.player.capabilities.allowFlying = true;
        mc.player.capabilities.isFlying = true;
    }

    private void NcpFly() {
        EntityPlayer player = Minecraft.getMinecraft().player;

        player.capabilities.isFlying = false;
        player.motionX = 0;
        player.motionY = 0;
        player.motionZ = 0;
        player.jumpMovementFactor = speed;

        if(Minecraft.getMinecraft().gameSettings.keyBindJump.isKeyDown())
            player.motionY += speed;
        if(Minecraft.getMinecraft().gameSettings.keyBindSneak.isKeyDown())
            player.motionY -= speed;
    }

}
