package philipp.it.me.phil.Me.module.render;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import org.lwjgl.opengl.GL11;
import philipp.it.me.phil.Me.module.Category;
import philipp.it.me.phil.Me.module.Module;
import philipp.it.me.phil.Me.utils.RenderUtil;

import java.util.ArrayList;
import java.util.List;

public class Esp extends Module {
    public Esp() {
        super("ESP", "You can see player", Category.RENDER, false, "OutlinedBox", "OutlinedBox", "SolidBox");
        setHasClickSettings();
        addClickOption("Show Player", true);
        addClickOption("Show Hostile", true);
        addClickOption("Show Animals", true);
        addClickOption("Show Items", true);
        addClickOption("Show Invisible", true);
    }

    private boolean showPlayer = true, showHostile = true, showInvisible = true, showAnimals = true, showItems = true;

    private final transient List<Entity> ENTITIES = new ArrayList<Entity>();
    private transient int BOX = 0;


    @Override
    public void onEnable() {
        BOX = GL11.glGenLists(1);
        GL11.glNewList(BOX, GL11.GL_COMPILE);
        if (this.getMode().equalsIgnoreCase("SolidBox")) RenderUtil.drawSolidBox(new AxisAlignedBB(-0.5, 0, -0.5, 0.5, 1, 0.5));
        else if (getMode().equalsIgnoreCase("OutlinedBox")) RenderUtil.drawOutlinedBox(new AxisAlignedBB(-0.5, 0, -0.5, 0.5, 1, 0.5));

        GL11.glEndList();
    }

    @Override
    public void onDisable() {
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

        RenderUtil.drawESPBoxes(ENTITIES, BOX, partialTicks);


        GL11.glPopMatrix();

        GL11.glPopAttrib();
    }
}
