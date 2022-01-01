package pw.tales.fairy.block;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockRenderLayer;

@MethodsReturnNonnullByDefault public class BlockFlippedWater extends BlockSimple {

    public BlockFlippedWater() {
        super(Material.IRON);
    }

    @Override public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.SOLID;
    }
}
