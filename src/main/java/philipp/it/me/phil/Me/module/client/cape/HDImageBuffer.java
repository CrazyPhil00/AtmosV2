/**
 * DeveloperCapes by Jadar
 * License: MIT License
 * (https://raw.github.com/jadar/DeveloperCapes/master/LICENSE)
 * version 3.3.0.0
 */
package philipp.it.me.phil.Me.module.client.cape;


import net.minecraft.client.renderer.IImageBuffer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * This class is an implementation of {@link IImageBuffer} that allows capes to be in HD
 *
 * @author Jadar
 */
@SideOnly(Side.CLIENT)
public class HDImageBuffer implements IImageBuffer {
    @Override
    public BufferedImage parseUserSkin(final BufferedImage texture) {
        if (texture == null)
            return null;
        int imageWidth = texture.getWidth(null) <= 64 ? 64 : texture.getWidth(null);
        int imageHeight = texture.getHeight(null) <= 32 ? 32 : texture.getHeight(null);

        BufferedImage capeImage = new BufferedImage(imageWidth, imageHeight, 2);

        Graphics graphics = capeImage.getGraphics();
        graphics.drawImage(texture, 0, 0, null);
        graphics.dispose();

        return capeImage;
    }

    @Override
    public void skinAvailable() {

    }

    public void func_152634_a() {}
}