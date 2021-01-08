package me.axua.impactplus.event.events;

import me.axua.impactplus.event.ImpactPlusEvent;

public class EventRenderSetupFog extends ImpactPlusEvent {
  public int StartCoords;
  
  public float PartialTicks;
  
  public EventRenderSetupFog(int startCoords, float partialTicks) {
    this.StartCoords = startCoords;
    this.PartialTicks = partialTicks;
  }
}
