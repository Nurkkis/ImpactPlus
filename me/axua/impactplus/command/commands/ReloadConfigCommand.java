package me.axua.impactplus.command.commands;

import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.command.Command;
import me.axua.impactplus.friends.Friends;
import me.axua.impactplus.gui.clickgui.ClickGUI;
import me.axua.impactplus.gui.settings.SettingsManager;
import me.axua.impactplus.module.ModuleManager;
import me.axua.impactplus.util.ConfigUtils;

public class ReloadConfigCommand extends Command {
  public SettingsManager settingsManager;
  
  public Friends friends;
  
  public ModuleManager moduleManager;
  
  public ClickGUI clickGui;
  
  public ConfigUtils configUtils;
  
  public String[] getAlias() {
    return new String[] { "reloadconfig", "reloadcfg" };
  }
  
  public String getSyntax() {
    return "reloadconfig";
  }
  
  public void onCommand(String command, String[] args) throws Exception {
    (ImpactPlus.getInstance()).configUtils.loadMods();
    (ImpactPlus.getInstance()).configUtils.loadSettingsList();
    (ImpactPlus.getInstance()).configUtils.loadBinds();
    (ImpactPlus.getInstance()).configUtils.loadDrawn();
    (ImpactPlus.getInstance()).configUtils.loadFriends();
    (ImpactPlus.getInstance()).configUtils.loadGui();
    (ImpactPlus.getInstance()).configUtils.loadPrefix();
    (ImpactPlus.getInstance()).configUtils.loadRainbow();
    (ImpactPlus.getInstance()).configUtils.loadMacros();
    (ImpactPlus.getInstance()).configUtils.loadMsgs();
    (ImpactPlus.getInstance()).configUtils.loadAutoEZ();
    (ImpactPlus.getInstance()).configUtils.loadAutoReply();
    (ImpactPlus.getInstance()).configUtils.loadHudComponents();
    (ImpactPlus.getInstance()).configUtils.loadFont();
    (ImpactPlus.getInstance()).configUtils.loadClientname();
    Command.sendClientMessage("Config reloaded");
  }
}
