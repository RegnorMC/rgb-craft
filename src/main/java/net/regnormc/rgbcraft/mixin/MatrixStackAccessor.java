package net.regnormc.rgbcraft.mixin;

import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Deque;

@Mixin(MatrixStack.class)
public interface MatrixStackAccessor {
	@Accessor
	Deque<MatrixStack.Entry> getStack();

	@Accessor("stack")
	void setStack(Deque<MatrixStack.Entry> stack);
}
