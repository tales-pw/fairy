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
import pw.tales.pillars.item_legacy.weapon.ItemMusket;

@Mixin(ModelBiped.class)
public abstract class MusketBipedModel {
    @Shadow
    public ModelBiped.ArmPose rightArmPose;
    @Shadow
    public ModelBiped.ArmPose leftArmPose;

    @Shadow
    public ModelRenderer bipedRightArm;

    @Shadow
    public ModelRenderer bipedLeftArm;

    public void processPreHand(EntityLivingBase entityLiving, EnumHand hand) {
        ItemStack itemStack = entityLiving.getHeldItem(hand);
        Item item = itemStack.getItem();

        if (!(item instanceof ItemMusket))
            return;

        ItemMusket itemMusket = (ItemMusket) item;
        ItemMusket.Pose pose = itemMusket.getPose(itemStack);

        if (pose == ItemMusket.Pose.AIM) {
            this.rightArmPose = ModelBiped.ArmPose.BOW_AND_ARROW;
            this.leftArmPose = ModelBiped.ArmPose.BOW_AND_ARROW;
        }
    }

    public void processPostHand(EntityLivingBase entityLiving, EnumHand hand, ModelRenderer model) {
        ItemStack itemStack = entityLiving.getHeldItem(hand);
        Item item = itemStack.getItem();

        if (!(item instanceof ItemMusket))
            return;

        ItemMusket itemMusket = (ItemMusket) item;
        ItemMusket.Pose pose = itemMusket.getPose(itemStack);

        if (pose == ItemMusket.Pose.MARCH) {
            model.rotateAngleX = (float) Math.toRadians(-45);
        }
    }


    @Inject(method = "setRotationAngles", at = @At("HEAD"))
    public void onPreRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks,
                                    float netHeadYaw, float headPitch, float scaleFactor, Entity entity, CallbackInfo ci) {

        if (!(entity instanceof EntityLivingBase))
            return;

        EntityLivingBase entityLiving = (EntityLivingBase) entity;

        processPreHand(entityLiving, EnumHand.MAIN_HAND);
        processPreHand(entityLiving, EnumHand.OFF_HAND);
    }

    @Inject(method = "setRotationAngles", at = @At("RETURN"))
    public void onPostRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks,
                                     float netHeadYaw, float headPitch, float scaleFactor, Entity entity, CallbackInfo ci) {

        if (!(entity instanceof EntityLivingBase))
            return;

        EntityLivingBase entityLiving = (EntityLivingBase) entity;

        processPostHand(entityLiving, EnumHand.MAIN_HAND, this.bipedRightArm);
        processPostHand(entityLiving, EnumHand.OFF_HAND, this.bipedLeftArm);
    }
}
