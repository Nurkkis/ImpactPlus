//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.mixin.mixins;

import java.util.UUID;
import javax.annotation.Nullable;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.module.ModuleManager;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({AbstractClientPlayer.class})
public abstract class MixinAbstractClientPlayer {
  @Shadow
  @Nullable
  protected abstract NetworkPlayerInfo getPlayerInfo();
  
  @Inject(method = {"getLocationCape"}, at = {@At("HEAD")}, cancellable = true)
  public void getLocationCape(CallbackInfoReturnable<ResourceLocation> cir) {
    UUID uuid = getPlayerInfo().getGameProfile().getId();
    if (ModuleManager.isModuleEnabled("Capes") && (ImpactPlus.getInstance()).capeUtils.hasCape(uuid))
      cir.setReturnValue(new ResourceLocation("osiris:textures/cape.png")); 
  }
}
