//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.module;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.command.Command;
import me.axua.impactplus.event.events.RenderEvent;
import me.axua.impactplus.gui.settings.Setting;
import net.minecraft.client.Minecraft;

public class Module {
  protected static final Minecraft mc = Minecraft.getMinecraft();
  
  String name;
  
  Category category;
  
  int bind;
  
  boolean enabled;
  
  boolean drawn;
  
  String description;
  
  public Module(String n, Category c) {
    this.name = n;
    this.category = c;
    this.bind = 0;
    this.enabled = false;
    this.drawn = true;
    this.description = "No description";
    setup();
  }
  
  public Module(String n, Category c, String desc) {
    this.name = n;
    this.category = c;
    this.bind = 0;
    this.enabled = false;
    this.drawn = true;
    this.description = desc;
    setup();
  }
  
  public String getName() {
    return this.name;
  }
  
  public void setName(String n) {
    this.name = n;
  }
  
  public Category getCategory() {
    return this.category;
  }
  
  public void setCategory(Category c) {
    this.category = c;
  }
  
  public int getBind() {
    return this.bind;
  }
  
  public void setBind(int b) {
    this.bind = b;
  }
  
  protected void onEnable() {}
  
  protected void onDisable() {}
  
  public void onUpdate() {}
  
  public void onRender() {}
  
  public void onWorldRender(RenderEvent event) {}
  
  public boolean isEnabled() {
    return this.enabled;
  }
  
  public void setEnabled(boolean e) {
    this.enabled = e;
  }
  
  public void enable() {
    setEnabled(true);
    if (ModuleManager.isModuleEnabled("ToggleMsgs") && !getName().equalsIgnoreCase("ClickGUI"))
      Command.sendClientMessage(getName() + ChatFormatting.GREEN + " enabled!"); 
    onEnable();
  }
  
  public void disable() {
    setEnabled(false);
    if (ModuleManager.isModuleEnabled("ToggleMsgs") && !getName().equalsIgnoreCase("ClickGUI"))
      Command.sendClientMessage(getName() + ChatFormatting.RED + " disabled!"); 
    onDisable();
  }
  
  public void toggle() {
    if (isEnabled()) {
      disable();
    } else if (!isEnabled()) {
      enable();
    } 
  }
  
  public String getHudInfo() {
    return "";
  }
  
  public void setup() {}
  
  public boolean isDrawn() {
    return this.drawn;
  }
  
  public void setDrawn(boolean d) {
    this.drawn = d;
  }
  
  public void rSetting(Setting setting) {
    (ImpactPlus.getInstance()).settingsManager.rSetting(setting);
  }
  
  public String getDescription() {
    return this.description;
  }
  
  public void setDescription(String desc) {
    this.description = desc;
  }
  
  public enum Category {
    COMBAT, PLAYER, MOVEMENT, MISC, CHAT, RENDER, COMPONENTS1, COMPONENTS2;
  }
}
