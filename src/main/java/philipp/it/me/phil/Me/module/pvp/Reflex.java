package philipp.it.me.phil.Me.module.pvp;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import philipp.it.me.phil.Me.module.Category;
import philipp.it.me.phil.Me.module.Module;
import scala.tools.nsc.symtab.SymbolLoaders;

import java.util.Timer;
import java.util.TimerTask;

public class Reflex extends Module {
    public Reflex() {
        super("Reflex", "Saves you in dangerous moments", Category.COMBAT, false, "Test" , "Vanilla", "Test");
        this.setKey(Keyboard.KEY_F12);


    }


    @Override
    public void onEnable() {
        System.out.println("test");
        //MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister(this);
    }

    int ticks = 0;
    @SubscribeEvent
    public void checkHealth(TickEvent event) {

        ticks ++;
        if (ticks >= 20) {
            ticks = 0;
            check();
        }
    }


    private void check() {
        EntityPlayer player = mc.player;
        if (player.getHealth() < 20) {
            Display.setTitle("DANGER!");
        }
    }

}
