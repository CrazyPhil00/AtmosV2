package philipp.it.me.phil.Me.module.player;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;
import philipp.it.me.phil.Me.module.Category;
import philipp.it.me.phil.Me.module.Module;

public class FollowEntity extends Module {
    public FollowEntity() {
        super("FollowEntity" , "test" , Category.EXPLOITS, false, "Test" , "Vanilla", "Test");
        this.setKey(Keyboard.KEY_4);
    }



    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event) {
        if (this.isToggled()) {

            EntityPlayer player = Minecraft.getMinecraft().player;


            for (Entity entity : Minecraft.getMinecraft().world.getLoadedEntityList()) {
                if (entity != player) {
                    if (entity.getDistance(player) < 5) {


                        float X = (float) (entity.posX);
                        float Y = (float) (entity.posY);
                        float Z = (float) (entity.posZ);


                        //player.moveRelative(X, Z, -Y, 1);
                        player.move(MoverType.PLAYER, X, Y, Z);

                    }
                }
            }
        }
    }
}
