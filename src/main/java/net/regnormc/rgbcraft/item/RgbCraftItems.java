package net.regnormc.rgbcraft.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.regnormc.rgbcraft.RgbCraft;

public class RgbCraftItems {
	public static RgbDyeItem RGB_DYE = register(RgbCraft.id("rgb_dye"), new RgbDyeItem(new FabricItemSettings().group(ItemGroup.MATERIALS)));

	private static <T extends Item> T register(Identifier id, T item) {
		return Registry.register(Registry.ITEM, id, item);
	}

	/**
	 * Used to initialize the class.
	 */
	public static void initialize() {
	}
}
