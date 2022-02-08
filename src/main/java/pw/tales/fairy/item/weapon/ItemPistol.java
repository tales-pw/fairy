package pw.tales.fairy.item.weapon;

import pw.tales.fairy.Fairy;
import pw.tales.fairy.item.IItemFairy;
import pw.tales.fairy.registration.registries.WeaponRegistry;

public class ItemPistol extends pw.tales.pillars.item_legacy.weapon.ItemPistol implements IItemFairy {
    public ItemPistol(String name) {
        super();
        this.setCreativeTab(WeaponRegistry.FAIRY_WEAPON_TAB);
        this.setIdentifier(name);
    }

    public ItemPistol setIdentifier(String name) {
        this.setRegistryName(Fairy.MOD_ID, name);
        this.setTranslationKey(name);
        return this;
    }
}
