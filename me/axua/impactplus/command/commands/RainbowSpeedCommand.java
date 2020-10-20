package me.axua.impactplus.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.axua.impactplus.command.Command;
import me.axua.impactplus.event.EventProcessor;

public class RainbowSpeedCommand extends Command {
  public String[] getAlias() {
    return new String[] { "rainbowspeed", "rainbow" };
  }
  
  public String getSyntax() {
    return "rainbowspeed <speed>";
  }
  
  public void onCommand(String command, String[] args) throws Exception {
    if (args.length == 1) {
      int i = Integer.parseInt(args[0]);
      if (i <= 0) {
        EventProcessor.INSTANCE.setRainbowSpeed(0);
      } else {
        EventProcessor.INSTANCE.setRainbowSpeed(i);
      } 
      Command.sendClientMessage("Rainbow speed set to " + i);
    } else {
      Command.sendClientMessage(ChatFormatting.RED + getSyntax());
    } 
  }
}
