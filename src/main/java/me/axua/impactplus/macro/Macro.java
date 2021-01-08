//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.macro;

import net.minecraft.client.Minecraft;

public class Macro {
  int key;
  
  String value;
  
  public Macro(int k, String v) {
    this.key = k;
    this.value = v;
  }
  
  public void onMacro() {
    if ((Minecraft.getMinecraft()).player != null)
      (Minecraft.getMinecraft()).player.sendChatMessage(this.value); 
  }
  
  public int getKey() {
    return this.key;
  }
  
  public String getValue() {
    return this.value;
  }
}
