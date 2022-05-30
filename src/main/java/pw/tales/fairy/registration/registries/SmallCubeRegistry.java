package pw.tales.fairy.registration.registries;

import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import pw.tales.fairy.Fairy;
import pw.tales.fairy.block.BlockSmallCube;
import pw.tales.fairy.block.IBlockFairy;
import pw.tales.fairy.registration.events.FairyRegisterEvent;

import java.util.Arrays;
import java.util.List;

@Mod.EventBusSubscriber(modid = Fairy.MOD_ID)
public class SmallCubeRegistry {
    public static List<IBlockFairy> smallCubeBlocks = Arrays.asList(
            new BlockSmallCube(Material.WOOD).setIdentifier("small_chest"),
            new BlockSmallCube(Material.PLANTS).setIdentifier("small_pumpkin"),
            new BlockSmallCube(Material.ROCK).setIdentifier("small_marble"),
            new BlockSmallCube(Material.ROCK).setIdentifier("small_rough_marble"),
            new BlockSmallCube(Material.WOOD).setIdentifier("smallcube_roman_hardclay_purple"),
            new BlockSmallCube(Material.WOOD).setIdentifier("smallcube_clayyellowclean"),
            new BlockSmallCube(Material.WOOD).setIdentifier("smallcube_roman_hardclay_red"),
            new BlockSmallCube(Material.WOOD).setIdentifier("smallcube_claypurpleclean"),
            new BlockSmallCube(Material.WOOD).setIdentifier("smallcube_roman_hardclay_white"),
            new BlockSmallCube(Material.WOOD).setIdentifier("smallcube_roman_hardclay_magenta"),
            new BlockSmallCube(Material.WOOD).setIdentifier("smallcube_roman_hardclay_blue"),
            new BlockSmallCube(Material.WOOD).setIdentifier("smallcube_roman_hardclay_pink"),
            new BlockSmallCube(Material.WOOD).setIdentifier("smallcube_clayorangesavanna"),
            new BlockSmallCube(Material.WOOD).setIdentifier("smallcube_roman_hardclay_lime"),
            new BlockSmallCube(Material.WOOD).setIdentifier("smallcube_roman_hardclay_orange"),
            new BlockSmallCube(Material.WOOD).setIdentifier("smallcube_claymagentaclean"),
            new BlockSmallCube(Material.WOOD).setIdentifier("smallcube_roman_hardclay_lightblue"),
            new BlockSmallCube(Material.WOOD).setIdentifier("smallcube_roman_hardclay_gray"),
            new BlockSmallCube(Material.WOOD).setIdentifier("smallcube_roman_hardclay_cyan"),
            new BlockSmallCube(Material.WOOD).setIdentifier("smallcube_claywhiteclean"),
            new BlockSmallCube(Material.WOOD).setIdentifier("smallcube_clayyellowmesa"),
            new BlockSmallCube(Material.WOOD).setIdentifier("smallcube_claylightdarkclean"),
            new BlockSmallCube(Material.WOOD).setIdentifier("smallcube_claywhitemesa"),
            new BlockSmallCube(Material.WOOD).setIdentifier("smallcube_roman_hardclay_green"),
            new BlockSmallCube(Material.WOOD).setIdentifier("smallcube_roman_hardclay_silver"),
            new BlockSmallCube(Material.WOOD).setIdentifier("smallcube_roman_hardclay_brown")
    );

    @SubscribeEvent
    public static void addItems(FairyRegisterEvent event) {
        event.registerBlocks(smallCubeBlocks);
    }
}
