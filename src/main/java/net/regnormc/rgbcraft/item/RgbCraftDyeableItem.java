package net.regnormc.rgbcraft.item;

import net.minecraft.item.DyeableItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public interface RgbCraftDyeableItem extends DyeableItem {
	@Override
	default int getColor(ItemStack stack) {
		NbtCompound nbtCompound = stack.getSubTag("display");
		return nbtCompound != null && nbtCompound.contains("color", 99) ? nbtCompound.getInt("color") : getDefaultColor();
	}

	default int getDefaultColor() {
		return 0xFFFFFF;
	}
}
