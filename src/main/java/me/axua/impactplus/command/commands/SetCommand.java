package me.axua.impactplus.command.commands;

import java.awt.Color;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.command.Command;
import me.axua.impactplus.gui.settings.Setting;
import me.axua.impactplus.module.Module;
import me.axua.impactplus.module.ModuleManager;

public class SetCommand extends Command {
  public String[] getAlias() {
    return new String[] { "set" };
  }
  
  public String getSyntax() {
    return "set <Module> <Setting> <Value>";
  }
  
  public void onCommand(String command, String[] args) throws Exception {
    for (Module m : ModuleManager.getModules()) {
      if (m.getName().equalsIgnoreCase(args[0]))
        (ImpactPlus.getInstance()).settingsManager.getSettingsByMod(m).stream().filter(s -> s.getDisplayName().equalsIgnoreCase(args[1])).forEach(s -> {
              if (s.isSlider()) {
                if (Double.parseDouble(args[2]) > s.getMax())
                  s.setValDouble(s.getMax()); 
                if (Double.parseDouble(args[2]) < s.getMin())
                  s.setValDouble(s.getMin()); 
                if (Double.parseDouble(args[2]) <= s.getMax() && Double.parseDouble(args[2]) >= s.getMin())
                  s.setValDouble(Double.parseDouble(args[2])); 
                Command.sendClientMessage(s.getDisplayName() + " set to " + s.getValDouble());
              } 
              if (s.isCheck()) {
                s.setValBoolean(Boolean.parseBoolean(args[2]));
                Command.sendClientMessage(s.getDisplayName() + " set to " + s.getValBoolean());
              } 
              if (s.isCombo()) {
                if (!s.getOptions().contains(args[2]))
                  return; 
                s.setValString(args[2]);
                Command.sendClientMessage(s.getDisplayName() + " set to" + s.getValString());
              } 
              if (s.isColorPicker()) {
                s.setValColor(Color.getColor(args[2]));
                Command.sendClientMessage(s.getDisplayName() + " set to" + s.getValColor());
              } 
            }); 
    } 
  }
}
