package pw.tales.fairy.registration.events;

import net.minecraftforge.fml.common.eventhandler.Event;
import pw.tales.fairy.block.IBlockFairy;
import pw.tales.fairy.item.IItemFairy;

import java.util.ArrayList;
import java.util.List;

public class FairyRegisterEvent extends Event {
    private final List<IItemFairy> items = new ArrayList<>();
    private final List<IBlockFairy> blocks = new ArrayList<>();

    public FairyRegisterEvent() {
    }

    public void registerItems(List<IItemFairy> items) {
        this.items.addAll(items);
    }

    public void registerBlocks(List<IBlockFairy> blocks) {
        this.blocks.addAll(blocks);
    }

    public List<IItemFairy> getItems() {
        return items;
    }

    public List<IBlockFairy> getBlocks() {
        return blocks;
    }
}
