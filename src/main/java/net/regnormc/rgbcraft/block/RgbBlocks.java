package net.regnormc.rgbcraft.block;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ConcretePowderBlock;
import net.minecraft.block.StainedGlassBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.regnormc.rgbcraft.RgbCraft;

import java.util.ArrayList;
import java.util.List;

public class RgbBlocks {
	public static class DyeableBlock {
		Block block;
		Block resultBlock;
		boolean vanillaBlock;

		public DyeableBlock(Block block, boolean vanillaBlock, Block resultBlock) {
			this.block = block;
			this.vanillaBlock = vanillaBlock;
			this.resultBlock = resultBlock;
		}
	}

	public static List<DyeableBlock> dyeableBlocks = new ArrayList<>();

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
		addDyeableBlock(block, false, null);
		return Registry.register(Registry.BLOCK, id, block);
	}

	public static void addDyeableBlock(Block block, boolean vanillaBlock, Block resultBlock) {
		dyeableBlocks.add(new DyeableBlock(block, vanillaBlock, resultBlock));
	}

	public static boolean isBlockDyeable(Block block) {
		for(DyeableBlock dyeableBlock : dyeableBlocks) {
			if(dyeableBlock.block == block) return true;
		}
		return false;
	}

	public static DyeableBlock getDyeableBlock(Block block) {
		for(DyeableBlock dyeableBlock : dyeableBlocks) {
			if(dyeableBlock.block == block) return dyeableBlock;
		}
		return null;
	}

	/**
	 * Used to initialize the class.
	 */
	public static void initialize() {
		addDyeableBlock(Blocks.WHITE_WOOL, true, RGB_WOOL);
		addDyeableBlock(Blocks.TERRACOTTA, true, RGB_TERRACOTTA);
		addDyeableBlock(Blocks.GLASS, true, RGB_STAINED_GLASS);
		UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
			Block hittedBlock = world.getBlockState(hitResult.getBlockPos()).getBlock();
			if(isBlockDyeable(hittedBlock)) {
				DyeableBlock dyeableBlock = getDyeableBlock(hittedBlock);
				if(dyeableBlock.vanillaBlock) {
					world.setBlockState(hitResult.getBlockPos(), dyeableBlock.resultBlock.getDefaultState());
				}
				else {
					world.setBlockState(hitResult.getBlockPos(), Blocks.STONE.getDefaultState());
				}

				return ActionResult.SUCCESS;
			}
			else {
				return ActionResult.PASS;
			}
		});
	}
}
