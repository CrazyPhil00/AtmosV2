package philipp.it.me.phil.Me.ui.clickgui;

import com.ibm.icu.util.ULocale;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import philipp.it.me.phil.Me.Mod;
import philipp.it.me.phil.Me.module.Category;
import philipp.it.me.phil.Me.module.Module;
import philipp.it.me.phil.Me.module.ModuleManager;

import java.awt.*;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

public class ClickGui extends GuiScreen {

    private int oldPosX, oldPosY;


    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        FontRenderer fr = mc.fontRenderer;
        try {
            Mod.getInstance().clickGui.setToggled(false);
        } catch (AWTException e) {
            e.printStackTrace();
        }
        for (Category c : Category.values()) {
            if (mouseX >= c.posX && mouseX <= c.posX + fr.getStringWidth(c.name) && mouseY >= c.posY && mouseY <= c.posY + 10) {
                if (c.mouseClicked) {
                    c.posX += mouseX - oldPosX;
                    c.posY += mouseY - oldPosY;
                }
            }
            fr.drawString(c.getName(), c.getPosX(), c.getPosY(), -1);
            render.draw_Rect(c.posX - 6,c.posY -6, c.posX + 90, c.posY + 14, 0x90000000);
            render.draw_Rect(c.posX - 6,c.posY -6, c.posX - 2, c.posY + 14, -1);
            int posX = c.posX, posY = c.posY + 25;
            if (c.showModules) {
                for (Module m : ModuleManager.modules) {
                    if (m.getCategory() == c) {
                        fr.drawStringWithShadow(m.getName(), posX, posY, -1);
                        render.draw_Rect(posX - 6,posY -6, posX + 90, posY + 14, 0x70000000);
                        render.draw_Rect(posX - 6,posY -6, posX - 2, posY + 14, m.isToggled() ? new Color(180, 43, 43).getRGB() : -1);
                        if (m.showSettings()) {
                            if (m.hasModeSetting()) {
                                //modes
                                render.draw_Rect(posX + 90, posY - 6, posX + 180, posY + 14, 0x70000000);
                                render.draw_Rect(posX + 176, posY - 6, posX + 180, posY + 14, -1);
                                fr.drawString(m.getMode(), posX + 96, posY, -1);
                            }
                            if (m.hasClickSettings()) {
                                int t = 0;
                                for (String s : m.getClickOptions()) {

                                    render.draw_Rect(posX + 266, (posY - 6) + t, posX + 270, (posY + 14) + t, m.isClickOptionsOn(s) ? new Color(180, 43, 43).getRGB() : -1);

                                    fr.drawString(s, posX + 186, posY + t, -1);
                                    render.draw_Rect(posX + 180, (posY - 6) + t, posX + 270, (posY + 14) + t, 0x70000000);
                                    t += 20;
                                }
                            }
                            if (m.isCustom()) {
                                if (m.hasDelaySetting()) {
                                    //delays
                                    render.draw_Rect(posX + 180, posY - 6, posX + 270, posY + 14, 0x70000000);
                                    render.draw_Rect(posX + 266, posY - 6, posX + 270, posY + 14, -1);
                                    fr.drawString(String.valueOf(m.getDelay()), posX + 186, posY, -1);
                                }
                                if (m.hasRangeSetting()) {
                                    render.draw_Rect(posX + 270, posY - 6, posX + 360, posY + 14, 0x70000000);
                                    render.draw_Rect(posX + 356, posY - 6, posX + 360, posY + 14, -1);
                                    fr.drawString(String.valueOf(m.getRange()), posX + 276, posY, -1);
                                }

                            }
                        }

                        posY += 20;

                    }
                }
            }
        }
        oldPosX = mouseX;
        oldPosY = mouseY;

    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (keyCode == 1) {
            this.mc.displayGuiScreen(null);

            for (Category c : Category.values()) {
                c.mouseClicked = false;
            }

            if (this.mc.currentScreen == null) {
                this.mc.setIngameFocus();
            }
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        FontRenderer fr = mc.fontRenderer;
        try {
            Mod.getInstance().clickGui.setToggled(false);
        } catch (AWTException e) {
            e.printStackTrace();
        }
        for (Category c : Category.values()) {
            if (mouseX >= c.posX && mouseX <= c.posX + fr.getStringWidth(c.name) && mouseY >= c.posY && mouseY <= c.posY + 20) {
                if (mouseButton == 0) {
                    c.mouseClicked = true;

                }else if (mouseButton == 1) {
                    c.showModules = !c.showModules;
                }
            }
            int posX = c.posX, posY = c.posY + 25;
            int settingPosX = c.posX + 90, settingPosY = c.posY + 25;
            int delayPosX = c.posX + 180, delayPosY = c.posY + 25;
            int rangePosX = c.posX + 270, rangePosY = c.posY + 25;
            for (Module m : ModuleManager.modules) {
                if (c.showModules) {
                    if (m.getCategory() == c) {

                        if (mouseX >= posX && mouseX <= posX + fr.getStringWidth(m.name) && mouseY >= posY && mouseY <= posY + 9) {
                            if (mouseButton == 0) {
                                try {
                                    m.toggle();
                                } catch (AWTException e) {
                                    e.printStackTrace();
                                }
                            } else if (mouseButton == 1) {
                                m.toggleShowSettings();
                            }

                        } else if (mouseX >= settingPosX && mouseX <= settingPosX + fr.getStringWidth(m.getMode()) && mouseY >= settingPosY && mouseY <= settingPosY + 9) {
                            if (m.hasModeSetting()) {
                                //mode settings
                                if (mouseButton == 0) {
                                    m.cycleMode();
                                }
                            }
                        }else if (m.hasClickSettings()) {
                                int t = 0;
                                for (String s : m.getClickOptions()) {
                                    if (mouseX >= posX + 180 && mouseX <= c.posX + 180 + fr.getStringWidth(String.valueOf(s)) && mouseY >= c.posY + 25 + t && mouseY <= c.posY + 34 + t) {
                                        if (mouseButton == 0) {
                                            m.toggleClickOption(s);
                                        }
                                    }
                                    t += 20;
                                }

                        } else if (mouseX >= delayPosX && mouseX <= delayPosX + fr.getStringWidth(String.valueOf(m.getDelay())) && mouseY >= delayPosY && mouseY <= delayPosY + 9) {
                            if (m.hasDelaySetting()) {
                                //delay settings
                                if (mouseButton == 0) {
                                    if (isShiftKeyDown()) m.setDelay(m.getDelay() + 5);
                                    //else if (isCtrlKeyDown()) m.setDelay(m.getDelay() + 1);
                                    else m.setDelay(m.getDelay() + 10);
                                }
                                if (mouseButton == 1) {
                                    if (isShiftKeyDown()) m.setDelay(m.getDelay() - 5);
                                    //else if (isCtrlKeyDown()) m.setDelay(m.getDelay() - 1);
                                    else m.setDelay(m.getDelay() - 10);
                                }
                            }
                        } else if (mouseX >= rangePosX && mouseX <= rangePosX + fr.getStringWidth(String.valueOf(m.getDelay())) && mouseY >= rangePosY && mouseY <= rangePosY + 9) {
                            if (m.hasRangeSetting()) {
                                //range settings
                                if (mouseButton == 0) {
                                    m.setRange(m.getRange() + 0.1);
                                }else if (mouseButton == 1) {
                                    m.setRange(m.getRange() - 0.1);
                                }
                            }
                        }
                        posY += 20;
                        settingPosY += 20;
                        delayPosY += 20;
                        rangePosY += 20;
                    }
                }
            }

        }
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        for (Category c : Category.values()) {
            c.mouseClicked = false;
        }
    }


    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    public static int rainbows(int delay) {
        double rainbowstate = Math.ceil(System.currentTimeMillis() + delay) /20.0;
        rainbowstate %= 360;
        return Color.getHSBColor((float) (rainbowstate /360.0f) , 0.5f , 1f).getRGB();
    }

    private static class render extends Gui {

        public static void draw_Rect(int left, int top, int right, int bottom, int color) {
            Gui.drawRect(left, top, right, bottom, color);
        }
    }

}
