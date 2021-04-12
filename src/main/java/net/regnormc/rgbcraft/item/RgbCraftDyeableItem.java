package net.regnormc.rgbcraft.item;

import net.minecraft.item.DyeableItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

import java.util.Objects;

public interface RgbCraftDyeableItem extends DyeableItem {
	@Override
	default int getColor(ItemStack stack) {
		return hasColor(stack) ? Objects.requireNonNull(stack.getSubTag("display"), "Nbt tag is null even though it passed the check").getInt("color") : getDefaultColor();
	}

	default boolean hasColor(ItemStack stack) {
		NbtCompound nbtCompound = stack.getSubTag("display");
		return nbtCompound != null && nbtCompound.contains("color", 99);
	}

	default int getDefaultColor() {
		return 0xFFFFFF;
	}
}
