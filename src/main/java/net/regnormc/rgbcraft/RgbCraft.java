package net.regnormc.rgbcraft;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import net.regnormc.rgbcraft.block.RgbBlocks;
import net.regnormc.rgbcraft.colorname.ColorNames;
import net.regnormc.rgbcraft.config.RgbCraftConfiguration;
import net.regnormc.rgbcraft.item.RgbItems;
import net.regnormc.rgbcraft.model.RgbModelProvider;
import net.regnormc.rgbcraft.model.RgbModels;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RgbCraft {
	public static final String MOD_ID = "rgb-craft";
	public static final Logger LOGGER = LogManager.getLogger("RGBCraft");

	public static final RgbCraftConfiguration DEFAULT_CONFIGURATION = new RgbCraftConfiguration(false);

	public static void init() {
		RgbItems.initialize();
		RgbBlocks.initialize();
	}

	public static void initClient() {
		ModelLoadingRegistry.INSTANCE.registerResourceProvider(rm -> new RgbModelProvider());
		BlockRenderLayerMap.INSTANCE.putBlock(RgbBlocks.RGB_STAINED_GLASS, RenderLayer.getTranslucent());

		RgbModels.initialize();
		ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new ColorNames.ReloadListener());
	}

	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}

	public static RgbCraftConfiguration getConfiguration() {
		return DEFAULT_CONFIGURATION;
	}
}
