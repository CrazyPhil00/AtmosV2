package philipp.it.me.phil.Me.module.keystrokes.render;

import net.minecraft.client.*;
import net.minecraftforge.fml.common.gameevent.*;
import java.io.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.client.gui.*;
import philipp.it.me.phil.Me.Mod;
import philipp.it.me.phil.Me.module.keystrokes.Key;
import philipp.it.me.phil.Me.module.keystrokes.KeystrokesSettings;
import philipp.it.me.phil.Me.module.keystrokes.MouseButton;
import philipp.it.me.phil.Me.module.render.TabGUI;

import java.awt.*;
import java.util.logging.Logger;

public class KeystrokesRenderer {
    private static final int[] COLORS;
    private final Key[] movementKeys;
    private final MouseButton[] mouseButtons;
    private TabGUI tabGUI;

    public KeystrokesRenderer() {
        this.movementKeys = new Key[4];
        this.mouseButtons = new MouseButton[2];
        this.movementKeys[0] = new Key(Minecraft.getMinecraft().gameSettings.keyBindForward, 96, 2);
        this.movementKeys[1] = new Key(Minecraft.getMinecraft().gameSettings.keyBindBack, 96, 26);
        this.movementKeys[2] = new Key(Minecraft.getMinecraft().gameSettings.keyBindLeft, 72, 26);
        this.movementKeys[3] = new Key(Minecraft.getMinecraft().gameSettings.keyBindRight, 120, 26);
        this.mouseButtons[0] = new MouseButton(0, 72, 50);
        this.mouseButtons[1] = new MouseButton(1, 108, 50);
    }

    @SubscribeEvent
    public void onRenderTick(final TickEvent.RenderTickEvent event) throws IOException {
        if (Minecraft.getMinecraft().currentScreen != null) {
            if (Minecraft.getMinecraft().currentScreen instanceof GuiScreenKeystrokes) {
                Minecraft.getMinecraft().currentScreen.handleInput();
            }
            return;
        }
        if (!Minecraft.getMinecraft().inGameHasFocus || Minecraft.getMinecraft().gameSettings.showDebugInfo) {
            return;
        }

        if (!TabGUI.expanded) {
            this.renderKeystrokes();
        }

    }
    
    public void renderKeystrokes() {
        final KeystrokesSettings settings = Mod.getSettings();
        if (!settings.isEnabled()) {
            return;
        }
        int x = settings.getX();
        int y = settings.getY();
        final int textColor = this.getColor(settings.getTextColor());
        final boolean showingMouseButtons = settings.isShowingMouseButtons();
        final ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());
        final int width = 74;
        final int height = showingMouseButtons ? 74 : 50;
        if (x < 0) {
            settings.setX(0);
            x = settings.getX();
        }
        else if (x > res.getScaledWidth() - 74) {
            settings.setX(res.getScaledWidth() - 74);
            x = settings.getX();
        }
        if (y < 0) {
            settings.setY(0);
            y = settings.getY();
        }
        else if (y > res.getScaledHeight() - height) {
            settings.setY(res.getScaledHeight() - height);
            y = settings.getY();
        }
        this.drawMovementKeys(x, y, textColor);
        if (showingMouseButtons) {
            this.drawMouseButtons(x, y, textColor);
        }
    }
    
    private int getColor(final int index) {
        if (index == 6) {
            return Color.HSBtoRGB(System.currentTimeMillis() % 1000L / 1000.0f, 0.8f, 0.8f);
        }
        return KeystrokesRenderer.COLORS[index];
    }
    
    private void drawMovementKeys(final int x, final int y, final int textColor) {
        for (final Key key : this.movementKeys) {
            key.renderKey(x, y, textColor);
        }
    }
    
    private void drawMouseButtons(final int x, final int y, final int textColor) {
        for (final MouseButton button : this.mouseButtons) {
            button.renderMouseButton(x, y, textColor);
        }
    }
    
    static {
        COLORS = new int[] { 16777215, 16711680, 65280, 255, 16776960, 11141290 };
    }
}
