package pw.tales.pillars.mixin;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pw.tales.pillars.PillarsMod;

@Mixin(BipedModel.class)
public abstract class ItemPoseBipedModel {
    @Shadow
    public ModelRenderer bipedRightArm;
    @Shadow
    public ModelRenderer bipedLeftArm;

    @Inject(method = "setRotationAngles", at = @At("RETURN"))
    public void onPostRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks,
                                     float netHeadYaw, float headPitch, float scaleFactor, Entity entity, CallbackInfo ci) {
        if (!(entity instanceof LivingEntity))
            return;

        LivingEntity entityLiving = (LivingEntity) entity;

        PillarsMod.INSTANCE.renderer.updateRotation(entityLiving, bipedRightArm, bipedLeftArm);
    }
}
