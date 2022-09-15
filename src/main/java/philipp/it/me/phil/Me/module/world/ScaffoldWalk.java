package philipp.it.me.phil.Me.module.world;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import philipp.it.me.phil.Me.module.Category;
import philipp.it.me.phil.Me.module.Module;

public class ScaffoldWalk extends Module {
    public ScaffoldWalk()
    {
        super("ScaffoldWalk" , "ScaffoldWalk" , Category.WORLD, false, "Test" , "Vanilla", "Test");
    }

    @Override
    public void onEnable() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister(this);
    }

    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event)
    {
    }
}
