package net.regnormc.rgbcraft.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ConcretePowderBlock;
import net.minecraft.block.StainedGlassBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.regnormc.rgbcraft.RgbCraft;

public class RgbBlocks {
	public static final Block RGB_WOOL = register(RgbCraft.id("rgb_wool"), new Block(FabricBlockSettings.copyOf(Blocks.BLACK_WOOL)), ItemGroup.BUILDING_BLOCKS);
	public static final Block RGB_CONCRETE = register(RgbCraft.id("rgb_concrete"), new Block(FabricBlockSettings.copyOf(Blocks.BLACK_CONCRETE)), ItemGroup.BUILDING_BLOCKS);
	public static final Block RGB_CONCRETE_POWDER = register(RgbCraft.id("rgb_concrete_powder"), new ConcretePowderBlock(RGB_CONCRETE, FabricBlockSettings.copyOf(Blocks.BLACK_CONCRETE_POWDER)), ItemGroup.BUILDING_BLOCKS);
	public static final Block RGB_STAINED_GLASS = register(RgbCraft.id("rgb_stained_glass"), new StainedGlassBlock(DyeColor.LIGHT_BLUE, FabricBlockSettings.copyOf(Blocks.BLACK_STAINED_GLASS)), ItemGroup.BUILDING_BLOCKS);
	public static final Block RGB_TERRACOTTA = register(RgbCraft.id("rgb_terracotta"), new Block(FabricBlockSettings.copyOf(Blocks.TERRACOTTA)), ItemGroup.BUILDING_BLOCKS);

	private static <T extends Block> T register(Identifier id, T block, ItemGroup itemGroup) {
		return register(id, block, new BlockItem(block, new FabricItemSettings().group(itemGroup)));
	}

	private static <T extends Block> T register(Identifier id, T block, BlockItem blockItem) {
		Registry.register(Registry.ITEM, id, blockItem);
		return register(id, block);
	}

	private static <T extends Block> T register(Identifier id, T block) {
		return Registry.register(Registry.BLOCK, id, block);
	}

	/**
	 * Used to initialize the class.
	 */
	public static void initialize() {
	}
}
