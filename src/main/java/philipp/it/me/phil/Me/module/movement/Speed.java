package philipp.it.me.phil.Me.module.movement;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;
import philipp.it.me.phil.Me.module.Category;
import philipp.it.me.phil.Me.module.Module;

import scala.collection.parallel.ParIterableLike;

import java.util.Timer;
import java.util.TimerTask;

public class Speed extends Module {
    public Speed() {
        super("Speed" , "You can run faster" , Category.MOVEMENT, false, "Test" , "Vanilla", "Test");
        this.setKey(Keyboard.KEY_LCONTROL);
    }

    public Timer timer = new Timer();

    @Override
    public void onEnable() {
        mc.player.capabilities.setPlayerWalkSpeed(4);

    }

    @Override
    public void onDisable() {

    }


}
