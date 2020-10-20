//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.module.modules.movement;

import me.axua.impactplus.module.Module;
import net.minecraft.client.Minecraft;

public class AutoWalk extends Module {
  public AutoWalk() {
    super("AutoWalk", Module.Category.MOVEMENT);
  }
  
  public void onUpdate() {
    if (!(Minecraft.getMinecraft()).gameSettings.keyBindForward.isPressed())
      (Minecraft.getMinecraft()).gameSettings.keyBindForward.pressed = true; 
    (Minecraft.getMinecraft()).gameSettings.keyBindForward.pressed = true;
  }
  
  public void onDisable() {
    (Minecraft.getMinecraft()).gameSettings.keyBindForward.pressed = false;
  }
}
