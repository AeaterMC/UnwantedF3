package com.cleanf3.mixin;

import com.cleanf3.LineFilter;
import net.minecraft.client.gui.components.DebugScreenOverlay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.List;

@Mixin(DebugScreenOverlay.class)
public abstract class DebugScreenOverlayMixin {

	/**
	 * The overlay draws each column from a list of text lines. Swap that list for one without the
	 * chart key hints. The method was called renderLines before 26.1 and extractLines after, so
	 * every plausible name is listed; whichever exists gets hooked.
	 */
	@ModifyVariable(method = { "extractLines", "renderLines", "drawLines" }, at = @At("HEAD"), argsOnly = true, require = 0)
	private List<String> cleanf3$dropChartHints(List<String> lines) {
		return LineFilter.filter(lines);
	}
}
