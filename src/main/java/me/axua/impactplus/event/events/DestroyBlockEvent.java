package me.axua.impactplus.event.events;

import me.axua.impactplus.event.ImpactPlusEvent;
import net.minecraft.util.math.BlockPos;

public class DestroyBlockEvent extends ImpactPlusEvent {
  BlockPos pos;
  
  public DestroyBlockEvent(BlockPos blockPos) {
    this.pos = blockPos;
  }
  
  public BlockPos getBlockPos() {
    return this.pos;
  }
}
