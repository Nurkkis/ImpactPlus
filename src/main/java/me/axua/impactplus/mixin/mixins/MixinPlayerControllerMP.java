package me.axua.impactplus.mixin.mixins;

import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.event.events.DestroyBlockEvent;
import me.axua.impactplus.module.ModuleManager;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({PlayerControllerMP.class})
public class MixinPlayerControllerMP {
  @Inject(method = {"onPlayerDestroyBlock"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/world/World;playEvent(ILnet/minecraft/util/math/BlockPos;I)V")}, cancellable = true)
  private void onPlayerDestroyBlock(BlockPos pos, CallbackInfoReturnable<Boolean> info) {
    ImpactPlus.EVENT_BUS.post(new DestroyBlockEvent(pos));
  }
  
  @Inject(method = {"resetBlockRemoving"}, at = {@At("HEAD")}, cancellable = true)
  private void resetBlock(CallbackInfo ci) {
    if (ModuleManager.isModuleEnabled("BreakTweaks"))
      ci.cancel(); 
  }
}
