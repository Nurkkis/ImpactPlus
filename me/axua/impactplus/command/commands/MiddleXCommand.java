//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.command.commands;

import me.axua.impactplus.command.Command;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

public class MiddleXCommand extends Command {
  public String[] getAlias() {
    return new String[] { "getmiddlex", "middlex", "getmiddle" };
  }
  
  public String getSyntax() {
    return getAlias()[0];
  }
  
  public void onCommand(String command, String[] args) throws Exception {
    Command.sendClientMessage(((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth() / 2) + "");
  }
}
