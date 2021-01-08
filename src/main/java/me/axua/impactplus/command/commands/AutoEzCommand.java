package me.axua.impactplus.command.commands;

import me.axua.impactplus.command.Command;
import me.axua.impactplus.module.modules.chat.AutoEZ;

public class AutoEzCommand extends Command {
  public String[] getAlias() {
    return new String[] { "autoez" };
  }
  
  public String getSyntax() {
    return "autoez <add | del> <message> (use \"{name}\" for the player's name, use \"_\" for spaces)";
  }
  
  public void onCommand(String command, String[] args) throws Exception {
    String s = args[1].replace("_", " ");
    if (args[0].equalsIgnoreCase("add")) {
      if (!AutoEZ.getAutoEzMessages().contains(s)) {
        AutoEZ.addAutoEzMessage(s);
        Command.sendClientMessage("Added AutoEZ message: " + s);
      } else {
        Command.sendClientMessage("AutoEZ list doesn't contain " + s);
      } 
    } else if (args[0].equalsIgnoreCase("del") || args[0].equalsIgnoreCase("remove")) {
      AutoEZ.getAutoEzMessages().remove(s);
      Command.sendClientMessage("Removed AutoEZ message: " + s);
    } 
  }
}
