package pw.tales.pillars.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import pw.tales.pillars.PillarsMod;

public class ItemModelWrapper {
    private final Item item;
    private final ItemPoseManager poses;

    public ItemModelWrapper(Item item, ItemPoseManager poses) {
        this.item = item;
        this.poses = poses;
    }

    public ItemModelWrapper(Item item, String[] poses) {
        this(item, new ItemPoseManager(poses));
    }

    public void onItemRightClick(PlayerEntity playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);

        String pose = this.poses.fromMeta(stack.getMetadata());
        String newPose = poses.next(pose);
        stack.setItemDamage(this.poses.getMeta(newPose));
    }

    public String getTranslationKey(ItemStack stack) {
        String pose = poses.fromMeta(stack.getMetadata());
        return item.getTranslationKey() + "." + pose;
    }

    private ResourceLocation getModelForMeta(int meta) {
        ResourceLocation itemRegistryName = item.getRegistryName();
        String pose = this.poses.fromMeta(meta);
        String name = itemRegistryName.getPath();
        return new ResourceLocation(itemRegistryName.getNamespace(), name + "_" + pose);
    }

    public void registerModels() {
        for (int i = 0; i < this.poses.getAmount(); i++) {
            ResourceLocation model = this.getModelForMeta(i);
            ModelResourceLocation modelVariant = new ModelResourceLocation(model, "inventory");
            ModelLoader.setCustomModelResourceLocation(item, i, modelVariant);

            PillarsMod.INSTANCE.registry.registerModel(model, item, i);
        }
    }
}
