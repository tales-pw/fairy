package pw.tales.pillars.mixin;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pw.tales.pillars.item_legacy.weapon.ItemPistol;

@Mixin(ModelBiped.class)
public abstract class PistolBipedModel {
    @Shadow
    public ModelRenderer bipedRightArm;
    @Shadow
    public ModelRenderer bipedLeftArm;

    @Shadow
    public ModelRenderer bipedHead;

    private void processHand(EntityLivingBase entityLiving, EnumHand hand, ModelRenderer model) {
        ItemStack itemStack = entityLiving.getHeldItem(hand);
        Item item = itemStack.getItem();

        if (!(item instanceof ItemPistol))
            return;

        ItemPistol itemPistol = (ItemPistol) item;
        ItemPistol.Pose pose = itemPistol.getPose(itemStack);

        if (pose == ItemPistol.Pose.AIM) {
            model.rotateAngleX = (float) (this.bipedHead.rotateAngleX + Math.toRadians(-90));
        }
    }

    @Inject(method = "setRotationAngles", at = @At("RETURN"))
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks,
                                  float netHeadYaw, float headPitch, float scaleFactor, Entity entity, CallbackInfo ci) {

        if (!(entity instanceof EntityLivingBase))
            return;

        EntityLivingBase entityLiving = (EntityLivingBase) entity;

        processHand(entityLiving, EnumHand.MAIN_HAND, this.bipedRightArm);
        processHand(entityLiving, EnumHand.OFF_HAND, this.bipedLeftArm);
    }
}
