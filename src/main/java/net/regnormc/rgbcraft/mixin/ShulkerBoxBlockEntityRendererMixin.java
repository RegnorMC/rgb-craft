package net.regnormc.rgbcraft.mixin;

import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.block.entity.ShulkerBoxBlockEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.ShulkerEntityModel;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.regnormc.rgbcraft.entity.ShulkerBoxRgbEntityModel;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ShulkerBoxBlockEntityRenderer.class)
public class ShulkerBoxBlockEntityRendererMixin {
	@Mutable
	@Shadow @Final private ShulkerEntityModel<?> model;

	@Inject(at = @At("TAIL"), method = "<init>")
	void onCreate(BlockEntityRendererFactory.Context ctx, CallbackInfo info) {
		this.model = new ShulkerBoxRgbEntityModel(ctx.getLayerModelPart(EntityModelLayers.SHULKER));
	}

	@Inject(at = @At("TAIL"), method = "render")
	void onRender(ShulkerBoxBlockEntity shulkerBoxBlockEntity, float f, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, int j, CallbackInfo ci) {
		ShulkerBoxRgbEntityModel shulkerBoxRgbEntityModel = (ShulkerBoxRgbEntityModel)this.model;
		shulkerBoxRgbEntityModel.renderColor(shulkerBoxBlockEntity);
	}
}
