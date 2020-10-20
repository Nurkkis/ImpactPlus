package me.axua.impactplus.event.events;

import me.axua.impactplus.event.ImpactPlusEvent;
import net.minecraft.client.gui.GuiScreen;

public class GuiScreenDisplayedEvent extends ImpactPlusEvent {
  private final GuiScreen guiScreen;
  
  public GuiScreenDisplayedEvent(GuiScreen screen) {
    this.guiScreen = screen;
  }
  
  public GuiScreen getScreen() {
    return this.guiScreen;
  }
}
