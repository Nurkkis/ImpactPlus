package me.axua.impactplus.command.commands;

import me.axua.impactplus.command.Command;
import me.axua.impactplus.module.modules.chat.AutoReply;

public class AutoReplyCommand extends Command {
  public String[] getAlias() {
    return new String[] { "autoreply" };
  }
  
  public String getSyntax() {
    return "autoreply <message (use \"_\" for spaces)>";
  }
  
  public void onCommand(String command, String[] args) throws Exception {
    if (args[0] != null && !args[0].equalsIgnoreCase("")) {
      AutoReply.setReply(args[0].replace("_", " "));
      Command.sendClientMessage("AutoReply message set to " + AutoReply.getReply());
    } else {
      Command.sendClientMessage(getSyntax());
    } 
  }
}
