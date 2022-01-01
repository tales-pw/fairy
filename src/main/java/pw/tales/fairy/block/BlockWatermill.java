package pw.tales.fairy.block;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.world.World;
import pw.tales.fairy.tile.TileWatermill;

import javax.annotation.Nullable;

@SuppressWarnings("deprecation") @MethodsReturnNonnullByDefault public class BlockWatermill
    extends BlockSimple {
    public BlockWatermill() {
        super(Material.WOOD);
    }

    @Override public boolean isFullBlock(IBlockState state) {
        return false;
    }

    @Override public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.INVISIBLE;
    }

    @Override public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable @Override public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileWatermill();
    }
}
