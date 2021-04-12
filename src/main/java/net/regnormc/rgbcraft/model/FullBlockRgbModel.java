package net.regnormc.rgbcraft.model;

import com.mojang.datafixers.util.Pair;
import net.fabricmc.fabric.api.renderer.v1.Renderer;
import net.fabricmc.fabric.api.renderer.v1.RendererAccess;
import net.fabricmc.fabric.api.renderer.v1.mesh.Mesh;
import net.fabricmc.fabric.api.renderer.v1.mesh.MeshBuilder;
import net.fabricmc.fabric.api.renderer.v1.mesh.MutableQuadView;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.fabricmc.fabric.api.renderer.v1.model.FabricBakedModel;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.client.render.model.ModelBakeSettings;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.client.render.model.json.JsonUnbakedModel;
import net.minecraft.client.render.model.json.ModelOverrideList;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockRenderView;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

public class FullBlockRgbModel extends BasicCustomRgbModel {
	protected final Identifier textureIdentifier;

	public FullBlockRgbModel(String textureID) {
		textureIdentifier = new Identifier("rgb-craft:block/" + textureID);
		SPRITE_IDS.add(new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, textureIdentifier));
	}

	@Override
	public Sprite getSprite() {
		return SPRITES.get(0);
	}

	Random rand = new Random();

	void generateModel(RenderContext context) {
		int randomColor = rand.nextInt();

		randomColor = randomColor % (0xFFFFFF);

		generateModel(context, randomColor);
	}

	void generateModel(RenderContext context, int color) {
		QuadEmitter emitter = context.getEmitter();

		for(Direction direction : Direction.values()) {
			emitter.square(direction, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f);
			emitter.spriteBake(0, SPRITES.get(0), MutableQuadView.BAKE_LOCK_UV);
			emitter.spriteColor(0, color, color, color, color);

			emitter.emit();
		}
	}

	@Override
	public void emitBlockQuads(BlockRenderView blockView, BlockState state, BlockPos pos, Supplier<Random> randomSupplier, RenderContext context) {
		generateModel(context);

		context.meshConsumer().accept(mesh);
	}

	@Override
	public void emitItemQuads(ItemStack stack, Supplier<Random> randomSupplier, RenderContext context) {
		generateModel(context);

		context.meshConsumer().accept(mesh);
	}
}
