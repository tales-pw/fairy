package pw.tales.pillars.mixin;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
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
    public ModelRenderer rightArm;
    @Shadow
    public ModelRenderer leftArm;

    @Inject(method = "setupAnim*", at = @At("RETURN"))
    public void onPostRotationAngles(
            LivingEntity entity,
            float p_225597_2_,
            float p_225597_3_,
            float p_225597_4_,
            float p_225597_5_,
            float p_225597_6_,
            CallbackInfo ci
    ) {
        PillarsMod.INSTANCE.renderer.updateRotation(entity, rightArm, leftArm);
    }
}
