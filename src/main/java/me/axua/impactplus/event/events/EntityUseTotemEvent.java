package me.axua.impactplus.event.events;

import me.axua.impactplus.event.ImpactPlusEvent;
import net.minecraft.entity.Entity;

public class EntityUseTotemEvent extends ImpactPlusEvent {
  private Entity entity;
  
  public EntityUseTotemEvent(Entity entity) {
    this.entity = entity;
  }
  
  public Entity getEntity() {
    return this.entity;
  }
}
