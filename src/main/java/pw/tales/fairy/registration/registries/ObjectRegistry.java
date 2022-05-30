package pw.tales.fairy.registration.registries;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import pw.tales.fairy.Fairy;
import pw.tales.fairy.block.*;
import pw.tales.fairy.block.divine.*;
import pw.tales.fairy.client.SetUpColorsEvent;
import pw.tales.fairy.client.colors.MetaColorHandler;
import pw.tales.fairy.client.colors.WaterColorHandler;
import pw.tales.fairy.registration.events.FairyRegisterEvent;

import java.util.Arrays;
import java.util.List;

@Mod.EventBusSubscriber(modid = Fairy.MOD_ID)
public class ObjectRegistry {
    public static CreativeTabs FAIRY_TAB = new CreativeTabs(Fairy.MOD_ID) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Items.aktanos_symbol_large, 1);
        }
    };

    public static List<IBlockFairy> objects = Arrays.asList(
            (new BlockRPSign().setIdentifier("rp_sign")),
            (new BlockCannon().setIdentifier("cannon")),
            (new BlockCannonBarrel().setIdentifier("cannon_barrel")),
            (new BlockCannon().setIdentifier("cannon_carriage")),
            (new BlockSimple(Material.WOOD).setIdentifier("bread_board")),
            (new BlockSimple(Material.WOOD).setIdentifier("rolling_pin")),
            (new BlockSimple(Material.SPONGE).setIdentifier("cheese_cut_1")),
            (new BlockSimple(Material.SPONGE).setIdentifier("cheese_cut_2")),
            (new BlockSimple(Material.SPONGE).setIdentifier("cheese_cut_3")),
            (new BlockSimple(Material.SPONGE).setIdentifier("cheese_cut_4")),
            (new BlockSimple(Material.CAKE).setIdentifier("chicken_raw")),
            (new BlockSimple(Material.CAKE).setIdentifier("chicken_cooked")),
            (new BlockWindowBoards().setIdentifier("window_boards_1")),
            (new BlockWindowBoards().setIdentifier("window_boards_2")),
            (new BlockWindowBoards().setIdentifier("window_boards_3")),
            (new BlockVial().setIdentifier("vial")),
            (new BlockVialSmall().setIdentifier("vial_small")),
            (new BlockBottle().setIdentifier("bottle")),
            (new BlockBottle().setIdentifier("bottle_wine")),
            (new BlockShutters().setIdentifier("shutters")),
            (new BlockTaperCandle().setIdentifier("taper_candle")),
            (new BlockSamovar().setIdentifier("samovar")),
            (new BlockSamovarBoot().setIdentifier("samovar_boot")),
            (new BlockSimplest(Material.PLANTS).setIdentifier("grapes")),
            (new BlockLayers(Material.PLANTS).setIdentifier("grapes_layer")),
            (new BlockSkullCandle().setIdentifier("skull_candle")),
            (new BlockGlobe().setIdentifier("globe")),
            (new BlockSimple(Material.WOOD).setIdentifier("checker_board")),
            (new BlockSimple(Material.WOOD).setIdentifier("canoe")),

            // Watermill
            (new BlockWatermill().setIdentifier("watermill")),

            // Divine Symbols
            new BlockLargeAktanosSymbol().setIdentifier("aktanos_symbol_large"),
            new BlockAkantosSymbol().setIdentifier("akantos_symbol"),
            new BlockSolSymbol().setIdentifier("sol_symbol"),
            new BlockMotheyaSymbol().setIdentifier("motheya_symbol"),
            new BlockSorokaSymbol().setIdentifier("soroka_symbol"),

            // Statue
            (new BlockStatue().setIdentifier("angel")),
            (new BlockStatue().setIdentifier("ship_statue")),
            (new BlockSimpleDouble(Material.ROCK).setIdentifier("obelisk_1")),
            (new BlockSimpleDouble(Material.ROCK).setIdentifier("obelisk_2")),
            (new BlockSimpleDouble(Material.ROCK).setIdentifier("obelisk_damaged")),
            (new BlockSimple(Material.ROCK).setIdentifier("tombstone_1")),
            (new BlockSimple(Material.ROCK).setIdentifier("tombstone_2")),
            (new BlockSimple(Material.ROCK).setIdentifier("tombstone_damaged")),

            // Honey
            new BlockSimple(Material.WOOD).setIdentifier("honeycomb"),
            new BlockSimple(Material.WOOD).setIdentifier("honeycomb_diagonal"),
            new BlockSimple(Material.WOOD).setIdentifier("honeycomb_diagonal_3"),
            new BlockSimple(Material.WOOD).setIdentifier("honeycomb_pile_3"),
            new BlockSimple(Material.WOOD).setIdentifier("honeycomb_pile_5"),
            new BlockSimple(Material.WOOD).setIdentifier("honey_pot"),

            // Trash
            (new BlockFiller().setIdentifier("adminium")),
            (new BlockRiverHolder().setIdentifier("river_holder")),

            // Liquids
            (new BlockFlippedWater().setIdentifier("flipped_water"))
    );

    @SubscribeEvent
    public static void addItems(FairyRegisterEvent event) {
        event.registerBlocks(objects);
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void setUpColors(SetUpColorsEvent event) {
        BlockColors blockColors = event.getBlockColors();
        ItemColors itemColors = event.getItemColors();

        blockColors.registerBlockColorHandler(MetaColorHandler.DEFAULT, ObjectRegistry.Blocks.vial);
        itemColors.registerItemColorHandler(MetaColorHandler.DEFAULT, ObjectRegistry.Items.vial);
        blockColors.registerBlockColorHandler(MetaColorHandler.DEFAULT, ObjectRegistry.Blocks.vial_small);
        itemColors.registerItemColorHandler(MetaColorHandler.DEFAULT, ObjectRegistry.Items.vial_small);

        blockColors.registerBlockColorHandler(WaterColorHandler.INSTANCE, ObjectRegistry.Blocks.flipped_water);
    }

    @GameRegistry.ObjectHolder(Fairy.MOD_ID)
    public static class Blocks {
        public static final BlockVial vial = null;
        public static final BlockVial vial_small = null;
        public static final BlockFlippedWater flipped_water = null;
    }


    @GameRegistry.ObjectHolder(Fairy.MOD_ID)
    public static class Items {
        public static final ItemBlock vial = null;
        public static final ItemBlock vial_small = null;
        public static final ItemBlock aktanos_symbol_large = null;
    }
}
