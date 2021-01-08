//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.command.commands;

import me.axua.impactplus.command.Command;
import me.axua.impactplus.module.Module;
import me.axua.impactplus.module.ModuleManager;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;

public class ModsCommand extends Command {
  public String[] getAlias() {
    return new String[] { "modules", "mods" };
  }
  
  public String getSyntax() {
    return "modules";
  }
  
  public void onCommand(String command, String[] args) throws Exception {
    int size = ModuleManager.getModules().size();
    TextComponentString msg = new TextComponentString("§7Modules: §f ");
    for (int i = 0; i < size; i++) {
      Module mod = ModuleManager.getModules().get(i);
      if (mod != null)
        msg.appendSibling((new TextComponentString((mod.isEnabled() ? "§a" : "§c") + mod.getName() + "§7" + ((i == size - 1) ? "" : ", ")))
            .setStyle((new Style())
              .setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (ITextComponent)new TextComponentString(mod.getCategory().name())))
              .setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, Command.getPrefix() + "toggle " + mod.getName())))); 
    } 
    (Minecraft.getMinecraft()).ingameGUI.getChatGUI().printChatMessage((ITextComponent)msg);
  }
}
