package pw.tales.pillars.item_legacy.posed;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
public abstract class ItemPosed<T extends IPoseEnum<T>>
        extends Item {
    protected IPoseManager<T> poseManager;

    public ItemPosed(IPoseManager<T> poses) {
        super();
        this.poseManager = poses;
    }

    public int getPosesAmount() {
        return poseManager.getAmount();
    }

    protected final ResourceLocation getModelVariant(String variant) {
        ResourceLocation rn = this.getRegistryName();
        return new ResourceLocation(rn.getNamespace(), rn.getPath() + "_" + variant);
    }

    public ResourceLocation getModelForMeta(int meta) {
        T pose = this.poseManager.fromId(meta);
        return getModelForPose(pose);
    }

    public ResourceLocation getModelForPose(T pose) {
        return this.getRegistryName();
    }

    public T getPose(ItemStack itemStack) {
        return poseManager.fromId(itemStack.getMetadata());
    }

    @Override
    public String getTranslationKey(ItemStack stack) {
        T pose = this.poseManager.fromId(stack.getMetadata());
        return super.getTranslationKey(stack) + "." + pose.toString().toLowerCase();
    }

    @Override
    @ParametersAreNonnullByDefault
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn,
                                                    EnumHand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);

        T poseEnum = poseManager.fromId(stack.getMetadata()).cycle();

        stack.setItemDamage(poseEnum.getId());
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    private static void registerRender(Item item, ResourceLocation model, int meta) {
        ModelLoader.setCustomModelResourceLocation(item, meta,
                new ModelResourceLocation(model, "inventory"));
    }

    public void registerModels() {
        for (int i = 0; i < this.getPosesAmount(); i++) {
            registerRender(this, this.getModelForMeta(i), i);
        }
    }

}
