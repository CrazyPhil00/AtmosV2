package philipp.it.me.phil.Me.module.pvp;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;
import philipp.it.me.phil.Me.module.Category;
import philipp.it.me.phil.Me.module.Module;
import philipp.it.me.phil.Me.utils.LeftClick;

public class AutoClicker extends Module {
    public AutoClicker() {
        super("Autoclicker" , "Autoclicker" , Category.COMBAT, false, "Normal" , "Normal", "Hold down");
        this.setKey(Keyboard.KEY_3);
    }

    public LeftClick Autoclick = new LeftClick();

    int t = 0;

    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event) {
        if (this.isToggled()) {
            if (this.getMode().equalsIgnoreCase("Normal")) {
                if (t >= this.delay) {
                    Autoclick.leftclick();

                    t = 0;
                }
                t++;
            }
        }
    }

    @SubscribeEvent
    public void onMouseEvent(MouseEvent event) {
        if (this.isToggled()) {
            if (this.getMode().equalsIgnoreCase("Hold down")) {
                if (t >= this.delay) {
                    if (event.getButton() != 0) return;
                    EntityPlayerSP player = Minecraft.getMinecraft().player;
                    if (event.isButtonstate()) {
                        // LMB down
                        Autoclick.leftclick();
                    }
                    t = 0;
                }
            }
            t ++;
        }
    }
}
