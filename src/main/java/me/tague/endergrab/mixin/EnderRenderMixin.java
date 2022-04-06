package me.tague.endergrab.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EndermanEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EndermanEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.EndermanEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(value= EnvType.CLIENT)
@Mixin(EndermanEntityRenderer.class)
public abstract class EnderRenderMixin extends MobEntityRenderer<EndermanEntity, EndermanEntityModel<EndermanEntity>> {

    public EnderRenderMixin(EntityRendererFactory.Context context, EndermanEntityModel<EndermanEntity> entityModel, float f) {
        super(context, entityModel, f);
    }

    @Inject(method = "render*", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/MobEntityRenderer;render(Lnet/minecraft/entity/mob/MobEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"))
    public void renderInsert(EndermanEntity endermanEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, CallbackInfo ci) {
        getModel().carryingBlock = (endermanEntity.getCarriedBlock() != null) || (endermanEntity.hasPassengers());
    }
}
