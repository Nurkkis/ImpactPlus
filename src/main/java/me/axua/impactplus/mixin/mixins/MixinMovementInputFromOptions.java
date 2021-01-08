//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.mixin.mixins;

import me.axua.impactplus.module.ModuleManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.MovementInput;
import net.minecraft.util.MovementInputFromOptions;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = {MovementInputFromOptions.class}, priority = 10000)
public abstract class MixinMovementInputFromOptions extends MovementInput {
  @Redirect(method = {"updatePlayerMoveState"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/settings/KeyBinding;isKeyDown()Z"))
  public boolean isKeyPressed(KeyBinding keyBinding) {
    if (ModuleManager.isModuleEnabled("GuiMove") && 
      (Minecraft.getMinecraft()).currentScreen != null && 
      !((Minecraft.getMinecraft()).currentScreen instanceof net.minecraft.client.gui.GuiChat) && 
      (Minecraft.getMinecraft()).player != null)
      return Keyboard.isKeyDown(keyBinding.getKeyCode()); 
    return keyBinding.isKeyDown();
  }
}
