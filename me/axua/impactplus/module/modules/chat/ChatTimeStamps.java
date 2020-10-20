//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.module.modules.chat;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.gui.settings.Setting;
import me.axua.impactplus.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.ClientChatReceivedEvent;

public class ChatTimeStamps extends Module {
  Setting format;
  
  Setting color;
  
  Setting decoration;
  
  @EventHandler
  private Listener<ClientChatReceivedEvent> listener;
  
  public ChatTimeStamps() {
    super("ChatTimeStamps", Module.Category.CHAT);
    this.listener = new Listener(event -> {
          String decoLeft = this.decoration.getValString().equalsIgnoreCase(" ") ? "" : this.decoration.getValString().split(" ")[0];
          String decoRight = this.decoration.getValString().equalsIgnoreCase(" ") ? "" : this.decoration.getValString().split(" ")[1];
          decoRight = decoRight + " ";
          String dateFormat = this.format.getValString().replace("H24", "k").replace("H12", "h");
          String date = (new SimpleDateFormat(dateFormat)).format(new Date());
          TextComponentString time = new TextComponentString(ChatFormatting.getByName(this.color.getValString()) + decoLeft + date + decoRight + ChatFormatting.RESET);
          event.setMessage(time.appendSibling(event.getMessage()));
        }new java.util.function.Predicate[0]);
    ArrayList<String> formats = new ArrayList<>();
    formats.add("H24:mm");
    formats.add("H12:mm");
    formats.add("H12:mm a");
    formats.add("H24:mm:ss");
    formats.add("H12:mm:ss");
    formats.add("H12:mm:ss a");
    ArrayList<String> deco = new ArrayList<>();
    deco.add("< >");
    deco.add("[ ]");
    deco.add("{ }");
    deco.add(" ");
    ArrayList<String> colors = new ArrayList<>();
    for (ChatFormatting cf : ChatFormatting.values())
      colors.add(cf.getName()); 
    rSetting(this.format = new Setting("Format", this, "H12:mm:ss a", formats, "ChatTimeStampsFormat"));
    rSetting(this.color = new Setting("Color", this, "Red", colors, "ChatTimeStampsColor"));
    rSetting(this.decoration = new Setting("Deco", this, "[ ]", deco, "ChatTimeStampsDeco"));
  }
  
  public void onEnable() {
    ImpactPlus.EVENT_BUS.subscribe(this);
  }
  
  public void onDisable() {
    ImpactPlus.EVENT_BUS.unsubscribe(this);
  }
}
