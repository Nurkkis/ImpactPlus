package me.axua.impactplus.util;

import me.axua.impactplus.event.ImpactPlusEvent;
import net.minecraft.network.Packet;

public class EventNetworkPacketEvent extends ImpactPlusEvent {
  public Packet m_Packet;
  
  public EventNetworkPacketEvent(Packet p_Packet) {
    this.m_Packet = p_Packet;
  }
  
  public Packet GetPacket() {
    return this.m_Packet;
  }
  
  public Packet getPacket() {
    return this.m_Packet;
  }
}
