package net.regnormc.rgbcraft.config;

public class RgbCraftConfiguration {
	private final boolean onlyBestColorNames;

	public RgbCraftConfiguration(boolean onlyBestColorNames) {
		this.onlyBestColorNames = onlyBestColorNames;
	}

	public boolean isOnlyBestColorNames() {
		return onlyBestColorNames;
	}
}
