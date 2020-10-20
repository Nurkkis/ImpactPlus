//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.module.modules.misc;

import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.command.Command;
import me.axua.impactplus.event.events.GuiScreenDisplayedEvent;
import me.axua.impactplus.gui.settings.Setting;
import me.axua.impactplus.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;

public class AutoRespawn extends Module {
  Setting coords;
  
  @EventHandler
  private Listener<GuiScreenDisplayedEvent> listener;
  
  public AutoRespawn() {
    super("AutoRespawn", Module.Category.MISC, "Respawn when you die");
    this.listener = new Listener(event -> {
          if (event.getScreen() instanceof net.minecraft.client.gui.GuiGameOver) {
            if (this.coords.getValBoolean())
              Command.sendClientMessage(String.format("You died at x%d y%d z%d", new Object[] { Integer.valueOf((int)mc.player.posX), Integer.valueOf((int)mc.player.posY), Integer.valueOf((int)mc.player.posZ) })); 
            if (mc.player != null)
              mc.player.respawnPlayer(); 
            mc.displayGuiScreen(null);
          } 
        }new java.util.function.Predicate[0]);
  }
  
  public void setup() {
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.coords = new Setting("DeathCoords", this, true, "AutoRespawnDeathCoords"));
  }
  
  public void onEnable() {
    ImpactPlus.EVENT_BUS.subscribe(this);
  }
  
  public void onDisable() {
    ImpactPlus.EVENT_BUS.unsubscribe(this);
  }
}
