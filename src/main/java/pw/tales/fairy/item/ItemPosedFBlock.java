package pw.tales.fairy.item;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import pw.tales.pillars.item.ItemModelMediator;
import pw.tales.pillars.item.ItemPoseManager;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
public class ItemPosedFBlock extends BlockItem implements IItemFairy {
    private ItemModelMediator itemModelMediator;

    public ItemPosedFBlock(Block block, ItemPoseManager poseManager) {
        super(block);
        this.setRegistryName(block.getRegistryName());
        this.itemModelMediator = new ItemModelMediator(this, poseManager);
    }

    public ItemPosedFBlock setCreativeTab(ItemGroup tab) {
        super.setCreativeTab(tab);
        return this;
    }


    @Override
    @ParametersAreNonnullByDefault
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn,
                                                    Hand handIn) {
        this.itemModelMediator.onItemRightClick(playerIn, handIn);
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public ActionResultType onItemUse(PlayerEntity player, World worldIn, BlockPos pos, Hand hand, Direction facing, float hitX, float hitY, float hitZ) {
        if (!player.isSneaking()) return ActionResultType.PASS;
        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }

    @Override
    public String getTranslationKey(ItemStack stack) {
        return this.itemModelMediator.getTranslationKey(stack);
    }

    public void registerModels() {
        this.itemModelMediator.registerItemModelsProperties();
    }
}
