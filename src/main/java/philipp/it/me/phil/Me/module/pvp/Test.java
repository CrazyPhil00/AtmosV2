package philipp.it.me.phil.Me.module.pvp;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import org.lwjgl.input.Keyboard;
import philipp.it.me.phil.Me.module.Category;
import philipp.it.me.phil.Me.module.Module;

import javax.annotation.Nonnull;
import java.awt.*;

public class Test extends Module {
private long time;
private final double speed = 1;
private float oldPitch;
private int blockPlaced;

    public Test() {
        super("test", "test", Category.COMBAT, false, "Test", "Test");
        this.setKey(Keyboard.KEY_F10);
    }

    @Override
    public void onEnable() throws AWTException {
        oldPitch = mc.player.rotationPitch;
        time = System.currentTimeMillis();
        mc.player.rotationPitch = 80;
        mc.gameSettings.fovSetting = 240f;

    }

    @Override
    public void onLocalPlayerUpdate() {
        mc.player.rotationPitch = 80;
        doPick(new ItemStack(Item.getItemById(3)));
        if (System.currentTimeMillis() - time < 1000.0d / speed) return;
        mc.player.sendChatMessage(String.valueOf(mc.player.inventory.currentItem));



    }


    private boolean doPick(
            final @Nonnull ItemStack result ) {
        final EntityPlayer player = Minecraft.getMinecraft().player;
        for (int x = 0; x < 9; x++) {
            final ItemStack stack = player.inventory.getStackInSlot(x);
            if (stack != null && stack.isItemEqual(result) && ItemStack.areItemStackTagsEqual(stack, result)) {
                player.inventory.currentItem = x;
                return true;
            }
        }
        if (!player.capabilities.isCreativeMode) {
            return false;
        }
        int slot = player.inventory.getFirstEmptyStack();
        if (slot < 0 || slot >= 9) {
            slot = player.inventory.currentItem;
        }
        // update inventory..
        player.inventory.setInventorySlotContents(slot, result);
        player.inventory.currentItem = slot;
        // update server...
        final int j = player.inventoryContainer.inventorySlots.size() - 9 + player.inventory.currentItem;
        Minecraft.getMinecraft().playerController.sendSlotPacket(player.inventory.getStackInSlot(player.inventory.currentItem), j);
        return true;
    }
}
