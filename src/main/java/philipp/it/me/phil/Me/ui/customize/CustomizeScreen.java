package philipp.it.me.phil.Me.ui.customize;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import philipp.it.me.phil.Me.module.render.TabGUI;
import philipp.it.me.phil.Me.ui.Hud;
import philipp.it.me.phil.Me.utils.ConfigHandler;

import java.io.IOException;

public class CustomizeScreen extends GuiScreen {



    int oldPosX,oldPosY;

    static boolean mouseClicked, active;
    FontRenderer fr = Minecraft.getMinecraft().fontRenderer;


    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        active = true;

        if (mouseX >= Hud.CordX && mouseX <= Hud.CordX + fr.getStringWidth(Hud.getCoordString()) && mouseY >= Hud.CordY && mouseY <= Hud.CordY + 10) {
            if (mouseClicked) {
                Hud.CordX += mouseX - oldPosX;
                Hud.CordY += mouseY - oldPosY;

                ConfigHandler.writeConfig("Coord", "posX", Hud.CordX);
                ConfigHandler.writeConfig("Coord", "posY", Hud.CordY);
            }
        }

        if (mouseX >= Hud.FpsX && mouseX <= Hud.FpsX + fr.getStringWidth(Hud.getFpsString()) && mouseY >= Hud.FpsY && mouseY <= Hud.FpsY + 10) {
            if (mouseClicked) {
                Hud.FpsX += mouseX - oldPosX;
                Hud.FpsY += mouseY - oldPosY;

                ConfigHandler.writeConfig("Fps", "posX", Hud.FpsX);
                ConfigHandler.writeConfig("Fps", "posY", Hud.FpsY);
            }
        }

        if (mouseX >= Hud.posX && mouseX <= Hud.posX + fr.getStringWidth("MODULE NAME") && mouseY >= Hud.posY && mouseY <= Hud.posY + 10) {
            if (mouseClicked) {
                Hud.modulesPosX += mouseX - oldPosX;
                Hud.modulesPosY += mouseY - oldPosY;

                ConfigHandler.writeConfig("Modules", "posX", Hud.modulesPosX);
                ConfigHandler.writeConfig("Modules", "posY", Hud.modulesPosY);
            }
        }

        if (mouseX >= TabGUI.guiPosX && mouseX <= TabGUI.guiPosX + fr.getStringWidth("TAB GUI") && mouseY >= TabGUI.guiPosY && mouseY <= TabGUI.guiPosY + 10) {
            if (mouseClicked) {
                TabGUI.guiPosX += mouseX - oldPosX;
                TabGUI.guiPosY += mouseY - oldPosY;

                ConfigHandler.writeConfig("TabGui", "posX", TabGUI.guiPosX);
                ConfigHandler.writeConfig("TabGui", "posY", TabGUI.guiPosY);
            }
        }



            this.oldPosX = mouseX;
        this.oldPosY = mouseY;
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        if (mouseX >= Hud.CordX && mouseX <= Hud.CordX + fr.getStringWidth(Hud.getCoordString()) && mouseY >= Hud.CordY && mouseY <= Hud.CordY + 10) {
            if (mouseButton == 0) {
                mouseClicked = true;
            }else if (mouseButton == 1) {
                Colors.cycleColor("coord");

            }
        }
        if (mouseX >= Hud.FpsX && mouseX <= Hud.FpsX + fr.getStringWidth(Hud.getFpsString()) && mouseY >= Hud.FpsY && mouseY <= Hud.FpsY + 10) {
            if (mouseButton == 0) {
                mouseClicked = true;
            }else if (mouseButton == 1) {
                Colors.cycleColor("fps");

            }
        }

        if (mouseX >= Hud.posX && mouseX <= Hud.posX + fr.getStringWidth("MODULE NAME") && mouseY >= Hud.posY && mouseY <= Hud.posY + 10) {
            if (mouseButton == 0) {
                mouseClicked = true;
            }else if (mouseButton == 1) {
                Colors.cycleColor("module");

            }
        }

        if (mouseX >= TabGUI.guiPosX && mouseX <= TabGUI.guiPosX + fr.getStringWidth("TAB GUI") && mouseY >= TabGUI.guiPosY && mouseY <= TabGUI.guiPosY + 10) {
            if (mouseButton == 0) {
                mouseClicked = true;
            }else if (mouseButton == 1) {
                Colors.cycleColor("tabgui");

            }
        }

    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        mouseClicked = false;
        Colors.saveColors();

    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (keyCode == 1) {
            this.mc.displayGuiScreen(null);


            mouseClicked = false;

            if (this.mc.currentScreen == null) {
                this.mc.setIngameFocus();
            }
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
        active = false;
    }

    public static boolean isActive() {
        return active;
    }
}
