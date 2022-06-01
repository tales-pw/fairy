package pw.tales.pillars;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;
import pw.tales.fairy.Fairy;
import pw.tales.pillars.pose_render.ModelPoseRenderer;

@Mod(modid = PillarsMod.MOD_ID, name = PillarsMod.MOD_NAME, version = PillarsMod.VERSION)
public class PillarsMod {

    public static final String MOD_ID = "pillars";
    public static final String MOD_NAME = "pillars";
    public static final String VERSION = "dev";

    @Mod.Instance(MOD_ID)
    public static PillarsMod INSTANCE;

    public ModelPoseRegistry registry;
    public ModelPoseRenderer renderer;

    public PillarsMod() {
        if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
            registry = new ModelPoseRegistry(Minecraft.getMinecraft().getResourceManager());
            renderer = new ModelPoseRenderer(registry);
        }
    }
}
