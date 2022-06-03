package pw.tales.pillars.mixin;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pw.tales.pillars.item_legacy.weapon.ItemMusket;

@Mixin(BipedModel.class)
public abstract class MusketBipedModel {
    @Shadow
    public BipedModel.ArmPose rightArmPose;
    @Shadow
    public BipedModel.ArmPose leftArmPose;

    @Shadow
    public ModelRenderer bipedRightArm;

    @Shadow
    public ModelRenderer bipedLeftArm;

    public void processPreHand(LivingEntity entityLiving, Hand hand) {
        ItemStack itemStack = entityLiving.getHeldItem(hand);
        Item item = itemStack.getItem();

        if (!(item instanceof ItemMusket))
            return;

        ItemMusket itemMusket = (ItemMusket) item;
        ItemMusket.Pose pose = itemMusket.getPose(itemStack);

        if (pose == ItemMusket.Pose.AIM) {
            this.rightArmPose = BipedModel.ArmPose.BOW_AND_ARROW;
            this.leftArmPose = BipedModel.ArmPose.BOW_AND_ARROW;
        }
    }

    public void processPostHand(LivingEntity entityLiving, Hand hand, ModelRenderer model) {
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

        if (!(entity instanceof LivingEntity))
            return;

        LivingEntity entityLiving = (LivingEntity) entity;

        processPreHand(entityLiving, Hand.MAIN_HAND);
        processPreHand(entityLiving, Hand.OFF_HAND);
    }

    @Inject(method = "setRotationAngles", at = @At("RETURN"))
    public void onPostRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks,
                                     float netHeadYaw, float headPitch, float scaleFactor, Entity entity, CallbackInfo ci) {

        if (!(entity instanceof LivingEntity))
            return;

        LivingEntity entityLiving = (LivingEntity) entity;

        processPostHand(entityLiving, Hand.MAIN_HAND, this.bipedRightArm);
        processPostHand(entityLiving, Hand.OFF_HAND, this.bipedLeftArm);
    }
}
