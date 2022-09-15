package philipp.it.me.phil.Me.module.render;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Rotation;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;
import philipp.it.me.phil.Me.module.Category;
import philipp.it.me.phil.Me.module.Module;


public class Backlook extends Module {
    public Backlook() {
        super("Backlook" , "Backlook" , Category.RENDER, false, "Test" , "Vanilla", "Test");
        this.setKey(Keyboard.KEY_NUMPAD1);
    }

    @Override
    public void onEnable() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister(this);
    }


    @SubscribeEvent
    public void cameraEvent(EntityViewRenderEvent.CameraSetup event) {
        if (isToggled()) {
            if (Minecraft.getMinecraft().gameSettings.thirdPersonView != 1 || Minecraft.getMinecraft().gameSettings.thirdPersonView != 2) {

            }
        }
    }
}
