package pw.tales.fairy.item;

import net.minecraft.item.Item;
import pw.tales.fairy.Fairy;
import pw.tales.fairy.registration.registries.ItemsRegistry;

public class ItemSimple extends Item implements IItemFairy {
    public ItemSimple() {
        super();
        this.setCreativeTab(ItemsRegistry.FAIRY_MISC_TAB);
    }

    public void registerModels() {
        Fairy.proxy.setUpItemVariant(this, 0, "inventory");
    }

    public ItemSimple setIdentifier(String name) {
        this.setRegistryName(Fairy.MOD_ID, name);
        this.setUnlocalizedName(name);
        return this;
    }
}
