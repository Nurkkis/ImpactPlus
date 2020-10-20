//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.module.modules.render;

import java.util.List;
import java.util.stream.Collectors;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.gui.settings.Setting;
import me.axua.impactplus.module.Module;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.passive.EntityWolf;

public class GlowESP extends Module {
  Setting players;
  
  Setting passive;
  
  Setting monsters;
  
  Setting items;
  
  Setting xpBottles;
  
  Setting crystals;
  
  List<Entity> entities;
  
  public GlowESP() {
    super("GlowESP", Module.Category.RENDER, "Gives entities the glowing effect");
  }
  
  public void setup() {
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.players = new Setting("Players", this, true, "GlowEspPlayers"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.passive = new Setting("Passive", this, false, "GlowEspPassive"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.monsters = new Setting("Monsters", this, false, "GlowEspMonsters"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.items = new Setting("Items", this, false, "GlowEspItems"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.crystals = new Setting("Crystals", this, false, "GlowEspCrystals"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.xpBottles = new Setting("XpBottles", this, false, "GlowEspXpBottles"));
  }
  
  public void onUpdate() {
    this
      
      .entities = (List<Entity>)mc.world.loadedEntityList.stream().filter(e -> (e != mc.player)).collect(Collectors.toList());
    this.entities.forEach(e -> {
          if (e instanceof net.minecraft.entity.player.EntityPlayer && this.players.getValBoolean())
            e.setGlowing(true); 
          if (isPassive(e) && this.passive.getValBoolean())
            e.setGlowing(true); 
          if (e instanceof net.minecraft.entity.item.EntityExpBottle && this.xpBottles.getValBoolean())
            e.setGlowing(true); 
          if (isMonster(e) && this.monsters.getValBoolean())
            e.setGlowing(true); 
          if (e instanceof net.minecraft.entity.item.EntityItem && this.items.getValBoolean())
            e.setGlowing(true); 
          if (e instanceof net.minecraft.entity.item.EntityEnderCrystal && this.crystals.getValBoolean())
            e.setGlowing(true); 
        });
  }
  
  public void onDisable() {
    this.entities.forEach(p -> p.setGlowing(false));
  }
  
  public static boolean isPassive(Entity e) {
    if (e instanceof EntityWolf && ((EntityWolf)e).isAngry())
      return false; 
    if (e instanceof net.minecraft.entity.passive.EntityAnimal || e instanceof net.minecraft.entity.EntityAgeable || e instanceof net.minecraft.entity.passive.EntityTameable || e instanceof net.minecraft.entity.passive.EntityAmbientCreature || e instanceof net.minecraft.entity.passive.EntitySquid)
      return true; 
    if (e instanceof EntityIronGolem && ((EntityIronGolem)e).getRevengeTarget() == null)
      return true; 
    return false;
  }
  
  public static boolean isMonster(Entity entity) {
    return entity.isCreatureType(EnumCreatureType.MONSTER, false);
  }
}
