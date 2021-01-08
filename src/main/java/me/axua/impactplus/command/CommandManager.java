package me.axua.impactplus.command;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.ArrayList;
import me.axua.impactplus.command.commands.AutoEzCommand;
import me.axua.impactplus.command.commands.AutoReplyCommand;
import me.axua.impactplus.command.commands.BindCommand;
import me.axua.impactplus.command.commands.CmdsCommand;
import me.axua.impactplus.command.commands.ConfigCommand;
import me.axua.impactplus.command.commands.DrawnCommand;
import me.axua.impactplus.command.commands.FriendCommand;
import me.axua.impactplus.command.commands.MiddleXCommand;
import me.axua.impactplus.command.commands.ModsCommand;
import me.axua.impactplus.command.commands.OpenFolderCommand;
import me.axua.impactplus.command.commands.PrefixCommand;
import me.axua.impactplus.command.commands.RainbowSpeedCommand;
import me.axua.impactplus.command.commands.ReloadConfigCommand;
import me.axua.impactplus.command.commands.SetCommand;
import me.axua.impactplus.command.commands.ToggleCommand;

public class CommandManager {
  private static ArrayList<Command> commands;
  
  boolean b;
  
  public static void initCommands() {
    commands = new ArrayList<>();
    addCommand((Command)new BindCommand());
    addCommand((Command)new ToggleCommand());
    addCommand((Command)new DrawnCommand());
    addCommand((Command)new SetCommand());
    addCommand((Command)new CmdsCommand());
    addCommand((Command)new ModsCommand());
    addCommand((Command)new PrefixCommand());
    addCommand((Command)new FriendCommand());
    addCommand((Command)new RainbowSpeedCommand());
    addCommand((Command)new ConfigCommand());
    addCommand((Command)new ReloadConfigCommand());
    addCommand((Command)new AutoEzCommand());
    addCommand((Command)new OpenFolderCommand());
    addCommand((Command)new AutoReplyCommand());
    addCommand((Command)new MiddleXCommand());
  }
  
  public static void addCommand(Command c) {
    commands.add(c);
  }
  
  public static ArrayList<Command> getCommands() {
    return commands;
  }
  
  public void callCommand(String input) {
    String[] split = input.split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
    String command = split[0];
    String args = input.substring(command.length()).trim();
    this.b = false;
    commands.forEach(c -> {
          for (String s : c.getAlias()) {
            if (s.equalsIgnoreCase(command)) {
              this.b = true;
              try {
                c.onCommand(args, args.split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"));
              } catch (Exception e) {
                Command.sendClientMessage(ChatFormatting.DARK_RED + c.getSyntax());
              } 
            } 
          } 
        });
    if (!this.b)
      Command.sendClientMessage(ChatFormatting.DARK_RED + "Unknown command!"); 
  }
}
