//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.module.modules.render;

import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.event.events.RenderEvent;
import me.axua.impactplus.friends.Friends;
import me.axua.impactplus.gui.settings.Setting;
import me.axua.impactplus.module.Module;
import me.axua.impactplus.module.modules.combat.Surround;
import me.axua.impactplus.util.ImpactPlusTessellator;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;

public class CsgoESP extends Module {
  Setting players;
  
  Setting passive;
  
  Setting monsters;
  
  Setting items;
  
  Setting xpBottles;
  
  Setting crystals;
  
  Setting orbs;
  
  public CsgoESP() {
    super("CsgoESP", Module.Category.RENDER, "Added back because haikewl");
  }
  
  public void setup() {
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.players = new Setting("Players", this, true, "CsGoEsPlayers"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.passive = new Setting("Passive", this, false, "CsGoEspPassive"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.monsters = new Setting("Monsters", this, false, "CsGoEspMonsters"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.items = new Setting("Items", this, false, "CsGoEspItems"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.crystals = new Setting("Crystals", this, false, "CsGoEspCrystals"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.xpBottles = new Setting("XpBottles", this, false, "CsGoEspXpBottles"));
    rSetting(this.orbs = new Setting("XpOrbs", this, false, "CsGoEspXpOrbs"));
  }
  
  public void onWorldRender(RenderEvent event) {
    if ((mc.getRenderManager()).options == null)
      return; 
    boolean isThirdPersonFrontal = ((mc.getRenderManager()).options.thirdPersonView == 2);
    float viewerYaw = (mc.getRenderManager()).playerViewY;
    mc.world.loadedEntityList.stream()
      .filter(entity -> (mc.player != entity))
      .forEach(e -> {
          ImpactPlusTessellator.prepareGL();
          GlStateManager.pushMatrix();
          Vec3d pos = Surround.getInterpolatedPos(e, mc.getRenderPartialTicks());
          GlStateManager.translate(pos.x - (mc.getRenderManager()).renderPosX, pos.y - (mc.getRenderManager()).renderPosY, pos.z - (mc.getRenderManager()).renderPosZ);
          GlStateManager.glNormal3f(0.0F, 1.0F, 0.0F);
          GlStateManager.rotate(-viewerYaw, 0.0F, 1.0F, 0.0F);
          GlStateManager.rotate((isThirdPersonFrontal ? -1 : true), 1.0F, 0.0F, 0.0F);
          GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
          GL11.glLineWidth(3.0F);
          GL11.glEnable(2848);
          if (e instanceof net.minecraft.entity.player.EntityPlayer && this.players.getValBoolean()) {
            if (Friends.isFriend(e.getName())) {
              GL11.glColor4f(0.0F, 1.0F, 1.0F, 0.5F);
            } else {
              GL11.glColor4f(1.0F, 0.0F, 0.0F, 0.5F);
            } 
            GL11.glBegin(2);
            GL11.glVertex2d(-e.width, 0.0D);
            GL11.glVertex2d(-e.width, (e.height / 3.0F));
            GL11.glVertex2d(-e.width, 0.0D);
            GL11.glVertex2d((-e.width / 3.0F * 2.0F), 0.0D);
            GL11.glEnd();
            GL11.glBegin(2);
            GL11.glVertex2d(-e.width, e.height);
            GL11.glVertex2d((-e.width / 3.0F * 2.0F), e.height);
            GL11.glVertex2d(-e.width, e.height);
            GL11.glVertex2d(-e.width, (e.height / 3.0F * 2.0F));
            GL11.glEnd();
            GL11.glBegin(2);
            GL11.glVertex2d(e.width, e.height);
            GL11.glVertex2d((e.width / 3.0F * 2.0F), e.height);
            GL11.glVertex2d(e.width, e.height);
            GL11.glVertex2d(e.width, (e.height / 3.0F * 2.0F));
            GL11.glEnd();
            GL11.glBegin(2);
            GL11.glVertex2d(e.width, 0.0D);
            GL11.glVertex2d((e.width / 3.0F * 2.0F), 0.0D);
            GL11.glVertex2d(e.width, 0.0D);
            GL11.glVertex2d(e.width, (e.height / 3.0F));
            GL11.glEnd();
          } 
          GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
          if (GlowESP.isPassive(e) && this.passive.getValBoolean()) {
            GL11.glBegin(2);
            GL11.glVertex2d(-e.width, 0.0D);
            GL11.glVertex2d(-e.width, (e.height / 3.0F));
            GL11.glVertex2d(-e.width, 0.0D);
            GL11.glVertex2d((-e.width / 3.0F * 2.0F), 0.0D);
            GL11.glEnd();
            GL11.glBegin(2);
            GL11.glVertex2d(-e.width, e.height);
            GL11.glVertex2d((-e.width / 3.0F * 2.0F), e.height);
            GL11.glVertex2d(-e.width, e.height);
            GL11.glVertex2d(-e.width, (e.height / 3.0F * 2.0F));
            GL11.glEnd();
            GL11.glBegin(2);
            GL11.glVertex2d(e.width, e.height);
            GL11.glVertex2d((e.width / 3.0F * 2.0F), e.height);
            GL11.glVertex2d(e.width, e.height);
            GL11.glVertex2d(e.width, (e.height / 3.0F * 2.0F));
            GL11.glEnd();
            GL11.glBegin(2);
            GL11.glVertex2d(e.width, 0.0D);
            GL11.glVertex2d((e.width / 3.0F * 2.0F), 0.0D);
            GL11.glVertex2d(e.width, 0.0D);
            GL11.glVertex2d(e.width, (e.height / 3.0F));
            GL11.glEnd();
          } 
          if (GlowESP.isMonster(e) && this.monsters.getValBoolean()) {
            GL11.glBegin(2);
            GL11.glVertex2d(-e.width, 0.0D);
            GL11.glVertex2d(-e.width, (e.height / 3.0F));
            GL11.glVertex2d(-e.width, 0.0D);
            GL11.glVertex2d((-e.width / 3.0F * 2.0F), 0.0D);
            GL11.glEnd();
            GL11.glBegin(2);
            GL11.glVertex2d(-e.width, e.height);
            GL11.glVertex2d((-e.width / 3.0F * 2.0F), e.height);
            GL11.glVertex2d(-e.width, e.height);
            GL11.glVertex2d(-e.width, (e.height / 3.0F * 2.0F));
            GL11.glEnd();
            GL11.glBegin(2);
            GL11.glVertex2d(e.width, e.height);
            GL11.glVertex2d((e.width / 3.0F * 2.0F), e.height);
            GL11.glVertex2d(e.width, e.height);
            GL11.glVertex2d(e.width, (e.height / 3.0F * 2.0F));
            GL11.glEnd();
            GL11.glBegin(2);
            GL11.glVertex2d(e.width, 0.0D);
            GL11.glVertex2d((e.width / 3.0F * 2.0F), 0.0D);
            GL11.glVertex2d(e.width, 0.0D);
            GL11.glVertex2d(e.width, (e.height / 3.0F));
            GL11.glEnd();
          } 
          if (e instanceof net.minecraft.entity.item.EntityItem && this.items.getValBoolean()) {
            GL11.glBegin(2);
            GL11.glVertex2d(-e.width, 0.0D);
            GL11.glVertex2d(-e.width, (e.height / 3.0F));
            GL11.glVertex2d(-e.width, 0.0D);
            GL11.glVertex2d((-e.width / 3.0F * 2.0F), 0.0D);
            GL11.glEnd();
            GL11.glBegin(2);
            GL11.glVertex2d(-e.width, e.height);
            GL11.glVertex2d((-e.width / 3.0F * 2.0F), e.height);
            GL11.glVertex2d(-e.width, e.height);
            GL11.glVertex2d(-e.width, (e.height / 3.0F * 2.0F));
            GL11.glEnd();
            GL11.glBegin(2);
            GL11.glVertex2d(e.width, e.height);
            GL11.glVertex2d((e.width / 3.0F * 2.0F), e.height);
            GL11.glVertex2d(e.width, e.height);
            GL11.glVertex2d(e.width, (e.height / 3.0F * 2.0F));
            GL11.glEnd();
            GL11.glBegin(2);
            GL11.glVertex2d(e.width, 0.0D);
            GL11.glVertex2d((e.width / 3.0F * 2.0F), 0.0D);
            GL11.glVertex2d(e.width, 0.0D);
            GL11.glVertex2d(e.width, (e.height / 3.0F));
            GL11.glEnd();
          } 
          if (e instanceof net.minecraft.entity.item.EntityExpBottle && this.xpBottles.getValBoolean()) {
            GL11.glBegin(2);
            GL11.glVertex2d(-e.width, 0.0D);
            GL11.glVertex2d(-e.width, (e.height / 3.0F));
            GL11.glVertex2d(-e.width, 0.0D);
            GL11.glVertex2d((-e.width / 3.0F * 2.0F), 0.0D);
            GL11.glEnd();
            GL11.glBegin(2);
            GL11.glVertex2d(-e.width, e.height);
            GL11.glVertex2d((-e.width / 3.0F * 2.0F), e.height);
            GL11.glVertex2d(-e.width, e.height);
            GL11.glVertex2d(-e.width, (e.height / 3.0F * 2.0F));
            GL11.glEnd();
            GL11.glBegin(2);
            GL11.glVertex2d(e.width, e.height);
            GL11.glVertex2d((e.width / 3.0F * 2.0F), e.height);
            GL11.glVertex2d(e.width, e.height);
            GL11.glVertex2d(e.width, (e.height / 3.0F * 2.0F));
            GL11.glEnd();
            GL11.glBegin(2);
            GL11.glVertex2d(e.width, 0.0D);
            GL11.glVertex2d((e.width / 3.0F * 2.0F), 0.0D);
            GL11.glVertex2d(e.width, 0.0D);
            GL11.glVertex2d(e.width, (e.height / 3.0F));
            GL11.glEnd();
          } 
          if (e instanceof net.minecraft.entity.item.EntityEnderCrystal && this.crystals.getValBoolean()) {
            GL11.glBegin(2);
            GL11.glVertex2d((-e.width / 2.0F), 0.0D);
            GL11.glVertex2d((-e.width / 2.0F), (e.height / 3.0F));
            GL11.glVertex2d((-e.width / 2.0F), 0.0D);
            GL11.glVertex2d((-e.width / 3.0F * 2.0F / 2.0F), 0.0D);
            GL11.glEnd();
            GL11.glBegin(2);
            GL11.glVertex2d((-e.width / 2.0F), e.height);
            GL11.glVertex2d((-e.width / 3.0F * 2.0F / 2.0F), e.height);
            GL11.glVertex2d((-e.width / 2.0F), e.height);
            GL11.glVertex2d((-e.width / 2.0F), (e.height / 3.0F * 2.0F));
            GL11.glEnd();
            GL11.glBegin(2);
            GL11.glVertex2d((e.width / 2.0F), e.height);
            GL11.glVertex2d((e.width / 3.0F * 2.0F / 2.0F), e.height);
            GL11.glVertex2d((e.width / 2.0F), e.height);
            GL11.glVertex2d((e.width / 2.0F), (e.height / 3.0F * 2.0F));
            GL11.glEnd();
            GL11.glBegin(2);
            GL11.glVertex2d((e.width / 2.0F), 0.0D);
            GL11.glVertex2d((e.width / 3.0F * 2.0F / 2.0F), 0.0D);
            GL11.glVertex2d((e.width / 2.0F), 0.0D);
            GL11.glVertex2d((e.width / 2.0F), (e.height / 3.0F));
            GL11.glEnd();
          } 
          if (e instanceof net.minecraft.entity.item.EntityXPOrb && this.orbs.getValBoolean()) {
            GL11.glBegin(2);
            GL11.glVertex2d((-e.width / 2.0F), 0.0D);
            GL11.glVertex2d((-e.width / 2.0F), (e.height / 3.0F));
            GL11.glVertex2d((-e.width / 2.0F), 0.0D);
            GL11.glVertex2d((-e.width / 3.0F * 2.0F / 2.0F), 0.0D);
            GL11.glEnd();
            GL11.glBegin(2);
            GL11.glVertex2d((-e.width / 2.0F), e.height);
            GL11.glVertex2d((-e.width / 3.0F * 2.0F / 2.0F), e.height);
            GL11.glVertex2d((-e.width / 2.0F), e.height);
            GL11.glVertex2d((-e.width / 2.0F), (e.height / 3.0F * 2.0F));
            GL11.glEnd();
            GL11.glBegin(2);
            GL11.glVertex2d((e.width / 2.0F), e.height);
            GL11.glVertex2d((e.width / 3.0F * 2.0F / 2.0F), e.height);
            GL11.glVertex2d((e.width / 2.0F), e.height);
            GL11.glVertex2d((e.width / 2.0F), (e.height / 3.0F * 2.0F));
            GL11.glEnd();
            GL11.glBegin(2);
            GL11.glVertex2d((e.width / 2.0F), 0.0D);
            GL11.glVertex2d((e.width / 3.0F * 2.0F / 2.0F), 0.0D);
            GL11.glVertex2d((e.width / 2.0F), 0.0D);
            GL11.glVertex2d((e.width / 2.0F), (e.height / 3.0F));
            GL11.glEnd();
          } 
          ImpactPlusTessellator.releaseGL();
          GlStateManager.popMatrix();
        });
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
  }
}
