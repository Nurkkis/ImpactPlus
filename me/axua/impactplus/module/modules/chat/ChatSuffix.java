//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.module.modules.chat;

import java.util.ArrayList;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.command.Command;
import me.axua.impactplus.event.events.PacketEvent;
import me.axua.impactplus.gui.settings.Setting;
import me.axua.impactplus.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.network.play.client.CPacketChatMessage;

public class ChatSuffix extends Module {
  Setting suffixmode;
  
  @EventHandler
  private Listener<PacketEvent.Send> listener;
  
  public ChatSuffix() {
    super("ChatSuffix", Module.Category.CHAT, "Adds a suffix to your messages");
    this.listener = new Listener(event -> {
          if (event.getPacket() instanceof CPacketChatMessage) {
            if (((CPacketChatMessage)event.getPacket()).getMessage().startsWith("/") || ((CPacketChatMessage)event.getPacket()).getMessage().startsWith(Command.getPrefix()) || ((CPacketChatMessage)event.getPacket()).getMessage().startsWith(".") || ((CPacketChatMessage)event.getPacket()).getMessage().startsWith("!") || ((CPacketChatMessage)event.getPacket()).getMessage().startsWith("#"))
              return; 
            String server = (mc.getCurrentServerData()).serverIP.toLowerCase();
            String old = ((CPacketChatMessage)event.getPacket()).getMessage();
            String suffix = "";
            String s = "";
            if (server.equals("2b2t.org")) {
              if (this.suffixmode.getValString().equalsIgnoreCase("Salhack"))
                suffix = " Salhack"; 
              if (this.suffixmode.getValString().equalsIgnoreCase("Impact+"))
                suffix = " Impact+"; 
            } else {
              if (this.suffixmode.getValString().equalsIgnoreCase("Salhack"))
                suffix = " ᵟᵃᴸᴴᵃᶜᴷ"; 
              if (this.suffixmode.getValString().equalsIgnoreCase("Impact+"))
                suffix = " ɪ�?ᴘᴀᴄᴛ₊"; 
            } 
            if (this.suffixmode.getValString().equalsIgnoreCase("Meme Suffix"))
              suffix = " » ɴᴇʙᴜʟᴀ ᵟᵃᴸᴴᵃᶜᴷ » ɪ�?ᴀᴘᴄᴛ₊ » ʌгᴇѕ+ « ᴋᴀ�?ɪ ʙʟᴜᴇ �?ɴ ᴛ�?ᴘ » ˢ�?�ᵒʷ�?? �?εᎮнᗩεѕƭυѕ » ʙᴀᴄᴋᴅ�?�?ʀᴇᴅ | �?ᴇ�?ᴡ » ᴜɴɪᴄ�?ʀɴɢ�?ᴅ.ɢɢ ~~ ꜱᴇᴘᴘᴜᴋᴜ | ʜᴜᴢᴜɴɪɢʀᴇᴇɴ.ɢɢ™ » ʙᴀᴄᴋᴄʟɪᴇɴᴛ™ » ɴ�?ᴜ ʟᴇᴀᴋ ☯ �?? ғ�?ʀɢᴇʀᴀᴛ ♡ | ӨBΛMΛ ᄃᄂIΣПƬ - ᴇʟᴇ�?ᴇɴᴛᴀʀꜱ.ᴄ�?�? 》�?ꜱɪʀɪꜱ | WÔÔK�?Ê ÇLîëÑT™ {ʀᴀɪ�?ɴᴋᴇᴋ} ッ Ｒ�?�?Ｔ ｜ ʀᴜʜᴀ�?ᴀ | ᴅ�?ᴛғᴀɢ.ɪɴ™ >> ᴀʀɪѕᴛ�?ɪѕ ʳᵘˢʰᵉʳʰᵃᶜ�?"; 
            s = old + suffix;
            int longs = s.length();
            int ok = 0;
            if (s.length() > 255)
              ok = longs - 255; 
            s = s.substring(0, s.length() - ok);
            ((CPacketChatMessage)event.getPacket()).message = s;
          } 
        }new java.util.function.Predicate[0]);
  }
  
  public void setup() {
    ArrayList<String> modes = new ArrayList<>();
    modes.add("Impact+");
    modes.add("Salhack");
    modes.add("Meme Suffix");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.suffixmode = new Setting("Suffix", this, "Impact+", modes, "SuffixMode"));
  }
  
  public void onEnable() {
    ImpactPlus.EVENT_BUS.subscribe(this);
  }
  
  public void onDisable() {
    ImpactPlus.EVENT_BUS.unsubscribe(this);
  }
}
