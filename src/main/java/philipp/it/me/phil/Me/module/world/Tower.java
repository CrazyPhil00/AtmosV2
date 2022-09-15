package philipp.it.me.phil.Me.module.world;


import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import philipp.it.me.phil.Me.module.Category;
import philipp.it.me.phil.Me.module.Module;
import philipp.it.me.phil.Me.utils.LeftClick;

import javax.annotation.Nonnull;

public class Tower extends Module {
    public Tower() {
        super("Tower", "Build's up really fast", Category.WORLD, false, "Vanilla" , "Vanilla", "NCP", "Custom");
        this.setDelaySetting(true);
    }

    private ItemStack oldItem;

    @Override
    public void onEnable() {
        MinecraftForge.EVENT_BUS.register(this);
        oldItem = new ItemStack(Minecraft.getMinecraft().player.inventory.getCurrentItem().getItem());
    }

    @Override
    public void onDisable() {
        super.onDisable();
        Minecraft.getMinecraft().player.rotationPitch = 0;
        doPick(oldItem);
        MinecraftForge.EVENT_BUS.unregister(this);
    }

    int t = 0;




    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event) {
        EntityPlayer player = Minecraft.getMinecraft().player;
        if (t == 5) player.jump();
        if (t >= (getMode().equalsIgnoreCase("Vanilla") ? 10 : getMode().equalsIgnoreCase("NCP") ? 20 : getDelay())) {

            player.rotationPitch = 90;

            if (doPick(new ItemStack(Item.getItemById(3)))) LeftClick.rightclick();

            t = 0;
        }
        t ++;
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
