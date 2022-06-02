package pw.tales.fairy;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import pw.tales.fairy.client.ClientProxy;
import pw.tales.fairy.registration.FairyRegistration;
import pw.tales.fairy.server.ServerProxy;
import pw.tales.fairy.tile.TileRpSign;
import pw.tales.fairy.tile.TileWatermill;

@Mod("fairy")
public class Fairy {
    public static final String MOD_ID = "fairy";
    public static final String MOD_NAME = "fairy";
    public static final String VERSION = "dev";

    public static ServerProxy proxy = DistExecutor.safeRunForDist(
            () -> ClientProxy::new,
            () -> ServerProxy::new
    );

    public final FairyRegistration registration = FairyRegistration.getInstance();

    public Fairy() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent event) {
        proxy.setup(event);
        this.registration.init();

        GameRegistry.registerTileEntity(TileRpSign.class, new ResourceLocation(MOD_ID, "rp_sign"));
        GameRegistry.registerTileEntity(TileWatermill.class, new ResourceLocation(MOD_ID, "watermill"));
    }
}
