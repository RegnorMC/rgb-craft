package net.regnormc.rgbcraft.models;

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
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

public class RgbWoolModel implements UnbakedModel, BakedModel, FabricBakedModel {
	private static final SpriteIdentifier[] SPRITE_IDS = new SpriteIdentifier[]{
			new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, new Identifier("rgb-craft:block/rgb_wool"))
	};
	private Sprite[] SPRITES = new Sprite[1];
	private Mesh mesh;

	private static final Identifier DEFAULT_BLOCK_MODEL = new Identifier("minecraft:block/block");
	private ModelTransformation transformation;

	@Override
	public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction face, Random random) {
		return null;
	}

	@Override
	public boolean useAmbientOcclusion() {
		return true;
	}

	@Override
	public boolean hasDepth() {
		return false;
	}

	@Override
	public boolean isSideLit() {
		return true;
	}

	@Override
	public boolean isBuiltin() {
		return false;
	}

	@Override
	public Sprite getSprite() {
		return SPRITES[0];
	}

	@Override
	public ModelTransformation getTransformation() {
		return transformation;
	}

	@Override
	public ModelOverrideList getOverrides() {
		return ModelOverrideList.EMPTY;
	}

	@Override
	public Collection<Identifier> getModelDependencies() {
		return Arrays.asList(DEFAULT_BLOCK_MODEL);
	}

	@Override
	public Collection<SpriteIdentifier> getTextureDependencies(Function<Identifier, UnbakedModel> unbakedModelGetter, Set<Pair<String, String>> unresolvedTextureReferences) {
		return Arrays.asList(SPRITE_IDS);
	}

	@Nullable
	@Override
	public BakedModel bake(ModelLoader loader, Function<SpriteIdentifier, Sprite> textureGetter, ModelBakeSettings rotationContainer, Identifier modelId) {
		JsonUnbakedModel defaultBlockModel = (JsonUnbakedModel) loader.getOrLoadModel(DEFAULT_BLOCK_MODEL);

		transformation = defaultBlockModel.getTransformations();

		for(int i = 0; i < 1; ++i) {
			SPRITES[i] = textureGetter.apply(SPRITE_IDS[i]);
		}

		Renderer renderer = RendererAccess.INSTANCE.getRenderer();
		MeshBuilder builder = renderer.meshBuilder();
		QuadEmitter emitter = builder.getEmitter();

		/*for(Direction direction : Direction.values()) {
			emitter.square(direction, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f);
			emitter.spriteBake(0, SPRITES[0], MutableQuadView.BAKE_LOCK_UV);
			emitter.spriteColor(0, 255, 255, 255, 255);

			emitter.emit();
		}*/
		mesh = builder.build();

		return this;
	}

	@Override
	public boolean isVanillaAdapter() {
		return false;
	}

	Random rand = new Random();

	public void generateModel(RenderContext context) {
		QuadEmitter emitter = context.getEmitter();

		int randomColor = rand.nextInt();

		for(Direction direction : Direction.values()) {
			emitter.square(direction, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f);
			emitter.spriteBake(0, SPRITES[0], MutableQuadView.BAKE_LOCK_UV);
			emitter.spriteColor(0, randomColor, randomColor, randomColor, randomColor);

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
