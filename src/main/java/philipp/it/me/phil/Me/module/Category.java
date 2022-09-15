package philipp.it.me.phil.Me.module;


import philipp.it.me.phil.Me.module.render.TabGUI;
import philipp.it.me.phil.Me.utils.ConfigHandler;

import java.awt.*;


public enum Category {
    COMBAT("Combat", ConfigHandler.getInt("ClickGui", "Combat-X"), ConfigHandler.getInt("ClickGui", "Combat-Y"), false, false, new Color(148, 9, 9).getRGB(), Modules.getCombatSection().size()),
    EXPLOITS("Exploits", ConfigHandler.getInt("ClickGui", "Exploits-X"), ConfigHandler.getInt("ClickGui", "Exploits-Y"), false, false, new Color(0, 255, 255).getRGB(), Modules.getExploitSection().size()),
    RENDER("Render", ConfigHandler.getInt("ClickGui", "Render-X"), ConfigHandler.getInt("ClickGui", "Render-Y"), false, false, new Color(156, 0, 173).getRGB(), Modules.getRenderSection().size()),
    PLAYER("Player", ConfigHandler.getInt("ClickGui", "Player-X"), ConfigHandler.getInt("ClickGui", "Player-Y"), false, false, new Color(0, 26, 255).getRGB(), Modules.getPlayerSection().size()),
    CLIENT("Client", ConfigHandler.getInt("ClickGui", "Client-X"), ConfigHandler.getInt("ClickGui", "Client-Y"), false, false, new Color(86, 94, 86).getRGB(), Modules.getClientSection().size()) ,
    MOVEMENT("Movement", ConfigHandler.getInt("ClickGui", "Movement-X"), ConfigHandler.getInt("ClickGui", "Movement-Y"), false, false, new Color(217, 168, 7).getRGB(), Modules.getMovementSection().size()) ,
    WORLD("World", ConfigHandler.getInt("ClickGui", "World-X"), ConfigHandler.getInt("ClickGui", "World-Y"), false,false, new Color(12, 122, 9).getRGB(), Modules.getWorldSection().size());

    public TabGUI tabGUI = new TabGUI();

    public String name;
    public int posX, posY;
    public boolean mouseClicked, showModules;
    int color, size;


    Category(String name, int posX, int posY, boolean mouseClicked, boolean showModules, int color, int size) {
        this.name = name;
        this.posY = posY;
        this.posX = posX;
        this.mouseClicked = mouseClicked;
        this.showModules = showModules;
        this.color = color;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getSize() {
        return size;
    }
}
