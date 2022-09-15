package philipp.it.me.phil.Me.utils;

import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraft.client.entity.EntityPlayerSP;

public final class WPreMotionEvent extends Event
{
    private final EntityPlayerSP player;

    public WPreMotionEvent(EntityPlayerSP player)
    {
        this.player = player;
    }

    public EntityPlayerSP getPlayer()
    {
        return player;
    }
}
