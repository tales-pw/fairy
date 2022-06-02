package pw.tales.fairy.featured_block.features;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@MethodsReturnNonnullByDefault
public class FeatureSwitch extends FeatureStoreFlag {
    public FeatureSwitch(String name) {
        super(name);
    }

    public FeatureSwitch(PropertyBool property) {
        super(property);
    }

    @Override
    public boolean onActivated(World worldIn, BlockPos pos, IBlockState state,
                               PlayerEntity playerIn, Hand hand, Direction facing, float hitX, float hitY,
                               float hitZ) {
        return worldIn.setBlockState(pos, state.cycleProperty(property));
    }
}
