//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.module.modules.render;

import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.gui.settings.Setting;
import me.axua.impactplus.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.init.MobEffects;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;

public class NoRender extends Module {
  public Setting armor;
  
  Setting fire;
  
  Setting blind;
  
  Setting nausea;
  
  public Setting hurtCam;
  
  @EventHandler
  public Listener<RenderBlockOverlayEvent> blockOverlayEventListener;
  
  public NoRender() {
    super("NoRender", Module.Category.RENDER, "Prevents rendering some things");
    this.blockOverlayEventListener = new Listener(event -> {
          if (this.fire.getValBoolean() && event.getOverlayType() == RenderBlockOverlayEvent.OverlayType.FIRE)
            event.setCanceled(true); 
        }new java.util.function.Predicate[0]);
  }
  
  public void setup() {
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.armor = new Setting("Armor", this, false, "NoRenderArmor"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.fire = new Setting("Fire", this, false, "NoRenderFire"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.blind = new Setting("Blindness", this, false, "NoRenderBlindnessEffect"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.nausea = new Setting("Nausea", this, false, "NoRenderNauseaEffect"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.hurtCam = new Setting("HurtCam", this, false, "NoRenderHurtCamera"));
  }
  
  public void onUpdate() {
    if (this.blind.getValBoolean() && mc.player.isPotionActive(MobEffects.BLINDNESS))
      mc.player.removePotionEffect(MobEffects.BLINDNESS); 
    if (this.nausea.getValBoolean() && mc.player.isPotionActive(MobEffects.NAUSEA))
      mc.player.removePotionEffect(MobEffects.NAUSEA); 
  }
  
  public void onEnable() {
    ImpactPlus.EVENT_BUS.subscribe(this);
  }
  
  public void onDisable() {
    ImpactPlus.EVENT_BUS.unsubscribe(this);
  }
}
