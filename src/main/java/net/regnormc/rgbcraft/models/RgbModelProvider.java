package net.regnormc.rgbcraft.models;

import net.fabricmc.fabric.api.client.model.ModelProviderContext;
import net.fabricmc.fabric.api.client.model.ModelResourceProvider;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class RgbModelProvider implements ModelResourceProvider {
	public static final FullBlockRgbModel RGB_WOOL_MODEL = new FullBlockRgbModel("rgb_wool");
	public static final Identifier RGB_WOOL_BLOCK = new Identifier("rgb-craft:block/rgb_wool");
	public static final Identifier RGB_WOOL_ITEM = new Identifier("rgb-craft:item/rgb_wool");

	public static final FullBlockRgbModel RGB_CONCRETE_MODEL = new FullBlockRgbModel("rgb_concrete");
	public static final Identifier RGB_CONCRETE_BLOCK = new Identifier("rgb-craft:block/rgb_concrete");
	public static final Identifier RGB_CONCRETE_ITEM = new Identifier("rgb-craft:item/rgb_concrete");

	public static final FullBlockRgbModel RGB_CONCRETE_POWDER_MODEL = new FullBlockRgbModel("rgb_concrete_powder");
	public static final Identifier RGB_CONCRETE_POWDER_BLOCK = new Identifier("rgb-craft:block/rgb_concrete_powder");
	public static final Identifier RGB_CONCRETE_POWDER_ITEM = new Identifier("rgb-craft:item/rgb_concrete_powder");

	public static final FullBlockRgbModel RGB_STAINED_GLASS_MODEL = new GlassRgbModel("rgb_stained_glass");
	public static final Identifier RGB_STAINED_GLASS_BLOCK = new Identifier("rgb-craft:block/rgb_stained_glass");
	public static final Identifier RGB_STAINED_GLASS_ITEM = new Identifier("rgb-craft:item/rgb_stained_glass");

	public static final FullBlockRgbModel RGB_TERRACOTTA_MODEL = new FullBlockRgbModel("rgb_terracotta");
	public static final Identifier RGB_TERRACOTTA_BLOCK = new Identifier("rgb-craft:block/rgb_terracotta");
	public static final Identifier RGB_TERRACOTTA_ITEM = new Identifier("rgb-craft:item/rgb_terracotta");

	@Override
	public @Nullable UnbakedModel loadModelResource(Identifier resourceId, ModelProviderContext context) {
		if(resourceId.equals(RGB_WOOL_BLOCK) || resourceId.equals(RGB_WOOL_ITEM)) {
			return RGB_WOOL_MODEL;
		}
		else if(resourceId.equals(RGB_CONCRETE_BLOCK) || resourceId.equals(RGB_CONCRETE_ITEM)) {
			return RGB_CONCRETE_MODEL;
		}
		else if(resourceId.equals(RGB_CONCRETE_POWDER_BLOCK) || resourceId.equals(RGB_CONCRETE_POWDER_ITEM)) {
			return RGB_CONCRETE_POWDER_MODEL;
		}
		else if(resourceId.equals(RGB_STAINED_GLASS_BLOCK) || resourceId.equals(RGB_STAINED_GLASS_ITEM)) {
			return RGB_STAINED_GLASS_MODEL;
		}
		else if(resourceId.equals(RGB_TERRACOTTA_BLOCK) || resourceId.equals(RGB_TERRACOTTA_ITEM)) {
			return RGB_TERRACOTTA_MODEL;
		}
		else {
			return null;
		}
	}
}
