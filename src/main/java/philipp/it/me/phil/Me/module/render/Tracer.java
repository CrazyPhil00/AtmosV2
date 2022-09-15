package philipp.it.me.phil.Me.module.render;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import philipp.it.me.phil.Me.module.Category;
import philipp.it.me.phil.Me.module.Module;
import philipp.it.me.phil.Me.utils.RenderUtil;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Tracer extends Module {
    public Tracer() {
        super("Tracers" , "Draws Lines" , Category.RENDER, false, "Test" , "Vanilla", "Test");
        this.setKey(Keyboard.KEY_8);
    }

    private boolean showPlayer = true, showHostile = true, showInvisible = true, showAnimals = true, showItems = true;

    private final transient List<Entity> ENTITIES = new ArrayList<Entity>();
    private transient int BOX = 0;

    boolean isViewBobbing =  Minecraft.getMinecraft().gameSettings.viewBobbing;

    @Override
    public void onEnable() throws AWTException {
        Minecraft.getMinecraft().gameSettings.viewBobbing = false;
        BOX = GL11.glGenLists(1);
        GL11.glNewList(BOX, GL11.GL_COMPILE);
        RenderUtil.drawOutlinedBox(new AxisAlignedBB(-0.5, 0, -0.5, 0.5, 1, 0.5));
        GL11.glEndList();
    }

    @Override
    public void onDisable() {
        if (isViewBobbing) {
            Minecraft.getMinecraft().gameSettings.viewBobbing = true;
        }
        super.onDisable();
        GL11.glDeleteLists(BOX, 1);
    }

    @Override
    public void onLocalPlayerUpdate() {

        showItems = isClickOptionsOn("Show Items");
        showPlayer = isClickOptionsOn("Show Player");
        showAnimals = isClickOptionsOn("Show Animals");
        showInvisible = isClickOptionsOn("Show Invisible");
        showHostile = isClickOptionsOn("Show Hostile");

        ENTITIES.clear();
        for (Entity entity : Minecraft.getMinecraft().world.loadedEntityList) {
            if (entity.isDead)
                continue;
            if (entity == Minecraft.getMinecraft().player)
                continue;
            if (entity.isInvisible() && showInvisible)
                continue;
            if (entity instanceof EntityPlayer && showPlayer)
                continue;
            if (entity instanceof EntityAnimal && showAnimals)
                continue;
            if (entity instanceof EntityMob && showHostile)
                continue;
            if (entity instanceof EntityItem && showItems)
                continue;

            ENTITIES.add(entity);
        }
    }

    @Override
    public void onRenderWorldLast(float partialTicks) {
        GL11.glPushAttrib(GL11.GL_ENABLE_BIT | GL11.GL_COLOR_BUFFER_BIT | GL11.GL_LINE_BIT | GL11.GL_CURRENT_BIT);

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glLineWidth(2);

        GL11.glPushMatrix();
        GL11.glTranslated(-Minecraft.getMinecraft().getRenderManager().viewerPosX,
                -Minecraft.getMinecraft().getRenderManager().viewerPosY,
                -Minecraft.getMinecraft().getRenderManager().viewerPosZ);

        RenderUtil.drawESPTracers(ENTITIES);

        GL11.glPopMatrix();

        GL11.glPopAttrib();
    }



}
