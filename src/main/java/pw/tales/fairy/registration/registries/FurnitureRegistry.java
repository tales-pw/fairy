package pw.tales.fairy.registration.registries;

import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import pw.tales.fairy.block.*;
import pw.tales.fairy.block.scholar_table.BlockScholarTable;
import pw.tales.fairy.registration.events.FairyRegisterEvent;

import java.util.Arrays;
import java.util.List;

@Mod.EventBusSubscriber
public class FurnitureRegistry {
    public static List<IBlockFairy> blocks = Arrays.asList(
            new BlockTable().setIdentifier("table"),
            new BlockTable().setIdentifier("table_pine_planks"),
            new BlockTable().setIdentifier("table_gray_painted"),
            new BlockTable().setIdentifier("table_nailed_oak"),
            new BlockTable().setIdentifier("table_brown_painted"),
            new BlockTable().setIdentifier("table_jungle_wood"),
            new BlockCradle().setIdentifier("cradle"),
            new BlockSimpleDouble(Material.WOOD).setIdentifier("cabinet"),
            new BlockSimple(Material.WOOD).setIdentifier("bedside_table"),
            new BlockScholarTable().setIdentifier("scholar_table"),
            new BlockTarget().setIdentifier("target"),
            new BlockWithFire().setIdentifier("campfire_pot"),
            new BlockWithFire().setIdentifier("braizer"),
            new BlockWithFire().setIdentifier("braizer_big"),
            new BlockSimpleDouble(Material.GLASS).setIdentifier("mirror"),

            // Couches
            new BlockCouch().setIdentifier("couch_green"),
            new BlockCouch().setIdentifier("couch_black"),
            new BlockCouch().setIdentifier("couch_red"),
            new BlockCouch().setIdentifier("couch_cushion_green"),
            new BlockCouch().setIdentifier("couch_cushion_blue"),
            new BlockCouch().setIdentifier("couch_cushion_black"),

            // Doors
            new BlockMiddleDoor(Material.WOOD).setIdentifier("jungle_door"),
            new BlockDiagonalDoor(Material.WOOD).setIdentifier("wooden_door_diagonal"),
            new BlockLargeDoor(Material.WOOD, 4, 1.5).setIdentifier("large_gate")
    );

    @SubscribeEvent
    public static void addItems(FairyRegisterEvent event) {
        event.registerBlocks(blocks);
    }
}
