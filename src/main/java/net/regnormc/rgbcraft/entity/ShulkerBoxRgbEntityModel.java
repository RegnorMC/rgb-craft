package net.regnormc.rgbcraft.entity;

import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.ShulkerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.regnormc.rgbcraft.mixin.MatrixStackAccessor;

import java.util.ArrayDeque;
import java.util.Deque;

public class ShulkerBoxRgbEntityModel extends ShulkerEntityModel {
	MatrixStack matrixStack = new MatrixStack();
	VertexConsumer vertexConsumer;
	int light;
	int overlay;

	public ShulkerBoxRgbEntityModel(ModelPart root) {
		super(root);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
		Deque<MatrixStack.Entry> matrixStack = ((ArrayDeque)((MatrixStackAccessor)matrices).getStack()).clone();
		((MatrixStackAccessor)this.matrixStack).setStack(matrixStack);
		this.vertexConsumer = vertices;
		this.light = light;
		this.overlay = overlay;
		//super.render(matrices, vertices, light, overlay, red, green, blue, alpha);
	}

	public void renderColor(ShulkerBoxBlockEntity shulkerBoxBlockEntity) {
		super.render(matrixStack, vertexConsumer, light, overlay, 1.0f, 0.0f, 0.0f, 1.0f);
	}
}
