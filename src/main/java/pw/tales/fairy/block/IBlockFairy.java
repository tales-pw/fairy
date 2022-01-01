package pw.tales.fairy.block;

import net.minecraft.block.Block;
import pw.tales.fairy.item.IItemFairy;
import pw.tales.fairy.item.ItemFBlock;

public interface IBlockFairy {
    default Block getBlock() {
        return (Block) this;
    }

    default IItemFairy createItem() {
        Block block = this.getBlock();
        return new ItemFBlock(block);
    }

    Block setIdentifier(String name);
}
