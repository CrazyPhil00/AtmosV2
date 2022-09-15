package philipp.it.me.phil.Me.utils;

import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import philipp.it.me.phil.Me.module.Module;
import philipp.it.me.phil.Me.module.ModuleManager;

public class RenderWorldLastHandler {
    @SubscribeEvent
    public void onRenderWorldLast(RenderWorldLastEvent event) {
        for (Module mod : ModuleManager.modules) {
            if (mod.isToggled()) {
                mod.onRenderWorldLast(event.getPartialTicks());
            }
        }
    }

}
