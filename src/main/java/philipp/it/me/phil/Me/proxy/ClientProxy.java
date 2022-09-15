package philipp.it.me.phil.Me.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import philipp.it.me.phil.Me.ui.Hud;

public class ClientProxy extends CommonProxy {

    @SideOnly(Side.CLIENT)
    public void registerRenderers() {

    }
}
