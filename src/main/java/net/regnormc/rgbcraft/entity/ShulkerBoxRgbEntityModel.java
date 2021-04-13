package net.regnormc.rgbcraft.entity;

import net.minecraft.block.Block;
import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.ShulkerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.world.World;
import net.regnormc.rgbcraft.block.RgbBlocks;
import net.regnormc.rgbcraft.mixin.MatrixStackAccessor;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

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
		Random rand = new Random();

		World world = MinecraftClient.getInstance().player.getEntityWorld();
		if(world != null) {
			Block blockAtPos = world.getBlockState(shulkerBoxBlockEntity.getPos()).getBlock();
			//if (blockAtPos == RgbBlocks.RGB_SHULKER_BOX) {
				super.render(matrixStack, vertexConsumer, light, overlay, rand.nextFloat(), rand.nextFloat(), rand.nextFloat(), 1.0f);
			//}
		}
	}
}
