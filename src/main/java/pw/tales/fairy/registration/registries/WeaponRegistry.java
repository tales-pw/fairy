package pw.tales.fairy.registration.registries;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import pw.tales.fairy.Fairy;
import pw.tales.fairy.item.IItemFairy;
import pw.tales.fairy.item.weapon.ItemMusket;
import pw.tales.fairy.item.weapon.ItemPistol;
import pw.tales.fairy.item.weapon.ItemWeapon;
import pw.tales.fairy.registration.events.FairyRegisterEvent;
import pw.tales.pillars.item.ItemPoseManager;

import java.util.Arrays;
import java.util.List;

@Mod.EventBusSubscriber
public class WeaponRegistry {
    public static CreativeTabs FAIRY_WEAPON_TAB = new CreativeTabs(String.format("%s.weapon", Fairy.MOD_ID)) {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(Items.crossbow, 1, 0);
        }
    };

    public final static ItemPoseManager oneHandedPoses =
            ItemPoseManager.builder().add("default").add("block").add("backswing")
                    .add("mordhau").add("preparing_to_attack").add("relaxed_holding")
                    .add("supporting_twohands").add("two_hand").build();
    public final static ItemPoseManager twoHandedPoses =
            ItemPoseManager.builder().add("default").add("shoulder").add("gerbeck").add("kohler")
                    .add("mordhau").add("relaxed_holding").add("relaxed_holding_onehand")
                    .add("supporting_hand").build();
    public final static ItemPoseManager daggerPoses =
            ItemPoseManager.builder().add("pose_default").add("pose_backswing").add("pose_reverse_1")
                    .add("pose_reverse_2").add("pose_behind_back").build();
    public final static ItemPoseManager glaivePoses =
            ItemPoseManager.builder().add("pose_1").add("pose_2").add("pose_3").add("pose_4").build();
    public final static ItemPoseManager crossbowPoses =
            ItemPoseManager.builder().add("down").add("down_arrow").add("up_arrow").add("up").build();
    public final static ItemPoseManager warhammerPoses =
            ItemPoseManager.builder().add("pose_default").add("pose_reverse_default").add("pose_hit")
                    .build();
    public final static ItemPoseManager battleaxePoses =
            ItemPoseManager.builder().add("pose_default").add("pose_block").add("pose_backslash")
                    .build();
    public final static ItemPoseManager batPoses =
            ItemPoseManager.builder().add("pose_default").add("pose_backswing").add("pose_block").add("pose_handle")
                    .build();
    public final static ItemPoseManager poleaxePoses =
            ItemPoseManager.builder().add("pose_stance").add("pose_stand")
                    .build();
    public final static ItemPoseManager halberdPoses =
            ItemPoseManager.builder().add("pose_default").add("pose_stance").add("pose_standing")
                    .build();
    public final static ItemPoseManager greataxePoses =
            ItemPoseManager.builder().add("pose_stand").add("pose_stance").build();
    public final static ItemPoseManager staffPoses =
            ItemPoseManager.builder().add("pose_default").add("pose_standing").add("pose_block")
                    .add("pose_stance").build();
    public final static ItemPoseManager macePoses =
            ItemPoseManager.builder().add("pose_default").add("pose_twohanded_stance").add("pose_up")
                    .build();
    public final static ItemPoseManager tridentPoses =
            ItemPoseManager.builder().add("pose_default").add("pose_stance").add("pose_attack").build();
    public final static ItemPoseManager brassKnucklesPoses =
            ItemPoseManager.builder().add("pose_default").add("pose_hit_1").add("pose_hit_2").build();
    public final static ItemPoseManager rapierPoses =
            ItemPoseManager.builder().add("pose_default").add("pose_block").add("pose_parry")
                    .add("pose_thrusting").build();
    public final static ItemPoseManager spearPoses =
            ItemPoseManager.builder().add("pose_default").add("pose_standing").add("pose_stance")
                    .build();
    public final static ItemPoseManager longbowPoses =
            ItemPoseManager.builder().add("pose_base").add("pose_behind_back")
                    .build();
    public final static ItemPoseManager bombPoses =
            ItemPoseManager.builder().add("default").add("fire").add("throw")
                    .build();
    public final static ItemPoseManager lucernHammerPoses =
            ItemPoseManager.builder().add("default").build();
    public final static ItemPoseManager lancePoses =
            ItemPoseManager.builder().add("default").build();
    public final static ItemPoseManager shieldPoses =
            ItemPoseManager.builder().add("default").build();
    public final static ItemPoseManager morningStarPoses =
            ItemPoseManager.builder().add("safe").add("idle").add("stance").build();

    public static List<IItemFairy> weapons = Arrays.asList(
            // Special
            new ItemPistol("revolver"),
            new ItemPistol("pistol"),
            new ItemPistol("pistol_2"),
            new ItemMusket("musket"),
            new ItemMusket("revolver_rifle"),

            // One-handed
            new ItemWeapon("katana", oneHandedPoses),
            new ItemWeapon("woodcutter_axe", oneHandedPoses),
            new ItemWeapon("shortsword", oneHandedPoses),
            new ItemWeapon("bastard_sword", oneHandedPoses),
            new ItemWeapon("battleaxe", battleaxePoses),
            new ItemWeapon("mace", macePoses),
            new ItemWeapon("bat", batPoses),
            new ItemWeapon("warhammer", warhammerPoses),

            // Pole
            new ItemWeapon("poleaxe", poleaxePoses),
            new ItemWeapon("spear", spearPoses),
            new ItemWeapon("lucern_hammer", lucernHammerPoses),
            new ItemWeapon("glaive", glaivePoses),
            new ItemWeapon("trident", tridentPoses),
            new ItemWeapon("halberd", halberdPoses),
            new ItemWeapon("staff", staffPoses),
            new ItemWeapon("greataxe", greataxePoses),

            // Two-handed
            new ItemWeapon("claymore", twoHandedPoses),

            // Daggers
            new ItemWeapon("dagger", daggerPoses),

            // Shields
            new ItemWeapon("raynor_shield", shieldPoses),

            // Other
            new ItemWeapon("crossbow", crossbowPoses),
            new ItemWeapon("longbow", longbowPoses),
            new ItemWeapon("bomb", bombPoses),
            new ItemWeapon("brass_knuckles", brassKnucklesPoses),
            new ItemWeapon("rapier", rapierPoses),
            new ItemWeapon("lance", lancePoses),
            new ItemWeapon("morning_star", morningStarPoses)
    );

    @SubscribeEvent
    public static void addItems(FairyRegisterEvent event) {
        event.registerItems(weapons);
    }

    @GameRegistry.ObjectHolder(Fairy.MOD_ID)
    public static class Items {
        public static final ItemWeapon crossbow = null;
    }
}
