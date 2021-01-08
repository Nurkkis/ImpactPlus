//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.command;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public abstract class Command {
  static Minecraft mc = Minecraft.getMinecraft();
  
  public static String prefix = "-";
  
  public static boolean MsgWaterMark = true;
  
  public static ChatFormatting cf = ChatFormatting.WHITE;
  
  public abstract String[] getAlias();
  
  public abstract String getSyntax();
  
  public abstract void onCommand(String paramString, String[] paramArrayOfString) throws Exception;
  
  public static void sendClientMessage(String message) {
    if (MsgWaterMark) {
      mc.player.sendMessage((ITextComponent)new TextComponentString("§f[§9Impact+§f] " + cf + message));
    } else {
      mc.player.sendMessage((ITextComponent)new TextComponentString(cf + message));
    } 
  }
  
  public static Color getColorFromChatFormatting(ChatFormatting cf) {
    if (cf == ChatFormatting.BLACK)
      return Color.BLACK; 
    if (cf == ChatFormatting.GRAY)
      return Color.GRAY; 
    if (cf == ChatFormatting.AQUA)
      return Color.CYAN; 
    if (cf == ChatFormatting.BLUE || cf == ChatFormatting.DARK_BLUE || cf == ChatFormatting.DARK_AQUA)
      return Color.BLUE; 
    if (cf == ChatFormatting.DARK_GRAY)
      return Color.DARK_GRAY; 
    if (cf == ChatFormatting.DARK_GREEN || cf == ChatFormatting.GREEN)
      return Color.GREEN; 
    if (cf == ChatFormatting.DARK_PURPLE)
      return Color.MAGENTA; 
    if (cf == ChatFormatting.RED || cf == ChatFormatting.DARK_RED)
      return Color.RED; 
    if (cf == ChatFormatting.LIGHT_PURPLE)
      return Color.PINK; 
    if (cf == ChatFormatting.YELLOW)
      return Color.YELLOW; 
    if (cf == ChatFormatting.GOLD)
      return Color.ORANGE; 
    return Color.WHITE;
  }
  
  public static void sendRawMessage(String message) {
    mc.player.sendMessage((ITextComponent)new TextComponentString(message));
  }
  
  public static String getPrefix() {
    return prefix;
  }
  
  public static void setPrefix(String p) {
    prefix = p;
  }
}
