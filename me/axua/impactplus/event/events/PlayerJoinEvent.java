package me.axua.impactplus.event.events;

import me.axua.impactplus.event.ImpactPlusEvent;

public class PlayerJoinEvent extends ImpactPlusEvent {
  private final String name;
  
  public PlayerJoinEvent(String n) {
    this.name = n;
  }
  
  public String getName() {
    return this.name;
  }
}
