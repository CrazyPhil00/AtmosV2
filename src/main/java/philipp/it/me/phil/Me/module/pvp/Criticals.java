package philipp.it.me.phil.Me.module.pvp;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;
import philipp.it.me.phil.Me.module.Category;
import philipp.it.me.phil.Me.module.Module;

public class Criticals extends Module {
    public Criticals() {
        super("Criticals" , "Criticals" , Category.COMBAT, false, "Test" , "Vanilla", "Test");
        this.setKey(Keyboard.KEY_5);
    }

    @Override
    public void onEnable() {
        MinecraftForge.EVENT_BUS.register(this);



    }

    @Override
    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister(this);
    }



    private void doMiniJump() {
        mc.player.jump();
        mc.player.onGround = false;
    }
}
