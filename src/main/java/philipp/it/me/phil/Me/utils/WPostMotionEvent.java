package philipp.it.me.phil.Me.utils;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.eventhandler.Event;

public final class WPostMotionEvent extends Event
{
    private final EntityPlayerSP player;

    public WPostMotionEvent(EntityPlayerSP player)
    {
        this.player = player;
    }

    public EntityPlayerSP getPlayer()
    {
        return player;
    }
}
