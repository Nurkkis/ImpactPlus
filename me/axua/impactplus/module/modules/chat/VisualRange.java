//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.module.modules.chat;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.awt.TrayIcon;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.command.Command;
import me.axua.impactplus.friends.Friends;
import me.axua.impactplus.gui.settings.Setting;
import me.axua.impactplus.module.Module;
import me.axua.impactplus.module.ModuleManager;
import me.axua.impactplus.module.modules.misc.Notifications;
import net.minecraft.entity.Entity;

public class VisualRange extends Module {
  List<Entity> knownPlayers;
  
  List<Entity> players;
  
  public Setting colours;
  
  public VisualRange() {
    super("VisualRange", Module.Category.CHAT, "Sends a client side message when someone enters your render distance");
    this.knownPlayers = new ArrayList<>();
  }
  
  public void setup() {
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.colours = new Setting("Colours", this, true, "VisualRangeColours"));
  }
  
  public void onUpdate() {
    if (mc.player == null)
      return; 
    this.players = (List<Entity>)mc.world.loadedEntityList.stream().filter(e -> e instanceof net.minecraft.entity.player.EntityPlayer).collect(Collectors.toList());
    try {
      for (Entity e : this.players) {
        if (e instanceof net.minecraft.entity.player.EntityPlayer && !e.getName().equalsIgnoreCase(mc.player.getName()) && 
          !this.knownPlayers.contains(e)) {
          this.knownPlayers.add(e);
          if (this.colours.getValBoolean()) {
            Command.sendClientMessage(e.getName() + ChatFormatting.GREEN + " entered visual range.");
          } else {
            Command.sendClientMessage(e.getName() + " entered visual range.");
          } 
          if (ModuleManager.isModuleEnabled("Notifications") && 
            !Friends.isFriend(e.getName()))
            Notifications.sendNotification(e.getName() + " entered visual range.", TrayIcon.MessageType.INFO); 
        } 
      } 
    } catch (Exception exception) {}
    try {
      for (Entity e : this.knownPlayers) {
        if (e instanceof net.minecraft.entity.player.EntityPlayer && !e.getName().equalsIgnoreCase(mc.player.getName()) && 
          !this.players.contains(e))
          this.knownPlayers.remove(e); 
      } 
    } catch (Exception exception) {}
  }
  
  public void onDisable() {
    this.knownPlayers.clear();
  }
}
