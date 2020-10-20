//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.module.modules.chat;

import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraftforge.client.event.ClientChatReceivedEvent;

public class AutoReply extends Module {
  public AutoReply() {
    super("AutoReply", Module.Category.CHAT, "Reply to whispers");
    this.listener = new Listener(event -> {
          if (event.getMessage().getUnformattedText().contains("whispers: ") && !event.getMessage().getUnformattedText().startsWith(mc.player.getName()))
            mc.player.sendChatMessage("/r " + reply); 
        }new java.util.function.Predicate[0]);
  }
  
  private static String reply = "fuck off";
  
  @EventHandler
  private Listener<ClientChatReceivedEvent> listener;
  
  public static String getReply() {
    return reply;
  }
  
  public static void setReply(String r) {
    reply = r;
  }
  
  public void onEnable() {
    ImpactPlus.EVENT_BUS.subscribe(this);
  }
  
  public void onDisable() {
    ImpactPlus.EVENT_BUS.unsubscribe(this);
  }
}
