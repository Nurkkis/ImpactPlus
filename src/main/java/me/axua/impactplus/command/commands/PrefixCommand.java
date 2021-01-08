package me.axua.impactplus.command.commands;

import me.axua.impactplus.command.Command;

public class PrefixCommand extends Command {
  public String[] getAlias() {
    return new String[] { "prefix", "setprefix" };
  }
  
  public String getSyntax() {
    return "prefix <character>";
  }
  
  public void onCommand(String command, String[] args) throws Exception {
    Command.setPrefix(args[0]);
    Command.sendClientMessage("Command prefix set to " + Command.getPrefix());
  }
}
