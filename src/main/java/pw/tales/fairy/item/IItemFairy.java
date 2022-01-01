package pw.tales.fairy.item;

import net.minecraft.item.Item;

public interface IItemFairy {
    default Item getItem() {
        return (Item) this;
    }

    default void registerModels() {
    }
}
