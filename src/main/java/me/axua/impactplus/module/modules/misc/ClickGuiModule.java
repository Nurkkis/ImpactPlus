//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.module.modules.misc;

import java.util.ArrayList;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.gui.settings.Setting;
import me.axua.impactplus.module.Module;
import net.minecraft.client.gui.GuiScreen;

public class ClickGuiModule extends Module {
  public ClickGuiModule INSTANCE;
  
  public Setting textmode;
  
  public ClickGuiModule() {
    super("ClickGUI", Module.Category.MISC, "Opens the ClickGUI");
    setBind(25);
    this.INSTANCE = this;
  }
  
  public void setup() {
    ArrayList<String> textmodes = new ArrayList<>();
    textmodes.add("Left");
    textmodes.add("Middle");
    ArrayList<String> styles = new ArrayList<>();
    styles.add("Old");
    styles.add("New");
    styles.add("WeepCraft");
    (ImpactPlus.getInstance()).settingsManager.rSetting(new Setting("Red", this, 68.0D, 0.0D, 255.0D, true, "ClickGuiRed"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(new Setting("Green", this, 68.0D, 0.0D, 255.0D, true, "ClickGuiGreen"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(new Setting("Blue", this, 255.0D, 0.0D, 255.0D, true, "ClickGuiBlue"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(new Setting("Rainbow", this, true, "ClickGuiRainbow"));
    rSetting(new Setting("Tooltips", this, true, "ClickGuiTooltips"));
    rSetting(new Setting("Style", this, "New", styles, "ClickGuiStyle"));
    rSetting(new Setting("Text Align", this, "Left", textmodes, "ClickGuiTextMode"));
  }
  
  public void onEnable() {
    mc.displayGuiScreen((GuiScreen)(ImpactPlus.getInstance()).clickGui);
    disable();
  }
}
