package me.axua.impactplus.command.commands;

import java.awt.Desktop;
import java.io.File;
import me.axua.impactplus.command.Command;

public class OpenFolderCommand extends Command {
  public String[] getAlias() {
    return new String[] { "openfolder", "folder" };
  }
  
  public String getSyntax() {
    return "openfolder";
  }
  
  public void onCommand(String command, String[] args) throws Exception {
    try {
      Desktop.getDesktop().open(new File("ImpactPlus"));
    } catch (Exception e) {
      sendClientMessage("Error: " + e.getMessage());
    } 
  }
}
