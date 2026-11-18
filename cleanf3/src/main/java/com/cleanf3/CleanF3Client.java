package com.cleanf3;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

public class CleanF3Client implements ClientModInitializer {
	private static final Logger LOGGER = LoggerFactory.getLogger("cleanf3");

	@Override
	public void onInitializeClient() {
		// Diagnostic only: lists the overlay methods that take a list of lines, so if the
		// chart hints ever come back after a Minecraft update, latest.log shows what to hook.
		try {
			Class<?> overlay = Class.forName("net.minecraft.client.gui.components.DebugScreenOverlay");
			for (Method m : overlay.getDeclaredMethods()) {
				for (Class<?> p : m.getParameterTypes()) {
					if (java.util.List.class.isAssignableFrom(p)) {
						LOGGER.info("DebugScreenOverlay method taking a List: {}", m);
						break;
					}
				}
			}
		} catch (ClassNotFoundException | RuntimeException e) {
			LOGGER.warn("Could not inspect DebugScreenOverlay: {}", e.toString());
		}
	}
}
