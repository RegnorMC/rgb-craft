package net.regnormc.rgbcraft.colorname;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonStreamParser;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3i;
import net.regnormc.rgbcraft.RgbCraft;

import java.awt.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class ColorNames {
	public static final int RGB_COLORS_AMOUNT = (int) Math.pow(256, 3);

	private static final Map<Integer, String> names = new HashMap<>();
	private static final Map<Integer, String> nearestNameCache = new HashMap<>();

	public static String getNearestName(int color) {
		if (!nearestNameCache.containsKey(color)) {
			if (names.containsKey(color)) {
				nearestNameCache.put(color, names.get(color));
			} else {
				Vec3i vec = getColorVector(color);
				int nearestColor = 0xffffff;
				double nearestColorDistance = 256.0d * Math.sqrt(3) + 1.0d; // We set it to the max possible distance + 1
				for (Integer namedColor : names.keySet()) {
					Vec3i namedVec = getColorVector(namedColor);
					double namedColorDistance = vec.getSquaredDistance(namedVec);
					if (namedColorDistance < nearestColorDistance) {
						nearestColor = namedColor;
						nearestColorDistance = namedColorDistance;
					}
				}
				nearestNameCache.put(color, names.get(nearestColor));
			}
		}
		return nearestNameCache.get(color);
	}

	public static void loadNames() {
		Identifier namesResourceId = RgbCraft.id(!RgbCraft.getConfiguration().isOnlyBestColorNames() ? "color_names/colornames.min.json" : "color_names/colornames.bestof.min.json");
		try {
			Resource namesResource = MinecraftClient.getInstance().getResourceManager().getResource(namesResourceId);
			JsonStreamParser jsonStreamParser = new JsonStreamParser(new InputStreamReader(namesResource.getInputStream()));
			JsonObject colorsJson = jsonStreamParser.next().getAsJsonObject();
			clearNearestNameCache();
			names.clear();
			for (Map.Entry<String, JsonElement> nameEntry : colorsJson.entrySet()) {
				try {
					names.put(Integer.parseInt(nameEntry.getKey(), 16), nameEntry.getValue().getAsString());
				} catch (IllegalStateException | ClassCastException exception) {
					RgbCraft.LOGGER.warn("Could not load a color name from the JSON file '{}'", namesResourceId.toString(), exception);
				}
			}
			if (names.size() > 1)
				RgbCraft.LOGGER.info("Loaded {} color names (about {}% of all the colors in RGB space)", names.size(), (double) Math.round(((double) names.size() / (double) RGB_COLORS_AMOUNT) * 100d * 1000) / 1000);
			else
				RgbCraft.LOGGER.error("The color file '{}' has no valid colors", namesResourceId.toString());
		} catch (IllegalStateException | IOException exception) {
			RgbCraft.LOGGER.error("Could not load the color names JSON file '{}'!", namesResourceId.toString(), exception);
		}
	}

	public static void clearNearestNameCache() {
		nearestNameCache.clear();
	}

	public static Vec3i getColorVector(int color) {
		Color convertedColor = new Color(color);
		return new Vec3i(convertedColor.getRed(), convertedColor.getGreen(), convertedColor.getBlue());
	}

	public static boolean isLoaded() {
		return !names.isEmpty();
	}

	public static class ReloadListener implements SimpleSynchronousResourceReloadListener {
		@Override
		public Identifier getFabricId() {
			return RgbCraft.id("color_names");
		}

		@Override
		public void reload(ResourceManager manager) {
			loadNames();
		}
	}
}
