package net.regnormc.rgbcraft.models;

import net.fabricmc.fabric.api.client.model.ModelProviderContext;
import net.fabricmc.fabric.api.client.model.ModelProviderException;
import net.fabricmc.fabric.api.client.model.ModelResourceProvider;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class ModelProvider implements ModelResourceProvider {
	public static final FullBlockRgbModel RGB_WOOL_MODEL = new FullBlockRgbModel("rgb_wool");
	public static final Identifier RGB_WOOL_MODEL_BLOCK = new Identifier("rgb-craft:block/rgb_wool");
	public static final Identifier RGB_WOOL_MODEL_ITEM = new Identifier("rgb-craft:item/rgb_wool");

	public static final FullBlockRgbModel RGB_CONCREATE_MODEL = new FullBlockRgbModel("rgb_concrete");
	public static final Identifier RGB_CONCREATE_MODEL_BLOCK = new Identifier("rgb-craft:block/rgb_concrete");
	public static final Identifier RGB_CONCREATE_MODEL_ITEM = new Identifier("rgb-craft:item/rgb_concrete");

	public static final FullBlockRgbModel RGB_CONCREATE_POWDER_MODEL = new FullBlockRgbModel("rgb_concrete_powder");
	public static final Identifier RGB_CONCREATE_POWDER_MODEL_BLOCK = new Identifier("rgb-craft:block/rgb_concrete_powder");
	public static final Identifier RGB_CONCREATE_POWDER_MODEL_ITEM = new Identifier("rgb-craft:item/rgb_concrete_powder");

	public static final FullBlockRgbModel RGB_STAINED_GLASS_MODEL = new FullBlockRgbModel("rgb_stained_glass");
	public static final Identifier RGB_STAINED_GLASS_MODEL_BLOCK = new Identifier("rgb-craft:block/rgb_stained_glass");
	public static final Identifier RGB_STAINED_GLASS_MODEL_ITEM = new Identifier("rgb-craft:item/rgb_stained_glass");

	public static final FullBlockRgbModel RGB_TERRACOTA_MODEL = new FullBlockRgbModel("rgb_terracota");
	public static final Identifier RGB_TERRACOTA_MODEL_BLOCK = new Identifier("rgb-craft:block/rgb_terracota");
	public static final Identifier RGB_TERRACOTA_MODEL_ITEM = new Identifier("rgb-craft:item/rgb_terracota");
	@Override
	public @Nullable UnbakedModel loadModelResource(Identifier resourceId, ModelProviderContext context) throws ModelProviderException {
		if(resourceId.equals(RGB_WOOL_MODEL_BLOCK) || resourceId.equals(RGB_WOOL_MODEL_ITEM)) {
			return RGB_WOOL_MODEL;
		}
		else if(resourceId.equals(RGB_CONCREATE_MODEL_BLOCK) || resourceId.equals(RGB_CONCREATE_MODEL_ITEM)) {
			return RGB_CONCREATE_MODEL;
		}
		else if(resourceId.equals(RGB_CONCREATE_POWDER_MODEL_BLOCK) || resourceId.equals(RGB_CONCREATE_POWDER_MODEL_ITEM)) {
			return RGB_CONCREATE_POWDER_MODEL;
		}
		else if(resourceId.equals(RGB_STAINED_GLASS_MODEL_BLOCK) || resourceId.equals(RGB_STAINED_GLASS_MODEL_ITEM)) {
			return RGB_STAINED_GLASS_MODEL;
		}
		else if(resourceId.equals(RGB_TERRACOTA_MODEL_BLOCK) || resourceId.equals(RGB_TERRACOTA_MODEL_ITEM)) {
			return RGB_TERRACOTA_MODEL;
		}
		else {
			return null;
		}
	}
}
