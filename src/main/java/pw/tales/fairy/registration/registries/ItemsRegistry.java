package pw.tales.fairy.registration.registries;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import pw.tales.fairy.Fairy;
import pw.tales.fairy.block.BlockLayingItem;
import pw.tales.fairy.block.IBlockFairy;
import pw.tales.fairy.client.SetUpColorsEvent;
import pw.tales.fairy.client.colors.MaskItemColors;
import pw.tales.fairy.item.*;
import pw.tales.fairy.item.weapon.ItemWeapon;
import pw.tales.fairy.registration.events.FairyRegisterEvent;
import pw.tales.pillars.item.ItemPoseManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Mod.EventBusSubscriber
public class ItemsRegistry {
    public final static ItemPoseManager maskPoses = ItemPoseManager.builder().add("pose_up").add("pose_normal")
            .add("pose_down").add("pose_down2").build();
    public final static ItemPoseManager clothMaskPoses = ItemPoseManager.builder().add("normal").add("down").build();
    public final static ItemPoseManager walkingStickPoses = ItemPoseManager.builder().add("default").build();
    public final static ItemPoseManager cigarettePoses = ItemPoseManager.builder().add("normal").add("down").build();
    public final static ItemPoseManager censerPoses = ItemPoseManager.builder().add("lit").build();
    public final static ItemPoseManager book_poses = ItemPoseManager.builder().add("open")
            .add("closed").add("one_hand").build();
    public static CreativeTabs FAIRY_MISC_TAB = new CreativeTabs(String.format("%s.misc", Fairy.MOD_ID)) {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(Items.cards, 1);
        }
    };
    public final static ItemPoseManager trayPoses = ItemPoseManager.builder().add("default").build();

    public final static List<IItemFairy> items = new ArrayList<>(Arrays.asList(
            new ItemCigarette(cigarettePoses).setIdentifier("cigarette"),
            new ItemSimple().setIdentifier("cards"),
            new ItemMask(maskPoses).setIdentifier("mask"),
            new ItemMask(clothMaskPoses).setIdentifier("cloth_mask"),
            new ItemWearable().setIdentifier("top_hat"),
            new ItemWearable().setIdentifier("top_hat_card"),
            new ItemWearable().setIdentifier("top_hat_bad"),
            new ItemWearable().setIdentifier("pipe"),
            new ItemWearable().setIdentifier("mail_coif"),
            new ItemWeapon("censer", censerPoses),
            new ItemWeapon("walking_stick", walkingStickPoses)
    ));

    public static List<IBlockFairy> blocks = Arrays.asList(
            new BlockLayingItem(Material.IRON).setIdentifier("tophelm"),
            new BlockLayingItem(Material.WOOD, book_poses).setIdentifier("book_1"),
            new BlockLayingItem(Material.WOOD, book_poses).setIdentifier("book_2"),
            new BlockLayingItem(Material.WOOD, book_poses).setIdentifier("book_3"),
            new BlockLayingItem(Material.WOOD, book_poses).setIdentifier("book_4"),
            new BlockLayingItem(Material.WOOD, trayPoses).setIdentifier("tray_empty"),
            new BlockLayingItem(Material.WOOD, trayPoses).setIdentifier("tray_goblets"),
            new BlockLayingItem(Material.WOOD, trayPoses).setIdentifier("tray_goblets_bottle"),
            new BlockLayingItem(Material.WOOD, trayPoses).setIdentifier("tray_jug_and_bread")
    );

    @SubscribeEvent
    public static void register(FairyRegisterEvent event) {
        event.registerBlocks(blocks);
        event.registerItems(items);
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void setUpColors(SetUpColorsEvent event) {
        ItemColors itemColors = event.getItemColors();
        itemColors.registerItemColorHandler(new MaskItemColors(), ItemsRegistry.Items.mask);
        itemColors.registerItemColorHandler(new MaskItemColors(), ItemsRegistry.Items.cloth_mask);
    }

    @GameRegistry.ObjectHolder(Fairy.MOD_ID)
    public static class Items {
        public static final ItemSimple cards = null;
        public static final ItemMask mask = null;
        public static final ItemMask cloth_mask = null;
    }
}
