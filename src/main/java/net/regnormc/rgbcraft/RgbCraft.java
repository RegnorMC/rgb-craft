package net.regnormc.rgbcraft;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import net.regnormc.rgbcraft.item.RgbItems;
import net.regnormc.rgbcraft.models.RgbBlocks;
import net.regnormc.rgbcraft.models.RgbModelProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RgbCraft {
	public static final String MOD_ID = "rgb-craft";
	public static final Logger LOGGER = LogManager.getLogger("RGBCraft");

	public static void init() {
		RgbItems.initialize();
		RgbBlocks.initialize();
	}

	public static void initClient() {
		ModelLoadingRegistry.INSTANCE.registerResourceProvider(rm -> new RgbModelProvider());

		BlockRenderLayerMap.INSTANCE.putBlock(RgbBlocks.RGB_STAINED_GLASS, RenderLayer.getTranslucent());
	}

	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}
}
