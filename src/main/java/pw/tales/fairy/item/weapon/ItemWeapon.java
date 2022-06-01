package pw.tales.fairy.item.weapon;

import net.minecraft.util.ResourceLocation;
import pw.tales.fairy.Fairy;
import pw.tales.fairy.item.IItemFairy;
import pw.tales.fairy.registries.WeaponRegistry;
import pw.tales.pillars.item.ItemPoseManager;

public class ItemWeapon extends pw.tales.pillars.item.ItemPosed implements IItemFairy {
    public ItemWeapon(String blockName, ItemPoseManager poseManager) {
        super(new ResourceLocation(Fairy.MOD_ID, blockName), poseManager);
        this.setCreativeTab(WeaponRegistry.FAIRY_WEAPON_TAB);
        this.setMaxStackSize(1);
    }
}
