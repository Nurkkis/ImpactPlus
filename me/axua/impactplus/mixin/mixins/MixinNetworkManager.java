package me.axua.impactplus.mixin.mixins;

import io.netty.channel.ChannelHandlerContext;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.event.events.PacketEvent;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({NetworkManager.class})
public class MixinNetworkManager {
  @Inject(method = {"sendPacket(Lnet/minecraft/network/Packet;)V"}, at = {@At("HEAD")}, cancellable = true)
  private void onSendPacket(Packet<?> packet, CallbackInfo callbackInfo) {
    PacketEvent.Send event = new PacketEvent.Send(packet);
    ImpactPlus.EVENT_BUS.post(event);
    if (event.isCancelled())
      callbackInfo.cancel(); 
  }
  
  @Inject(method = {"channelRead0"}, at = {@At("HEAD")}, cancellable = true)
  private void onChannelRead(ChannelHandlerContext context, Packet<?> packet, CallbackInfo callbackInfo) {
    PacketEvent.Receive event = new PacketEvent.Receive(packet);
    ImpactPlus.EVENT_BUS.post(event);
    if (event.isCancelled())
      callbackInfo.cancel(); 
  }
}
