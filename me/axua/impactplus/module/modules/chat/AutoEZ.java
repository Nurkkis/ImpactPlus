//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.module.modules.chat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.event.events.PacketEvent;
import me.axua.impactplus.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class AutoEZ extends Module {
  public static AutoEZ INSTANCE;
  
  public AutoEZ() {
    super("AutoEZ", Module.Category.CHAT, "Sends a message in chat when you kill someone");
    this.targetedPlayers = null;
    this.index = -1;
    this.sendListener = new Listener(event -> {
          if (mc.player != null) {
            if (this.targetedPlayers == null)
              this.targetedPlayers = new ConcurrentHashMap<>(); 
            if (event.getPacket() instanceof CPacketUseEntity) {
              CPacketUseEntity cPacketUseEntity = (CPacketUseEntity)event.getPacket();
              if (cPacketUseEntity.getAction().equals(CPacketUseEntity.Action.ATTACK)) {
                Entity targetEntity = cPacketUseEntity.getEntityFromWorld((World)mc.world);
                if (targetEntity instanceof EntityPlayer)
                  addTargetedPlayer(targetEntity.getName()); 
              } 
            } 
          } 
        }new java.util.function.Predicate[0]);
    this.livingDeathEventListener = new Listener(event -> {
          if (mc.player != null) {
            if (this.targetedPlayers == null)
              this.targetedPlayers = new ConcurrentHashMap<>(); 
            EntityLivingBase entity = event.getEntityLiving();
            if (entity != null && entity instanceof EntityPlayer) {
              EntityPlayer player = (EntityPlayer)entity;
              if (player.getHealth() <= 0.0F) {
                String name = player.getName();
                if (shouldAnnounce(name))
                  doAnnounce(name); 
              } 
            } 
          } 
        }new java.util.function.Predicate[0]);
    INSTANCE = this;
  }
  
  static List<String> AutoEzMessages = new ArrayList<>();
  
  private ConcurrentHashMap targetedPlayers;
  
  int index;
  
  @EventHandler
  private Listener<PacketEvent.Send> sendListener;
  
  @EventHandler
  private Listener<LivingDeathEvent> livingDeathEventListener;
  
  public void onEnable() {
    this.targetedPlayers = new ConcurrentHashMap<>();
    ImpactPlus.EVENT_BUS.subscribe(this);
  }
  
  public void onDisable() {
    this.targetedPlayers = null;
    ImpactPlus.EVENT_BUS.unsubscribe(this);
  }
  
  public void onUpdate() {
    if (this.targetedPlayers == null)
      this.targetedPlayers = new ConcurrentHashMap<>(); 
    Iterator<Entity> var1 = mc.world.getLoadedEntityList().iterator();
    while (var1.hasNext()) {
      Entity entity = var1.next();
      if (entity instanceof EntityPlayer) {
        EntityPlayer player = (EntityPlayer)entity;
        if (player.getHealth() <= 0.0F) {
          String name = player.getName();
          if (shouldAnnounce(name)) {
            doAnnounce(name);
            break;
          } 
        } 
      } 
    } 
    this.targetedPlayers.forEach((namex, timeout) -> {
          if (((Integer)timeout).intValue() <= 0) {
            this.targetedPlayers.remove(namex);
          } else {
            this.targetedPlayers.put(namex, Integer.valueOf(((Integer)timeout).intValue() - 1));
          } 
        });
  }
  
  private boolean shouldAnnounce(String name) {
    return this.targetedPlayers.containsKey(name);
  }
  
  private void doAnnounce(String name) {
    String message;
    this.targetedPlayers.remove(name);
    if (this.index >= AutoEzMessages.size() - 1)
      this.index = -1; 
    this.index++;
    if (AutoEzMessages.size() > 0) {
      message = AutoEzMessages.get(this.index);
    } else {
      message = "Ez";
    } 
    String messageSanitized = message.replaceAll("à¸¢à¸‡", "").replace("{name}", name);
    if (messageSanitized.length() > 255)
      messageSanitized = messageSanitized.substring(0, 255); 
    mc.player.connection.sendPacket((Packet)new CPacketChatMessage(messageSanitized));
  }
  
  public void addTargetedPlayer(String name) {
    if (!Objects.equals(name, mc.player.getName())) {
      if (this.targetedPlayers == null)
        this.targetedPlayers = new ConcurrentHashMap<>(); 
      this.targetedPlayers.put(name, Integer.valueOf(20));
    } 
  }
  
  public static void addAutoEzMessage(String s) {
    AutoEzMessages.add(s);
  }
  
  public static List<String> getAutoEzMessages() {
    return AutoEzMessages;
  }
}
