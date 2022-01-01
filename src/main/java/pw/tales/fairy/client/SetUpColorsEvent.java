package pw.tales.fairy.client;

import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraftforge.fml.common.eventhandler.Event;

public class SetUpColorsEvent extends Event {
    private final BlockColors blockColors;
    private final ItemColors itemColors;

    public SetUpColorsEvent(BlockColors blockColors, ItemColors itemColors) {
        this.blockColors = blockColors;
        this.itemColors = itemColors;
    }

    public BlockColors getBlockColors() {
        return blockColors;
    }

    public ItemColors getItemColors() {
        return itemColors;
    }
}
