//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.world.World;

public class Wrapper {
  public static Minecraft getMinecraft() {
    return Minecraft.getMinecraft();
  }
  
  public static EntityPlayerSP getPlayer() {
    return (getMinecraft()).player;
  }
  
  public static World getWorld() {
    return (World)(getMinecraft()).world;
  }
}
