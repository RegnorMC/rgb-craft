package net.regnormc.rgbcraft;

import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.regnormc.rgbcraft.models.ModelProvider;
import net.regnormc.rgbcraft.item.RgbCraftItems;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RgbCraft {
	public static final String MOD_ID = "rgb-craft";
	public static final Logger LOGGER = LogManager.getLogger("RGBCraft");

	public static final Block RGB_WOOL = new Block(FabricBlockSettings.of(Material.WOOL).strength(0.8F).sounds(BlockSoundGroup.WOOL));

	public static void init() {
		RgbCraftItems.initialize();
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "rgb_wool"), RGB_WOOL);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "rgb_wool"), new BlockItem(RGB_WOOL, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
	}

	public static void initClient() {
		ModelLoadingRegistry.INSTANCE.registerResourceProvider(rm -> new ModelProvider());
	}

	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}
}
