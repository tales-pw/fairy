package pw.tales.fairy.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import pw.tales.fairy.Fairy;

public class ItemFBlock extends ItemBlock implements IItemFairy {
    public ItemFBlock(Block block) {
        super(block);
        this.setRegistryName(block.getRegistryName());
    }

    @Override
    public void registerModels() {
        Fairy.proxy.setUpItemVariant(this, 0, "inventory");
    }
}
