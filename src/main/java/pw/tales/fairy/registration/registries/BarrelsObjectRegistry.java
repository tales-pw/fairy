package pw.tales.fairy.registration.registries;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import pw.tales.fairy.block.BlockBarrel;
import pw.tales.fairy.block.IBlockFairy;
import pw.tales.fairy.registration.events.FairyRegisterEvent;

import java.util.Arrays;
import java.util.List;

@Mod.EventBusSubscriber
public class BarrelsObjectRegistry {
    public static List<IBlockFairy> blocks = Arrays.asList(
            new BlockBarrel().setIdentifier("barrel_cocoa_2"),
            new BlockBarrel().setIdentifier("barrel_cabbage"),
            new BlockBarrel().setIdentifier("barrel_fish"),
            new BlockBarrel().setIdentifier("barrel_turnip_full"),
            new BlockBarrel().setIdentifier("barrel_apple_2"),
            new BlockBarrel().setIdentifier("barrel_cocoa"),
            new BlockBarrel().setIdentifier("barrel_clay"),
            new BlockBarrel().setIdentifier("barrel_coal"),
            new BlockBarrel().setIdentifier("barrel_dirt_full"),
            new BlockBarrel().setIdentifier("barrel_bread"),
            new BlockBarrel().setIdentifier("barrel_potato"),
            new BlockBarrel().setIdentifier("barrel_wine"),
            new BlockBarrel().setIdentifier("barrel_eggs_full"),
            new BlockBarrel().setIdentifier("barrel_apple"),
            new BlockBarrel().setIdentifier("ore_emerald_1"),
            new BlockBarrel().setIdentifier("ore_gold_1"),

            // Sacks
            new BlockBarrel().setIdentifier("barrel_sack_apple"),
            new BlockBarrel().setIdentifier("barrel_sack_cabbage"),
            new BlockBarrel().setIdentifier("barrel_sack_bread"),
            new BlockBarrel().setIdentifier("barrel_sack_cocoa"),
            new BlockBarrel().setIdentifier("barrel_sack_cookie"),
            new BlockBarrel().setIdentifier("barrel_sack_potato"),
            new BlockBarrel().setIdentifier("barrel_sack_fish"),
            new BlockBarrel().setIdentifier("barrel_sack_grapes"),
            new BlockBarrel().setIdentifier("barrel_sack_flour"),
            new BlockBarrel().setIdentifier("barrel_sack_ruby"),
            new BlockBarrel().setIdentifier("barrel_sack_pipeweed"),
            new BlockBarrel().setIdentifier("barrel_sack_gold"),
            new BlockBarrel().setIdentifier("barrel_sack_hops")
    );

    @SubscribeEvent
    public static void addItems(FairyRegisterEvent event) {
        event.registerBlocks(blocks);
    }
}
