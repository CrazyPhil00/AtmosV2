package philipp.it.me.phil.Me.module.movement;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.Sound;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;
import philipp.it.me.phil.Me.module.Category;
import philipp.it.me.phil.Me.module.Module;

public class Sprint extends Module {



    private static final int SPRINT_KEY = Minecraft.getMinecraft().gameSettings.keyBindSprint.getKeyCode();


    public Sprint() {
        super("Sprint" , "auto runs" , Category.MOVEMENT, false, "Test" , "Vanilla", "Test");
        this.setKey(Keyboard.KEY_M);

    }


    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event) {
        if (this.isToggled()) {
            KeyBinding.setKeyBindState(SPRINT_KEY, true);
        }
    }
}
