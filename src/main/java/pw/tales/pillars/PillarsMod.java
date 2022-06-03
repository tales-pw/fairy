package pw.tales.pillars;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.relauncher.Side;
import pw.tales.pillars.pose_render.ModelPoseRenderer;

@Mod(PillarsMod.MOD_ID)
public class PillarsMod {
    public static final String MOD_ID = "pillars";

    @Mod.Instance(MOD_ID)
    public static PillarsMod INSTANCE;

    public ModelPoseRegistry registry;
    public ModelPoseRenderer renderer;

    public PillarsMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::initPoseRegistry);
    }

    public void initPoseRegistry(final FMLClientSetupEvent event) {
        Minecraft minecraft = event.getMinecraftSupplier().get();
        registry = new ModelPoseRegistry(minecraft.getResourceManager());
        renderer = new ModelPoseRenderer(registry);
    }
}
