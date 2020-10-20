//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.mixin.mixins;

import com.google.common.base.Predicate;
import java.util.ArrayList;
import java.util.List;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.event.events.EventRenderSetupFog;
import me.axua.impactplus.module.ModuleManager;
import me.axua.impactplus.module.modules.render.NoRender;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({EntityRenderer.class})
public class MixinEntityRenderer {
  @Redirect(method = {"orientCamera"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/WorldClient;rayTraceBlocks(Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/util/math/Vec3d;)Lnet/minecraft/util/math/RayTraceResult;"))
  public RayTraceResult rayTraceBlocks(WorldClient world, Vec3d start, Vec3d end) {
    if (ModuleManager.isModuleEnabled("CameraClip"))
      return null; 
    return world.rayTraceBlocks(start, end);
  }
  
  @Redirect(method = {"getMouseOver"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/WorldClient;getEntitiesInAABBexcluding(Lnet/minecraft/entity/Entity;Lnet/minecraft/util/math/AxisAlignedBB;Lcom/google/common/base/Predicate;)Ljava/util/List;"))
  public List<Entity> getEntitiesInAABBexcluding(WorldClient worldClient, Entity entityIn, AxisAlignedBB boundingBox, Predicate predicate) {
    if (ModuleManager.isModuleEnabled("NoEntityTrace"))
      return new ArrayList<>(); 
    return worldClient.getEntitiesInAABBexcluding(entityIn, boundingBox, predicate);
  }
  
  @Inject(method = {"hurtCameraEffect"}, at = {@At("HEAD")}, cancellable = true)
  public void hurtCameraEffect(float ticks, CallbackInfo info) {
    if (ModuleManager.isModuleEnabled("NoRender") && ((NoRender)ModuleManager.getModuleByName("NoRender")).hurtCam.getValBoolean())
      info.cancel(); 
  }
  
  @Inject(method = {"setupFog"}, at = {@At("HEAD")}, cancellable = true)
  public void setupFog(int startCoords, float partialTicks, CallbackInfo p_Info) {
    EventRenderSetupFog l_Event = new EventRenderSetupFog(startCoords, partialTicks);
    ImpactPlus.EVENT_BUS.post(l_Event);
    if (l_Event.isCancelled())
      p_Info.cancel(); 
  }
}
