package philipp.it.me.phil.Me.commands;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.StringUtils;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import philipp.it.me.phil.Me.utils.ConfigHandler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class TestCommand extends CommandBase {

    public static ArrayList<Color> colors = new ArrayList<>();
    public static ArrayList<String> colorName = new ArrayList<>();

    @Override
    public String getName() {
        return "test";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/test";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args[0].equalsIgnoreCase("color")) {
            for (Property c : ConfigHandler.config.getCategory("Color").getOrderedValues()) {
                String[] rgbString = c.getString().split(",");

                int red = parseInt(rgbString[0]);
                int green = parseInt(rgbString[1]);
                int blue = parseInt(rgbString[2]);

                Color color = new Color(red, green,blue);
                colors.add(color);
                colorName.add(c.getName());



            }

            //sender.sendMessage(new TextComponentString(colors.toString()));
        }else if (args[0].equalsIgnoreCase("komma")) {

            int[] komma = {ConfigHandler.getInt("Color", "RedR"), ConfigHandler.getInt("Color", "RedG"), ConfigHandler.getInt("Color", "RedB")};
            sender.sendMessage(new TextComponentString(Arrays.toString(komma)));
        }




    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

}
