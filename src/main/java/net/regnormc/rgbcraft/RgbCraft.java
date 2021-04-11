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
import net.regnormc.rgbcraft.models.RgbBlocks;
import net.regnormc.rgbcraft.item.RgbItems;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RgbCraft {
	public static final String MOD_ID = "rgb-craft";
	public static final Logger LOGGER = LogManager.getLogger("RGBCraft");

	public static final Block RGB_WOOL = new Block(FabricBlockSettings.of(Material.WOOL).strength(0.8F).sounds(BlockSoundGroup.WOOL));
	public static final Block RGB_CONCREATE = new Block(FabricBlockSettings.of(Material.WOOL).strength(0.8F).sounds(BlockSoundGroup.WOOL));
	public static final Block RGB_CONCREATE_POWDER = new Block(FabricBlockSettings.of(Material.WOOL).strength(0.8F).sounds(BlockSoundGroup.WOOL));
	public static final Block RGB_STAINED_GLASS = new Block(FabricBlockSettings.of(Material.WOOL).strength(0.8F).sounds(BlockSoundGroup.WOOL));
	public static final Block RGB_TERRACOTA = new Block(FabricBlockSettings.of(Material.WOOL).strength(0.8F).sounds(BlockSoundGroup.WOOL));

	public static void init() {
		RgbItems.initialize();
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "rgb_wool"), RGB_WOOL);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "rgb_wool"), new BlockItem(RGB_WOOL, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));

		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "rgb_concrete"), RGB_CONCREATE);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "rgb_concrete"), new BlockItem(RGB_CONCREATE, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));

		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "rgb_concrete_powder"), RGB_CONCREATE_POWDER);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "rgb_concrete_powder"), new BlockItem(RGB_CONCREATE_POWDER, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));

		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "rgb_stained_glass"), RGB_STAINED_GLASS);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "rgb_stained_glass"), new BlockItem(RGB_STAINED_GLASS, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));

		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "rgb_terracota"), RGB_TERRACOTA);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "rgb_terracota"), new BlockItem(RGB_TERRACOTA, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
	}

	public static void initClient() {
		ModelLoadingRegistry.INSTANCE.registerResourceProvider(rm -> new RgbBlocks());
	}

	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}
}
