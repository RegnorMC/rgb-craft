package net.regnormc.rgbcraft.model;

import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;

public class GlassRgbModel extends FullBlockRgbModel {
	public GlassRgbModel(String textureID) {
		super(textureID);
	}

	@Override
	public void generateModel(RenderContext context, int color) {
		Long color_unsigned = (long)color;
		color_unsigned += 4278190080L;

		int color_2 = color_unsigned.intValue();

		super.generateModel(context, color_2);
	}
}
