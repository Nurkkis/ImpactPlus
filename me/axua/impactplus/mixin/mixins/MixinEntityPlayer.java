//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.mixin.mixins;

import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.event.events.PlayerTravelEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = {EntityPlayer.class}, priority = 2147483647)
public abstract class MixinEntityPlayer extends EntityLivingBase {
  public MixinEntityPlayer(World worldIn) {
    super(worldIn);
  }
  
  @Inject(method = {"travel"}, at = {@At("HEAD")}, cancellable = true)
  public void travel(float strafe, float vertical, float forward, CallbackInfo info) {
    PlayerTravelEvent event = new PlayerTravelEvent();
    ImpactPlus.EVENT_BUS.post(event);
    if (event.isCancelled()) {
      move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
      info.cancel();
    } 
  }
}
