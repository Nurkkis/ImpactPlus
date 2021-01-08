package me.axua.impactplus.module.modules.render;

import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.module.Module;

public class CleanGui extends Module {
  public CleanGui() {
    super("CleanGui", Module.Category.RENDER);
  }
  
  protected void onEnable() {
    ImpactPlus.EVENT_BUS.subscribe(this);
  }
  
  protected void onDisable() {
    ImpactPlus.EVENT_BUS.unsubscribe(this);
  }
}
