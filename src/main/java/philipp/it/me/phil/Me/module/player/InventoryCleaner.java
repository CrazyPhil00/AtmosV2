package philipp.it.me.phil.Me.module.player;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;
import philipp.it.me.phil.Me.module.Category;
import philipp.it.me.phil.Me.module.Module;

import java.awt.*;

public class InventoryCleaner extends Module {
    public InventoryCleaner() {
        super("InvCleaner" , "Clean's your Inventory", Category.PLAYER, false, "Test" , "Vanilla", "Test");
    }
    long time;
    @Override
    public void onEnable() throws AWTException {
       time = System.currentTimeMillis();
    }


    boolean invOpen = false;

    @Override
    public void onLocalPlayerUpdate() {
        if (System.currentTimeMillis() - time < 1000.0d) return;
            EntityPlayer player = Minecraft.getMinecraft().player;

        for (ItemStack s : player.inventory.mainInventory) {
            player.sendMessage(new TextComponentString(s.getDisplayName()));
            player.dropItem(s, false);
            player.dropItem(s, true);

        }
    }




}
