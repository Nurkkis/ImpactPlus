//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.module.modules.misc;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import me.axua.impactplus.command.Command;
import me.axua.impactplus.module.Module;
import net.minecraft.client.Minecraft;

public class CopyIp extends Module {
  String server;
  
  public CopyIp() {
    super("CopyIp", Module.Category.MISC, "Copy's current server ip to clipboard");
  }
  
  public void onEnable() {
    Minecraft mc = Minecraft.getMinecraft();
    try {
      this.server = (mc.getCurrentServerData()).serverIP;
    } catch (Exception e) {
      this.server = "Singleplayer";
    } 
    String myString = this.server;
    StringSelection stringSelection = new StringSelection(myString);
    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    clipboard.setContents(stringSelection, null);
    Command.sendClientMessage("Copied '" + this.server + "' to clipboard.");
    disable();
  }
}
