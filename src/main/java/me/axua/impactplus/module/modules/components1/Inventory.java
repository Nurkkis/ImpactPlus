package me.axua.impactplus.module.modules.components1;

import java.util.ArrayList;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.gui.settings.Setting;
import me.axua.impactplus.module.Module;

public class Inventory extends Module {
  public Setting red;
  
  public Setting green;
  
  public Setting blue;
  
  public Setting alpha;
  
  public Setting rainbow;
  
  public Setting mode;
  
  public Setting hotbar;
  
  public Setting crafting;
  
  public Inventory() {
    super("Inventory", Module.Category.COMPONENTS1);
    setDrawn(false);
  }
  
  public void setup() {
    ArrayList<String> modes = new ArrayList<>();
    modes.add("None");
    modes.add("Rect");
    this.red = new Setting("Red", this, 9.0D, 0.0D, 255.0D, true, "InventoryRed");
    this.green = new Setting("Green", this, 9.0D, 0.0D, 255.0D, true, "InventoryGreen");
    this.blue = new Setting("Blue", this, 9.0D, 0.0D, 255.0D, true, "InventoryBlue");
    this.alpha = new Setting("Alpha", this, 125.0D, 0.0D, 255.0D, true, "InventoryAlpha");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.red);
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.green);
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.blue);
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.alpha);
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.rainbow = new Setting("Rainbow", this, false, "InventoryRainbow"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.hotbar = new Setting("HotBar", this, true, "InventoryHotBar"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.crafting = new Setting("Crafting", this, true, "InventoryCrafting"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.mode = new Setting("Mode", this, "Rect", modes, "InventoryMode"));
  }
  
  public void onEnable() {
    disable();
  }
}
