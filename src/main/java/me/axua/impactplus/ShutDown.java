package me.axua.impactplus;

public class ShutDown extends Thread {
  public void run() {
    saveConfig();
  }
  
  public static void saveConfig() {
    (ImpactPlus.getInstance()).configUtils.saveMods();
    (ImpactPlus.getInstance()).configUtils.saveSettingsList();
    (ImpactPlus.getInstance()).configUtils.saveBinds();
    (ImpactPlus.getInstance()).configUtils.saveDrawn();
    (ImpactPlus.getInstance()).configUtils.saveFriends();
    (ImpactPlus.getInstance()).configUtils.saveGui();
    (ImpactPlus.getInstance()).configUtils.savePrefix();
    (ImpactPlus.getInstance()).configUtils.saveRainbow();
    (ImpactPlus.getInstance()).configUtils.saveMacros();
    (ImpactPlus.getInstance()).configUtils.saveMsgs();
    (ImpactPlus.getInstance()).configUtils.saveAutoEZ();
    (ImpactPlus.getInstance()).configUtils.saveAutoReply();
    (ImpactPlus.getInstance()).configUtils.saveHudComponents();
    (ImpactPlus.getInstance()).configUtils.saveFont();
    (ImpactPlus.getInstance()).configUtils.saveClientname();
  }
}
