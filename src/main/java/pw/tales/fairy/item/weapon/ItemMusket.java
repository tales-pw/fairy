package pw.tales.fairy.item.weapon;

import pw.tales.fairy.Fairy;
import pw.tales.fairy.item.IItemFairy;
import pw.tales.fairy.registration.registries.WeaponRegistry;

public class ItemMusket extends pw.tales.pillars.item_legacy.weapon.ItemMusket implements IItemFairy {
    public ItemMusket(String name) {
        super();
        this.setCreativeTab(WeaponRegistry.FAIRY_WEAPON_TAB);
        this.setIdentifier(name);
    }

    public ItemMusket setIdentifier(String name) {
        this.setRegistryName(Fairy.MOD_ID, name);
        this.setUnlocalizedName(name);
        return this;
    }
}
