package net.regnormc.rgbcraft.models;

import net.fabricmc.fabric.api.client.model.ModelProviderContext;
import net.fabricmc.fabric.api.client.model.ModelProviderException;
import net.fabricmc.fabric.api.client.model.ModelResourceProvider;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class ModelProvider implements ModelResourceProvider {
	public static final RgbWoolModel RGB_WOOL_MODEL = new RgbWoolModel();
	public static final Identifier RGB_WOOL_MODEL_BLOCK = new Identifier("rgb-craft:block/rgb_wool");
	public static final Identifier RGB_WOOL_MODEL_ITEM = new Identifier("rgb-craft:item/rgb_wool");
	@Override
	public @Nullable UnbakedModel loadModelResource(Identifier resourceId, ModelProviderContext context) throws ModelProviderException {
		if(resourceId.equals(RGB_WOOL_MODEL_BLOCK) || resourceId.equals(RGB_WOOL_MODEL_ITEM)) {
			return RGB_WOOL_MODEL;
		} else {
			return null;
		}
	}
}
