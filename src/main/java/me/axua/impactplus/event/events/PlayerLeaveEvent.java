package me.axua.impactplus.event.events;

import me.axua.impactplus.event.ImpactPlusEvent;

public class PlayerLeaveEvent extends ImpactPlusEvent {
  private final String name;
  
  public PlayerLeaveEvent(String n) {
    this.name = n;
  }
  
  public String getName() {
    return this.name;
  }
}
