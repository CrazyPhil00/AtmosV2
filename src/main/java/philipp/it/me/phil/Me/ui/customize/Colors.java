package philipp.it.me.phil.Me.ui.customize;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.config.Property;
import philipp.it.me.phil.Me.utils.ConfigHandler;

import java.awt.*;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class Colors {

    private static int indexf = ConfigHandler.getInt("Color", "Fps"), indexc = ConfigHandler.getInt("Color", "Coord"), indexm = ConfigHandler.getInt("Color", "Module"), indext = ConfigHandler.getInt("Color", "Tabgui");
    private static Color fpsColor, tabGuiColor, moduleColor, coordColor;

    public static ArrayList<Color> colors = new ArrayList<>();
    public static ArrayList<String> colorName = new ArrayList<>();


    public static void loadColors() {

        for (Property c : ConfigHandler.config.getCategory("textcolor").getOrderedValues()) {
            String[] rgbString = c.getString().split(",");

            int red = parseInt(rgbString[0]);
            int green = parseInt(rgbString[1]);
            int blue = parseInt(rgbString[2]);

            Color color = new Color(red, green,blue);
            colors.add(color);
            colorName.add(c.getName());

        }

        fpsColor = colors.get(indexf);
        tabGuiColor = colors.get(indext);
        coordColor = colors.get(indexc);
        moduleColor = colors.get(indexm);
    }

    public static void cycleColor(String module) {
        if (module.equalsIgnoreCase("tabgui")) {
            if (indext < colors.size() - 1) {
                indext++;

            } else indext = 0;
            tabGuiColor = colors.get(indext);


        }else if (module.equalsIgnoreCase("fps")) {
            if (indexf < colors.size() - 1) {
                indexf++;

            } else indexf = 0;
            fpsColor = colors.get(indexf);


        }else if (module.equalsIgnoreCase("coord")) {
            if (indexc < colors.size() - 1) {
                indexc++;

            } else indexc = 0;
            coordColor = colors.get(indexc);


        }else if (module.equalsIgnoreCase("module")) {
            if (indexm < colors.size() - 1) {
                indexm++;

            } else indexm = 0;
            moduleColor = colors.get(indexm);

        }
    }

    public static void saveColors() {
        ConfigHandler.writeConfig("Color", "Tabgui" , indext);
        ConfigHandler.writeConfig("Color", "Fps" , indexf);
        ConfigHandler.writeConfig("Color", "Coord" , indexc);
        ConfigHandler.writeConfig("Color", "Module" , indexm);

    }


    public static Color getFpsColor() {
        return fpsColor;
    }

    public static Color getCoordColor() {
        return coordColor;
    }

    public static Color getTabGuiColor() {
        return tabGuiColor;
    }

    public static Color getModuleColor() {
        return moduleColor;
    }
}
