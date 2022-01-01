package pw.tales.fairy.item;

import net.minecraft.block.Block;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemCloth;
import pw.tales.fairy.Fairy;

public class ItemFCloth extends ItemCloth implements IItemFairy {
    public ItemFCloth(Block block) {
        super(block);
        this.setRegistryName(block.getRegistryName());
    }

    @Override
    public void registerModels() {
        for (EnumDyeColor color : EnumDyeColor.values()) {
            Fairy.proxy.setUpItemVariant(this, color.getMetadata(), "inventory");
        }
    }
}
