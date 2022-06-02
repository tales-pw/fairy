package pw.tales.fairy.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import pw.tales.fairy.Fairy;
import pw.tales.fairy.client.renderer.TileRpSignTESR;
import pw.tales.fairy.client.renderer.TileWatermillRenderer;
import pw.tales.fairy.server.ServerProxy;
import pw.tales.fairy.tile.TileRpSign;
import pw.tales.fairy.tile.TileWatermill;

import static net.minecraftforge.common.MinecraftForge.EVENT_BUS;

public class ClientProxy extends ServerProxy {
    @Override
    public void setup(FMLCommonSetupEvent event) {
        super.setup(event);
        ClientRegistry.bindTileEntitySpecialRenderer(TileRpSign.class, new TileRpSignTESR());
        ClientRegistry
                .bindTileEntitySpecialRenderer(TileWatermill.class, new TileWatermillRenderer());

        OBJLoader.INSTANCE.addDomain(Fairy.MOD_ID);

        this.setUpBlockColors();
    }

    public void setUpBlockColors() {
        Minecraft minecraft = Minecraft.getMinecraft();
        BlockColors blockColors = minecraft.getBlockColors();
        ItemColors itemColors = minecraft.getItemColors();
        EVENT_BUS.post(new SetUpColorsEvent(blockColors, itemColors));
    }

    public void setUpItemVariant(Item item, int meta, String variant) {
        ModelLoader.setCustomModelResourceLocation(item, meta,
                new ModelResourceLocation(item.getRegistryName(), variant));
    }
}
