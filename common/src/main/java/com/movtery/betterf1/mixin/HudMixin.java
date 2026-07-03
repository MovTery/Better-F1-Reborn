package com.movtery.betterf1.mixin;

import com.movtery.betterf1.BetterF1;
import com.movtery.betterf1.client.HUDState;
import net.minecraft.client.gui.Hud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Hud.class)
public class HudMixin {
    @Shadow
    private boolean isHidden;

    @Inject(method = "toggle", at = @At("HEAD"), cancellable = true)
    private void hadleHidden(CallbackInfo ci) {
        BetterF1.state = BetterF1.state.next();
        this.isHidden = !BetterF1.state.equals(HUDState.ALL_VISIBLE);
        ci.cancel();
    }
}
