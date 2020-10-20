//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.command.commands;

import me.axua.impactplus.command.Command;
import me.axua.impactplus.command.CommandManager;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.event.HoverEvent;

public class CmdsCommand extends Command {
  public String[] getAlias() {
    return new String[] { "commands", "cmds" };
  }
  
  public String getSyntax() {
    return "commands";
  }
  
  public void onCommand(String command, String[] args) throws Exception {
    int size = CommandManager.getCommands().size();
    TextComponentString msg = new TextComponentString("§7Commands: ");
    for (int i = 0; i < size; i++) {
      Command c = CommandManager.getCommands().get(i);
      if (c != null)
        msg.appendSibling((new TextComponentString(c.getAlias()[0] + ((i == size - 1) ? "" : ", ")))
            .setStyle((new Style())
              .setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (ITextComponent)new TextComponentString(c.getSyntax()))))); 
    } 
    (Minecraft.getMinecraft()).ingameGUI.getChatGUI().printChatMessage((ITextComponent)msg);
  }
}
