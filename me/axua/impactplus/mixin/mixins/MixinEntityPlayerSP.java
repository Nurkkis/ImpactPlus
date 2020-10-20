//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.mixin.mixins;

import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.event.ImpactPlusEvent;
import me.axua.impactplus.event.events.EventPlayerMotionUpdate;
import me.axua.impactplus.event.events.EventPlayerUpdate;
import me.axua.impactplus.event.events.PlayerMoveEvent;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.MoverType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({EntityPlayerSP.class})
public abstract class MixinEntityPlayerSP extends AbstractClientPlayer {
  public MixinEntityPlayerSP() {
    super(null, null);
  }
  
  @Redirect(method = {"move"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/AbstractClientPlayer;move(Lnet/minecraft/entity/MoverType;DDD)V"))
  public void move(AbstractClientPlayer player, MoverType type, double x, double y, double z) {
    PlayerMoveEvent moveEvent = new PlayerMoveEvent(type, x, y, z);
    ImpactPlus.EVENT_BUS.post(moveEvent);
    if (moveEvent.isCancelled());
    move(type, moveEvent.x, moveEvent.y, moveEvent.z);
  }
  
  @Inject(method = {"onUpdate"}, at = {@At("HEAD")}, cancellable = true)
  public void onUpdate(CallbackInfo p_Info) {
    EventPlayerUpdate l_Event = new EventPlayerUpdate();
    ImpactPlus.EVENT_BUS.post(l_Event);
    if (l_Event.isCancelled())
      p_Info.cancel(); 
  }
  
  @Inject(method = {"onUpdateWalkingPlayer"}, at = {@At("HEAD")}, cancellable = true)
  public void OnPreUpdateWalkingPlayer(CallbackInfo p_Info) {
    EventPlayerMotionUpdate l_Event = new EventPlayerMotionUpdate(ImpactPlusEvent.Era.PRE);
    ImpactPlus.EVENT_BUS.post(l_Event);
    if (l_Event.isCancelled())
      p_Info.cancel(); 
  }
  
  @Inject(method = {"onUpdateWalkingPlayer"}, at = {@At("RETURN")}, cancellable = true)
  public void OnPostUpdateWalkingPlayer(CallbackInfo p_Info) {
    EventPlayerMotionUpdate l_Event = new EventPlayerMotionUpdate(ImpactPlusEvent.Era.POST);
    ImpactPlus.EVENT_BUS.post(l_Event);
    if (l_Event.isCancelled())
      p_Info.cancel(); 
  }
}
