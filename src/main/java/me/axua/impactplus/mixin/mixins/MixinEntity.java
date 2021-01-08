//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.mixin.mixins;

import me.axua.impactplus.module.ModuleManager;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({Entity.class})
public class MixinEntity {
  @Redirect(method = {"applyEntityCollision"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;addVelocity(DDD)V"))
  public void velocity(Entity entity, double x, double y, double z) {
    if (!ModuleManager.isModuleEnabled("NoPush")) {
      entity.motionX += x;
      entity.motionY += y;
      entity.motionZ += z;
      entity.isAirBorne = true;
    } 
  }
}
