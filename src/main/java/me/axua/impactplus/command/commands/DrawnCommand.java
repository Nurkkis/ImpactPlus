package me.axua.impactplus.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.axua.impactplus.command.Command;
import me.axua.impactplus.module.Module;
import me.axua.impactplus.module.ModuleManager;

public class DrawnCommand extends Command {
  boolean found;
  
  public String[] getAlias() {
    return new String[] { "drawn", "visible", "d" };
  }
  
  public String getSyntax() {
    return "drawn <Module>";
  }
  
  public void onCommand(String command, String[] args) throws Exception {
    this.found = false;
    ModuleManager.getModules().forEach(m -> {
          if (m.getName().equalsIgnoreCase(args[0]))
            if (m.isDrawn()) {
              m.setDrawn(false);
              this.found = true;
              Command.sendClientMessage(m.getName() + ChatFormatting.RED + " drawn = false");
            } else if (!m.isDrawn()) {
              m.setDrawn(true);
              this.found = true;
              Command.sendClientMessage(m.getName() + ChatFormatting.GREEN + " drawn = true");
            }  
        });
    if (!this.found && args.length == 1)
      Command.sendClientMessage(ChatFormatting.DARK_RED + "Module not found!"); 
  }
}
