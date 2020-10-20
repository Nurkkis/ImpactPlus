//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.module.modules.chat;

import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.friends.Friend;
import me.axua.impactplus.friends.Friends;
import me.axua.impactplus.gui.settings.Setting;
import me.axua.impactplus.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;

public class BetterChat extends Module {
  public Setting clearBkg;
  
  Setting nameHighlight;
  
  Setting friendHighlight;
  
  @EventHandler
  private Listener<ClientChatReceivedEvent> chatReceivedEventListener;
  
  public BetterChat() {
    super("BetterChat", Module.Category.CHAT);
    this.chatReceivedEventListener = new Listener(event -> {
          if (mc.player == null)
            return; 
          if (this.friendHighlight.getValBoolean() && !event.getMessage().getUnformattedText().contains("<" + mc.player.getName() + ">"))
            Friends.getFriends().forEach(()); 
          Style style = event.getMessage().getStyle();
          if (this.nameHighlight.getValBoolean() && !event.getMessage().getUnformattedText().contains("<" + mc.player.getName() + ">") && event.getMessage().getUnformattedText().contains(mc.player.getName()))
            event.getMessage().getStyle().setParentStyle(style.setBold(Boolean.valueOf(true))); 
        }new java.util.function.Predicate[0]);
  }
  
  public void setup() {
    this.clearBkg = new Setting("Clear", this, true, "BetterChatClear");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.clearBkg);
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.nameHighlight = new Setting("NameHighlight", this, true, "BetterChatNameHighlight"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.friendHighlight = new Setting("FriendHighlight", this, true, "BetterChatFriendHighlight"));
  }
  
  public void onEnable() {
    ImpactPlus.EVENT_BUS.subscribe(this);
  }
  
  public void onDisable() {
    ImpactPlus.EVENT_BUS.unsubscribe(this);
  }
}
