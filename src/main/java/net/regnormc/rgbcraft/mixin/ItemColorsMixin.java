package net.regnormc.rgbcraft.mixin;

import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.item.DyeableItem;
import net.regnormc.rgbcraft.item.RgbCraftItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ItemColors.class)
public abstract class ItemColorsMixin {

	@Inject(
			method = "create",
			at = @At("RETURN"),
			locals = LocalCapture.CAPTURE_FAILHARD
	)
	private static void addRgbItems(BlockColors blockColors, CallbackInfoReturnable<ItemColors> cir, ItemColors itemColors) {
		itemColors.register((stack, tintIndex) -> tintIndex > 0 ? -1 : ((DyeableItem)stack.getItem()).getColor(stack), RgbCraftItems.RGB_DYE);
	}
}
