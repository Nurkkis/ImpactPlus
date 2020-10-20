package me.axua.impactplus.command.commands;

import me.axua.impactplus.ShutDown;
import me.axua.impactplus.command.Command;

public class ConfigCommand extends Command {
  public String[] getAlias() {
    return new String[] { "saveconfig", "savecfg" };
  }
  
  public String getSyntax() {
    return "saveconfig";
  }
  
  public void onCommand(String command, String[] args) throws Exception {
    ShutDown.saveConfig();
    Command.sendClientMessage("Config saved");
  }
}
