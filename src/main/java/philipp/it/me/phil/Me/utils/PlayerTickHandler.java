package philipp.it.me.phil.Me.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import philipp.it.me.phil.Me.module.Module;
import philipp.it.me.phil.Me.module.ModuleManager;

public class PlayerTickHandler {
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.START)
            return;

        EntityPlayer player = event.player;
        if (player == Minecraft.getMinecraft().player) {
            for (Module mod : ModuleManager.modules) {
                if (mod.isToggled()) {
                    mod.onLocalPlayerUpdate();
                }
            }
        }
    }
}