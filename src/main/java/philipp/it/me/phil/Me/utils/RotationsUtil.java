package philipp.it.me.phil.Me.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class RotationsUtil {
    private static boolean fakeRotation;
    private static float serverYaw;
    private static float serverPitch;
    private static float realYaw;
    private static float realPitch;

    @SubscribeEvent
    public static void onPreMotion(WPreMotionEvent event)
    {

        EntityPlayer player = event.getPlayer();
        realYaw = player.rotationYaw;
        realPitch = player.rotationPitch;
        player.rotationYaw = serverYaw;
        player.rotationPitch = serverPitch;
    }

    @SubscribeEvent
    public static void onPostMotion(WPostMotionEvent event)
    {

        EntityPlayer player = event.getPlayer();
        player.rotationYaw = realYaw;
        player.rotationPitch = realPitch;
        fakeRotation = false;
    }

    public static Vec3d getEyesPos()
    {
        return new Vec3d(Minecraft.getMinecraft().player.posX,
                Minecraft.getMinecraft().player.posY + Minecraft.getMinecraft().player.getEyeHeight(),
                Minecraft.getMinecraft().player.posZ);
    }

    public static Vec3d getClientLookVec()
    {
        EntityPlayerSP player = Minecraft.getMinecraft().player;

        float f =
                MathHelper.cos(-player.rotationYaw * 0.017453292F - (float)Math.PI);
        float f1 =
                MathHelper.sin(-player.rotationYaw * 0.017453292F - (float)Math.PI);

        float f2 = -MathHelper.cos(-player.rotationPitch * 0.017453292F);
        float f3 = MathHelper.sin(-player.rotationPitch * 0.017453292F);

        return new Vec3d(f1 * f2, f3, f * f2);
    }

    private static float[] getNeededRotations(Vec3d vec)
    {
        Vec3d eyesPos = getEyesPos();

        double diffX = getX(vec) - getX(eyesPos);
        double diffY = getY(vec) - getY(eyesPos);
        double diffZ = getZ(vec) - getZ(eyesPos);

        double diffXZ = Math.sqrt(diffX * diffX + diffZ * diffZ);

        float yaw = (float)Math.toDegrees(Math.atan2(diffZ, diffX)) - 90F;
        float pitch = (float)-Math.toDegrees(Math.atan2(diffY, diffXZ));

        return new float[]{MathHelper.wrapDegrees(yaw),
                MathHelper.wrapDegrees(pitch)};
    }

    public static double getAngleToLookVec(Vec3d vec)
    {
        float[] needed = getNeededRotations(vec);

        EntityPlayerSP player = Minecraft.getMinecraft().player;
        float currentYaw = MathHelper.wrapDegrees(player.rotationYaw);
        float currentPitch = MathHelper.wrapDegrees(player.rotationPitch);

        float diffYaw = currentYaw - needed[0];
        float diffPitch = currentPitch - needed[1];

        return Math.sqrt(diffYaw * diffYaw + diffPitch * diffPitch);
    }



    public static boolean faceVectorPacket(Vec3d vec)
    {
        float[] rotations = getNeededRotations(vec);

        fakeRotation = true;
        serverYaw = rotations[0];
        serverPitch = rotations[1];

        return Math.abs(serverYaw - rotations[0]) < 1F;
    }

    public static void faceVectorForWalking(Vec3d vec)
    {
        float[] needed = getNeededRotations(vec);

        EntityPlayerSP player = Minecraft.getMinecraft().player;
        player.rotationYaw = needed[0];
        player.rotationPitch = 0;
    }


    public static double getX(Vec3d vec)
    {
        return vec.x;
    }

    public static double getY(Vec3d vec)
    {
        return vec.y;
    }

    public static double getZ(Vec3d vec)
    {
        return vec.z;
    }
}
