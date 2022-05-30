package pw.tales.fairy;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import pw.tales.fairy.registration.FairyRegistration;
import pw.tales.fairy.server.ServerProxy;
import pw.tales.fairy.tile.TileRpSign;
import pw.tales.fairy.tile.TileWatermill;

@Mod(modid = Fairy.MOD_ID, name = Fairy.MOD_NAME, version = Fairy.VERSION, dependencies = "required-after:conquest; required-after:pillars;")
public class Fairy {
    public static final String MOD_ID = "fairy";
    public static final String MOD_NAME = "fairy";
    public static final String VERSION = "dev";

    @SidedProxy(clientSide = "pw.tales.fairy.client.ClientProxy", serverSide = "pw.tales.fairy.server.ServerProxy")
    public static ServerProxy proxy;

    public static FairyRegistration registration = FairyRegistration.getInstance();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Fairy.proxy.preInit(event);
        Fairy.registration.init();
        GameRegistry.registerTileEntity(TileRpSign.class, new ResourceLocation(MOD_ID, "rp_sign"));
        GameRegistry.registerTileEntity(TileWatermill.class, new ResourceLocation(MOD_ID, "watermill"));
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

}
