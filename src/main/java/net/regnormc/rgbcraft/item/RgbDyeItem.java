package net.regnormc.rgbcraft.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.regnormc.rgbcraft.colorname.ColorNames;

public class RgbDyeItem extends Item implements RgbCraftDyeableItem {
	public RgbDyeItem(Settings settings) {
		super(settings);
	}

	@Override
	public Text getName(ItemStack stack) {
		if (!hasColor(stack))
			return new TranslatableText("item.rgb-craft.rgb_dye.empty");
		else if (ColorNames.isLoaded())
			return new TranslatableText("item.rgb-craft.rgb_dye.colored", ColorNames.getNearestName(getColor(stack)));
		else
			return super.getName(stack);
	}
}
