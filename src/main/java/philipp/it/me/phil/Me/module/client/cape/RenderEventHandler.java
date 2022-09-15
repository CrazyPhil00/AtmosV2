/**
 * DeveloperCapes by Jadar
 * License: MIT License
 * (https://raw.github.com/jadar/DeveloperCapes/master/LICENSE)
 * version 4.0.0.x
 */
package philipp.it.me.phil.Me.module.client.cape;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import philipp.it.me.phil.Me.module.client.cape.cape.ICape;
import philipp.it.me.phil.Me.module.client.cape.user.User;
import philipp.it.me.phil.Me.module.client.cape.user.UserManager;

/**
 * This is not the class you are looking for.
 * 
 * @author jadar
 */
public class RenderEventHandler {

    @SubscribeEvent
    public void renderPlayer(RenderPlayerEvent.Specials.Pre event) {
        AbstractClientPlayer player = (AbstractClientPlayer) event.getEntityPlayer();

        UserManager manager = UserManager.getInstance();
        User user = manager.getUser(player.getUniqueID().toString());
        if (user == null) return;

        ICape cape = user.capes.get(0);
        if (cape == null) return;

        boolean flag = cape.isTextureLoaded(player);
        if (!flag) {
            cape.loadTexture(player);
        }
    }
}