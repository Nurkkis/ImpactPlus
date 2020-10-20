//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.module.modules.chat;

import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.command.Command;
import me.axua.impactplus.event.events.PlayerJoinEvent;
import me.axua.impactplus.event.events.PlayerLeaveEvent;
import me.axua.impactplus.gui.settings.Setting;
import me.axua.impactplus.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;

public class Welcomer extends Module {
  Setting publicS;
  
  @EventHandler
  private Listener<PlayerJoinEvent> listener1;
  
  @EventHandler
  private Listener<PlayerLeaveEvent> listener2;
  
  public Welcomer() {
    super("Welcomer", Module.Category.CHAT, "Sends a message when someone joins the server");
    this.listener1 = new Listener(event -> {
          if (this.publicS.getValBoolean()) {
            mc.player.sendChatMessage(event.getName() + " joined the game");
          } else {
            Command.sendClientMessage(event.getName() + " joined the game");
          } 
        }new java.util.function.Predicate[0]);
    this.listener2 = new Listener(event -> {
          if (this.publicS.getValBoolean()) {
            mc.player.sendChatMessage(event.getName() + " left the game");
          } else {
            Command.sendClientMessage(event.getName() + " left the game");
          } 
        }new java.util.function.Predicate[0]);
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.publicS = new Setting("Public", this, false, "WelcomerPublicMode"));
  }
  
  public void onEnable() {
    ImpactPlus.EVENT_BUS.subscribe(this);
  }
  
  public void onDisable() {
    ImpactPlus.EVENT_BUS.unsubscribe(this);
  }
}
