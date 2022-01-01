package pw.tales.fairy.item;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import pw.tales.pillars.item.ItemModelWrapper;
import pw.tales.pillars.item.ItemPoseManager;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
public class ItemPosedFBlock extends ItemBlock implements IItemFairy {
    private ItemModelWrapper itemModelMediator;

    public ItemPosedFBlock(Block block, ItemPoseManager poseManager) {
        super(block);
        this.setRegistryName(block.getRegistryName());
        this.itemModelMediator = new ItemModelWrapper(this, poseManager);
    }

    public ItemPosedFBlock setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }


    @Override
    @ParametersAreNonnullByDefault
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn,
                                                    EnumHand handIn) {
        this.itemModelMediator.onItemRightClick(playerIn, handIn);
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!player.isSneaking()) return EnumActionResult.PASS;
        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return this.itemModelMediator.getTranslationKey(stack);
    }

    public void registerModels() {
        this.itemModelMediator.registerModels();
    }
}
