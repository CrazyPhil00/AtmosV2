package philipp.it.me.phil.Me.module.pvp;

import net.minecraft.entity.Entity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;
import philipp.it.me.phil.Me.module.Category;
import philipp.it.me.phil.Me.module.Module;

import java.awt.*;

public class Aimbot extends Module {
    public Aimbot() {
        super("Aimbot" , "Aimbot" , Category.COMBAT, false, "Test" , "Vanilla");
    }


    @Override
    public void onEnable() throws AWTException {
        super.onEnable();
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        MinecraftForge.EVENT_BUS.unregister(this);
    }


    @SubscribeEvent
    public void onTick(TickEvent.ServerTickEvent event) {
        if (this.isToggled()) {
            for(Entity entity : mc.world.getLoadedEntityList()) {
                if (entity != mc.player) {
                    //if (mc.player.getDistance(entity) <= 4.0f) {
                        //mc.player.setRotationYawHead(getRotations(entity));
                    //}
                }
            }
        }
    }


    private float yaw;
    private float pitch;


    public float getRotations(Entity e) {
        double  deltaX = mc.player.posX - e.posX,
                deltaY = e.posY - 3.5 + e.getEyeHeight() - mc.player.posY + mc.player.getEyeHeight(),
                deltaZ = e.posZ + (e.posZ - e.lastTickPosZ) - mc.player.posZ,
                distance = Math.sqrt(Math.pow(deltaX , 2) + Math.pow(deltaZ , 2));

        float yaw = (float) Math.toDegrees(-Math.atan(deltaX / deltaZ)),
                pitch = (float) -Math.toDegrees(Math.atan(deltaY / distance));
        if (deltaX < 0 && deltaZ < 0) {
            yaw = (float) (90 + Math.toDegrees(Math.atan(deltaZ - deltaX)));
        }else if (deltaX > 0 && deltaZ > 0) {
            yaw = (float) (-90 + Math.toDegrees(Math.atan(deltaZ - deltaX)));
        }

        return yaw ;
    }
}
