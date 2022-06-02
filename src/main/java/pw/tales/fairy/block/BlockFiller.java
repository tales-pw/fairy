package pw.tales.fairy.block;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.BlockEmptyDrops;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import pw.tales.fairy.Fairy;
import pw.tales.fairy.registries.ObjectRegistry;

@MethodsReturnNonnullByDefault
public class BlockFiller extends BlockEmptyDrops
        implements IBlockFairy {
    public BlockFiller() {
        super(Material.ROCK);
        this.setLightOpacity(15).setBlockUnbreakable().setResistance(6000000.0F);
        this.setCreativeTab(ObjectRegistry.FAIRY_TAB);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isFullCube(IBlockState state) {
        return true;
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos,
                                            Direction face) {
        return BlockFaceShape.UNDEFINED;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return true;
    }

    @Override
    public final BlockFiller setIdentifier(String name) {
        this.setRegistryName(Fairy.MOD_ID, name);
        this.setTranslationKey(name);
        return this;
    }
}
