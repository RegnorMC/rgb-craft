package net.regnormc.rgbcraft.model;

import net.fabricmc.fabric.api.resource.SimpleResourceReloadListener;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import net.regnormc.rgbcraft.RgbCraft;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class RgbModels implements SimpleResourceReloadListener {
	static Map<BlockState, BakedModel> stainedGlassPaneModels = new HashMap<>();

	public static void initialize() {
	}

	public static void refresh() {
		List<BlockState> states = Blocks.BLACK_STAINED_GLASS_PANE.getStateManager().getStates();
		for(BlockState state : states) {
			stainedGlassPaneModels.put(state, MinecraftClient.getInstance().getBakedModelManager().getBlockModels().getModel(state));
		}
	}

	@Override
	public CompletableFuture load(ResourceManager manager, Profiler profiler, Executor executor) {
		refresh();
		return null;
	}

	@Override
	public CompletableFuture<Void> apply(Object data, ResourceManager manager, Profiler profiler, Executor executor) {
		refresh();
		return null;
	}

	@Override
	public Identifier getFabricId() {
		return RgbCraft.id("rgb_models");
	}
}
