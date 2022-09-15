package philipp.it.me.phil.Me.ui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import philipp.it.me.phil.Me.Mod;
import philipp.it.me.phil.Me.listener.EventRenderGUI;
import philipp.it.me.phil.Me.module.Module;
import philipp.it.me.phil.Me.module.ModuleManager;
import philipp.it.me.phil.Me.module.keystrokes.MouseButton;
import philipp.it.me.phil.Me.ui.customize.Colors;
import philipp.it.me.phil.Me.ui.customize.CustomizeScreen;

import java.awt.*;
import java.util.Collections;
import java.util.Comparator;

public class Hud extends Gui {

    private MouseButton mouseButton;
    static int X, Y, Z;
    public static int modulesPosX, modulesPosY;

    public static int FpsX = 5, FpsY = 565, CordX = 5, CordY = 583;
    public static int backgroundColor = 0x30000000;
    public static int posX, posY;

    public static class ModuleComparator implements Comparator<Module> {

        @Override
        public int compare(Module arg0, Module arg1) {
            if (Minecraft.getMinecraft().fontRenderer.getStringWidth(arg0.getName()) > Minecraft.getMinecraft().fontRenderer.getStringWidth(arg1.getName())) {
                return -1;
            }
            if (Minecraft.getMinecraft().fontRenderer.getStringWidth(arg0.getName()) > Minecraft.getMinecraft().fontRenderer.getStringWidth(arg1.getName())) {
                return 1;
            }
            return 0;
        }
    }


    private final ResourceLocation logo = new ResourceLocation("edition.png");
    private final ResourceLocation pack = new ResourceLocation("serverPack.zip");
    private final ResourceLocation logo2 = new ResourceLocation("yannlogo.png");

    @SubscribeEvent
    public void renderOverlay(RenderGameOverlayEvent event) {
        Collections.sort(ModuleManager.modules, new ModuleComparator());
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        FontRenderer fr = Minecraft.getMinecraft().fontRenderer;

        //client logo
        if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            /*try {
                mc.defaultResourcePack.getInputStream(pack);
            } catch (IOException e) {
                e.printStackTrace();
            }*/
            //mc.renderEngine.bindTexture(logo2);

            //drawScaledCustomSizeModalRect(10, 5 , 0 , 0 , 50 , 50 , 100 ,50 ,20 , 20);
        }

        //client name
        if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            fr.drawString("Atmos", 2, 1, 3612416);

        }


        //coords / fps
        if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {

            X = (int) Minecraft.getMinecraft().player.posX;
            Y = (int) Minecraft.getMinecraft().player.posY;
            Z = (int) Minecraft.getMinecraft().player.posZ;
            if (Minecraft.getMinecraft().isFullScreen()) {
                //cord
                Gui.drawRect(CordX - 2, CordY - 3, CordX + fr.getStringWidth("PosX : " + X + ", PosY : " + Y + ", PosZ : " + Z + "") + 2, CordY + 12, backgroundColor);
                fr.drawString((CustomizeScreen.isActive() ? "PosX : XXX, PosY : YYY, PosZ : ZZZ" : "PosX : " + X + ", PosY : " + Y + ", PosZ : " + Z + ""), CordX, CordY, Colors.getCoordColor().getRGB());

                //Fps
                Gui.drawRect(FpsX - 2, FpsY - 3, FpsX + fr.getStringWidth("FPS : " + Minecraft.getDebugFPS() + 2) , FpsY + 11, backgroundColor);
                fr.drawString("FPS : " + (CustomizeScreen.isActive() ? "XXX" : Minecraft.getDebugFPS()), FpsX, FpsY, Colors.getFpsColor().getRGB());
            }
        }

        //arraylist
        if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            posY = 2 + modulesPosY;
            final int[] counter = {1};
            if (CustomizeScreen.isActive()) {
                posX = sr.getScaledWidth() - fr.getStringWidth("MODULE NAME") - 2 + modulesPosX;
                fr.drawStringWithShadow("MODULE NAME", posX, posY, Colors.getModuleColor().getRGB());
            } else {

                for (Module mod : Mod.moduleManager.getModuleList()) {
                    if (!mod.getName().equalsIgnoreCase("TabGui") && mod.isToggled()) {

                        posX = sr.getScaledWidth() - fr.getStringWidth(mod.getName()) - 2 + modulesPosX;
                        fr.drawStringWithShadow(mod.getName(), posX, posY, Colors.getModuleColor().getRGB());

                        posY += fr.FONT_HEIGHT;
                        counter[0]++;
                    }
                }
            }


            Mod.onEvent(new EventRenderGUI());
        }

    }

    @SubscribeEvent
    public void renderGameOverlayEvent(RenderGameOverlayEvent event)
    {
        if(event.getType() == RenderGameOverlayEvent.ElementType.TEXT && Minecraft.getMinecraft().gameSettings.showDebugInfo)
        {
            EntityPlayer player = Minecraft.getMinecraft().player;
            FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
            Vec3d vec3 = player.getLookVec();
            fontRenderer.drawStringWithShadow("X:"+(Math.abs(vec3.x)<-0.0001?0:String.format("%.4f",vec3.x)), 4, 160, 0xFFFFFF);
            fontRenderer.drawStringWithShadow("Y:"+(Math.abs(vec3.y)<-0.0001?0:String.format("%.4f",vec3.y)), 4, 168, 0xFFFFFF);
            fontRenderer.drawStringWithShadow("Z:"+(Math.abs(vec3.z)<-0.0001?0:String.format("%.4f",vec3.z)), 4, 176, 0xFFFFFF);
            fontRenderer.drawStringWithShadow("yaw:"+player.rotationYaw, 4, 184, 0xFFFFFF);
            fontRenderer.drawStringWithShadow("pitch:"+player.rotationPitch, 4, 192, 0xFFFFFF);
        }
    }


    public static int rainbows(int delay) {
        double rainbowstate = Math.ceil(System.currentTimeMillis() + delay) / 20.0;
        rainbowstate %= 360;
        return Color.getHSBColor((float) (rainbowstate / 360.0f), 0.5f, 1f).getRGB();
    }


    public static String getCoordString() { return "PosX : XXX, PosY : YYY, PosZ : ZZZ"; }


    public static String getFpsString() { return "FPS : XXX"; }

}
