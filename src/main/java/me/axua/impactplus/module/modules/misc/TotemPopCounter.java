//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.module.modules.misc;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.ArrayList;
import java.util.HashMap;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.command.Command;
import me.axua.impactplus.event.events.EntityUseTotemEvent;
import me.axua.impactplus.event.events.PacketEvent;
import me.axua.impactplus.gui.settings.Setting;
import me.axua.impactplus.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.network.play.server.SPacketEntityStatus;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class TotemPopCounter extends Module {
  Setting countFriends;
  
  Setting countSelf;
  
  Setting resetDeaths;
  
  Setting resetSelfDeaths;
  
  Setting Announce;
  
  Setting thanksTo;
  
  private HashMap<String, Integer> playerList;
  
  private boolean isDead;
  
  @EventHandler
  public Listener<EntityUseTotemEvent> listListener;
  
  @EventHandler
  public Listener<PacketEvent.Receive> popListener;
  
  public TotemPopCounter() {
    super("TotemPopCounter", Module.Category.MISC, "Fixed!");
    this.playerList = new HashMap<>();
    this.isDead = false;
    this.listListener = new Listener(event -> {
          if (this.playerList == null)
            this.playerList = new HashMap<>(); 
          if (this.playerList.get(event.getEntity().getName()) == null) {
            this.playerList.put(event.getEntity().getName(), Integer.valueOf(1));
            sendMessage(formatName2(event.getEntity().getName()) + " popped " + formatNumber(1) + " totem" + ending());
          } else if (this.playerList.get(event.getEntity().getName()) != null) {
            int popCounter = ((Integer)this.playerList.get(event.getEntity().getName())).intValue();
            this.playerList.put(event.getEntity().getName(), Integer.valueOf(++popCounter));
            sendMessage(formatName2(event.getEntity().getName()) + " popped " + formatNumber(popCounter) + " totems" + ending());
          } 
        }new java.util.function.Predicate[0]);
    this.popListener = new Listener(event -> {
          if (mc.player == null)
            return; 
          if (event.getPacket() instanceof SPacketEntityStatus) {
            SPacketEntityStatus packet = (SPacketEntityStatus)event.getPacket();
            if (packet.getOpCode() == 35) {
              Entity entity = packet.getEntity((World)mc.world);
              if (selfCheck(entity.getName()))
                ImpactPlus.EVENT_BUS.post(new EntityUseTotemEvent(entity)); 
            } 
          } 
        }new java.util.function.Predicate[0]);
  }
  
  public void setup() {
    ArrayList<String> modes = new ArrayList<>();
    modes.add("Client");
    modes.add("Public");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.countSelf = new Setting("Count Self", this, false, "TotemPopCounterCountSelf"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.resetSelfDeaths = new Setting("Reset Self Death", this, false, "TotemPopCounterResetSelfDeaths"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.resetDeaths = new Setting("Reset On Death", this, true, "TotemPopCounterResetDeaths"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.Announce = new Setting("Announce", this, "Client", modes, "TotemPopCounterAnnounce"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.thanksTo = new Setting("Thanks To", this, false, "TotemPopCounterThanksTo"));
  }
  
  public void onUpdate() {
    if (!this.isDead && this.resetSelfDeaths.getValBoolean() && 0.0F >= mc.player.getHealth()) {
      sendMessage(formatName(mc.player.getName()) + " died and " + grammar(mc.player.getName()) + " pop list was reset");
      this.isDead = true;
      this.playerList.clear();
      return;
    } 
    if (this.isDead && 0.0F < mc.player.getHealth())
      this.isDead = false; 
    for (EntityPlayer player : mc.world.playerEntities) {
      if (this.resetDeaths.getValBoolean() && 0.0F >= player.getHealth() && selfCheck(player.getName()) && this.playerList.containsKey(player.getName())) {
        sendMessage(formatName(player.getName()) + " died after popping " + formatNumber(((Integer)this.playerList.get(player.getName())).intValue()) + " totems" + ending());
        this.playerList.remove(player.getName(), this.playerList.get(player.getName()));
      } 
    } 
  }
  
  private boolean selfCheck(String name) {
    if (this.isDead)
      return false; 
    if (this.countSelf.getValBoolean() && name.equalsIgnoreCase(mc.player.getName()))
      return true; 
    if (!this.countSelf.getValBoolean() && name.equalsIgnoreCase(mc.player.getName()))
      return false; 
    return true;
  }
  
  private boolean isSelf(String name) {
    return name.equalsIgnoreCase(mc.player.getName());
  }
  
  private String formatName(String name) {
    String extraText = "";
    if (isSelf(name)) {
      extraText = "";
      name = "I";
    } 
    if (this.Announce.getValString().equalsIgnoreCase("Public"))
      return extraText + name; 
    return extraText + ChatFormatting.RED + name + TextFormatting.RESET;
  }
  
  private String formatName2(String name) {
    String extraText = "";
    if (isSelf(name)) {
      extraText = "";
      name = "I";
    } 
    if (this.Announce.getValString().equalsIgnoreCase("Public"))
      return extraText + name; 
    return extraText + ChatFormatting.GREEN + name + TextFormatting.RESET;
  }
  
  private String grammar(String name) {
    if (isSelf(name))
      return "my"; 
    return "their";
  }
  
  private String ending() {
    if (this.thanksTo.getValBoolean())
      return " thanks to " + ImpactPlus.MODNAME; 
    return "";
  }
  
  private boolean isPublic() {
    return this.Announce.getValString().equalsIgnoreCase("Public");
  }
  
  private String formatNumber(int message) {
    if (this.Announce.getValString().equalsIgnoreCase("Public"))
      return "" + message; 
    return ChatFormatting.RED + "" + message + TextFormatting.RESET;
  }
  
  private void sendMessage(String message) {
    if (this.Announce.getValString().equalsIgnoreCase("Public")) {
      mc.player.connection.sendPacket((Packet)new CPacketChatMessage(message));
    } else {
      Command.sendClientMessage(message);
    } 
  }
  
  public void onEnable() {
    ImpactPlus.EVENT_BUS.subscribe(this);
  }
  
  public void onDisable() {
    ImpactPlus.EVENT_BUS.unsubscribe(this);
  }
}
