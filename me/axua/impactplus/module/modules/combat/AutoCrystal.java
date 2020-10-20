//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.module.modules.combat;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.command.Command;
import me.axua.impactplus.event.events.PacketEvent;
import me.axua.impactplus.event.events.RenderEvent;
import me.axua.impactplus.friends.Friends;
import me.axua.impactplus.gui.settings.Setting;
import me.axua.impactplus.module.Module;
import me.axua.impactplus.module.ModuleManager;
import me.axua.impactplus.module.modules.chat.AutoEZ;
import me.axua.impactplus.util.ImpactPlusTessellator;
import me.axua.impactplus.util.Rainbow;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.potion.Potion;
import net.minecraft.util.CombatRules;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class AutoCrystal extends Module {
  private BlockPos render;
  
  private Entity renderEnt;
  
  private boolean switchCooldown;
  
  private boolean isAttacking;
  
  private int oldSlot;
  
  private int newSlot;
  
  private int waitCounter;
  
  EnumFacing f;
  
  Setting explode;
  
  Setting waitTick;
  
  Setting range;
  
  Setting walls;
  
  Setting antiWeakness;
  
  Setting nodesync;
  
  Setting place;
  
  Setting autoSwitch;
  
  Setting placeRange;
  
  Setting minDmg;
  
  Setting facePlace;
  
  Setting raytrace;
  
  Setting rotate;
  
  Setting spoofRotations;
  
  Setting chat;
  
  Setting rainbow;
  
  Setting espR;
  
  Setting espG;
  
  Setting espB;
  
  Setting espA;
  
  Setting maxSelfDmg;
  
  Setting noGappleSwitch;
  
  Setting renderMode;
  
  public boolean isActive;
  
  private static boolean isSpoofingAngles;
  
  private static double yaw;
  
  private static double pitch;
  
  @EventHandler
  private Listener<PacketEvent.Send> packetSendListener;
  
  @EventHandler
  private Listener<PacketEvent.Receive> packetReceiveListener;
  
  public AutoCrystal() {
    super("AutoCrystal", Module.Category.COMBAT);
    this.switchCooldown = false;
    this.isAttacking = false;
    this.oldSlot = -1;
    this.isActive = false;
    this.packetSendListener = new Listener(event -> {
          Packet packet = event.getPacket();
          if (packet instanceof CPacketPlayer && this.spoofRotations.getValBoolean() && isSpoofingAngles) {
            ((CPacketPlayer)packet).yaw = (float)yaw;
            ((CPacketPlayer)packet).pitch = (float)pitch;
          } 
        }new java.util.function.Predicate[0]);
    this.packetReceiveListener = new Listener(event -> {
          if (event.getPacket() instanceof SPacketSoundEffect && this.nodesync.getValBoolean()) {
            SPacketSoundEffect packet = (SPacketSoundEffect)event.getPacket();
            if (packet.getCategory() == SoundCategory.BLOCKS && packet.getSound() == SoundEvents.ENTITY_GENERIC_EXPLODE)
              for (Entity e : (Minecraft.getMinecraft()).world.loadedEntityList) {
                if (e instanceof EntityEnderCrystal && e.getDistance(packet.getX(), packet.getY(), packet.getZ()) <= 6.0D)
                  e.setDead(); 
              }  
          } 
        }new java.util.function.Predicate[0]);
  }
  
  public void setup() {
    this.explode = new Setting("Hit", this, true, "AutoCrystalHit");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.explode);
    this.waitTick = new Setting("TickDelay", this, 1.0D, 0.0D, 20.0D, true, "AutoCrystalTickDelay");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.waitTick);
    this.range = new Setting("HitRange", this, 5.0D, 0.0D, 10.0D, false, "AutoCrystalHitRange");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.range);
    this.walls = new Setting("WallsRange", this, 3.5D, 0.0D, 10.0D, false, "AutoCrystalWallsRange");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.walls);
    this.antiWeakness = new Setting("AntiWeakness", this, true, "AutoCrystalAntiWeakness");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.antiWeakness);
    this.nodesync = new Setting("AntiDesync", this, true, "AutoCrystalAntiDesync");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.nodesync);
    this.place = new Setting("Place", this, true, "AutoCrystalPlace");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.place);
    this.autoSwitch = new Setting("AutoSwitch", this, true, "AutoCrystalAutoSwitch");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.autoSwitch);
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.noGappleSwitch = new Setting("NoGapSwitch", this, false, "AutoCrystalNoGapSwitch"));
    this.placeRange = new Setting("PlaceRange", this, 5.0D, 0.0D, 10.0D, false, "AutoCrystalPlaceRange");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.placeRange);
    this.minDmg = new Setting("MinDamage", this, 5.0D, 0.0D, 40.0D, false, "AutoCrystalMinDamage");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.minDmg);
    this.facePlace = new Setting("FaceplaceHP", this, 6.0D, 0.0D, 40.0D, false, "AutoCrystalFaceplaceHP");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.facePlace);
    this.raytrace = new Setting("Raytrace", this, false, "AutoCrystalRaytrace");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.raytrace);
    this.rotate = new Setting("Rotate", this, true, "AutoCrystalRotate");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.rotate);
    this.spoofRotations = new Setting("SpoofAngles", this, true, "AutoCrystalSpoofAngles");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.spoofRotations);
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.maxSelfDmg = new Setting("MaxSelfDmg", this, 10.0D, 0.0D, 36.0D, false, "AutoCrystalMaxSelfDamage"));
    this.chat = new Setting("ToggleMsgs", this, true, "AutoCrystalToggleMessages");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.chat);
    this.rainbow = new Setting("EspRainbow", this, true, "AutoCrystalEspRainbow");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.rainbow);
    this.espR = new Setting("EspRed", this, 200.0D, 0.0D, 255.0D, true, "AutoCrystalEspRed");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.espR);
    this.espG = new Setting("EspGreen", this, 50.0D, 0.0D, 255.0D, true, "AutoCrystalEspGreen");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.espG);
    this.espB = new Setting("EspBlue", this, 200.0D, 0.0D, 255.0D, true, "AutoCrystalEspBlue");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.espB);
    this.espA = new Setting("EspAlpha", this, 50.0D, 0.0D, 255.0D, true, "AutoCrystalEspAlpha");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.espA);
    ArrayList<String> renderModes = new ArrayList<>();
    renderModes.add("Box");
    renderModes.add("HalfBox");
    renderModes.add("Plane");
    this.renderMode = new Setting("EspRenderMode", this, "Box", renderModes, "AutoCrystalEspRenderMode");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.renderMode);
  }
  
  public void onUpdate() {
    this.isActive = false;
    if (mc.player == null || mc.player.isDead)
      return; 
    EntityEnderCrystal crystal = mc.world.loadedEntityList.stream().filter(entity -> entity instanceof EntityEnderCrystal).filter(e -> (mc.player.getDistance(e) <= this.range.getValDouble())).map(entity -> (EntityEnderCrystal)entity).min(Comparator.comparing(c -> Float.valueOf(mc.player.getDistance((Entity)c)))).orElse(null);
    if (this.explode.getValBoolean() && crystal != null) {
      if (!mc.player.canEntityBeSeen((Entity)crystal) && mc.player.getDistance((Entity)crystal) > this.walls.getValDouble())
        return; 
      if (this.waitTick.getValDouble() > 0.0D) {
        if (this.waitCounter < this.waitTick.getValDouble()) {
          this.waitCounter++;
          return;
        } 
        this.waitCounter = 0;
      } 
      if (this.antiWeakness.getValBoolean() && mc.player.isPotionActive(MobEffects.WEAKNESS)) {
        if (!this.isAttacking) {
          this.oldSlot = mc.player.inventory.currentItem;
          this.isAttacking = true;
        } 
        this.newSlot = -1;
        for (int i = 0; i < 9; i++) {
          ItemStack stack = mc.player.inventory.getStackInSlot(i);
          if (stack != ItemStack.EMPTY) {
            if (stack.getItem() instanceof net.minecraft.item.ItemSword) {
              this.newSlot = i;
              break;
            } 
            if (stack.getItem() instanceof net.minecraft.item.ItemTool) {
              this.newSlot = i;
              break;
            } 
          } 
        } 
        if (this.newSlot != -1) {
          mc.player.inventory.currentItem = this.newSlot;
          this.switchCooldown = true;
        } 
      } 
      this.isActive = true;
      if (this.rotate.getValBoolean())
        lookAtPacket(crystal.posX, crystal.posY, crystal.posZ, (EntityPlayer)mc.player); 
      mc.playerController.attackEntity((EntityPlayer)mc.player, (Entity)crystal);
      mc.player.swingArm(EnumHand.MAIN_HAND);
      this.isActive = false;
      return;
    } 
    resetRotation();
    if (this.oldSlot != -1) {
      mc.player.inventory.currentItem = this.oldSlot;
      this.oldSlot = -1;
    } 
    this.isAttacking = false;
    this.isActive = false;
    int crystalSlot = (mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL) ? mc.player.inventory.currentItem : -1;
    if (crystalSlot == -1)
      for (int l = 0; l < 9; l++) {
        if (mc.player.inventory.getStackInSlot(l).getItem() == Items.END_CRYSTAL) {
          crystalSlot = l;
          break;
        } 
      }  
    boolean offhand = false;
    if (mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) {
      offhand = true;
    } else if (crystalSlot == -1) {
      return;
    } 
    List<BlockPos> blocks = findCrystalBlocks();
    List<Entity> entities = new ArrayList<>();
    entities.addAll((Collection<? extends Entity>)mc.world.playerEntities.stream().filter(entityPlayer -> !Friends.isFriend(entityPlayer.getName())).sorted(Comparator.comparing(e -> Float.valueOf(mc.player.getDistance((Entity)e)))).collect(Collectors.toList()));
    BlockPos q = null;
    double damage = 0.5D;
    for (Entity entity : entities) {
      if (entity == mc.player || ((EntityLivingBase)entity).getHealth() <= 0.0F || entity.isDead || mc.player == null)
        continue; 
      for (BlockPos blockPos : blocks) {
        double b = entity.getDistanceSq(blockPos);
        if (b >= 169.0D)
          continue; 
        double d = calculateDamage(blockPos.getX() + 0.5D, (blockPos.getY() + 1), blockPos.getZ() + 0.5D, entity);
        if (d < this.minDmg.getValDouble() && (((EntityLivingBase)entity).getHealth() + ((EntityLivingBase)entity).getAbsorptionAmount()) > this.facePlace.getValDouble())
          continue; 
        if (d > damage) {
          double self = calculateDamage(blockPos.getX() + 0.5D, (blockPos.getY() + 1), blockPos.getZ() + 0.5D, (Entity)mc.player);
          if ((self > d && d >= ((EntityLivingBase)entity).getHealth()) || self - 0.5D > mc.player.getHealth())
            continue; 
          if (self > this.maxSelfDmg.getValDouble())
            continue; 
          damage = d;
          q = blockPos;
          this.renderEnt = entity;
        } 
      } 
    } 
    if (damage == 0.5D) {
      this.render = null;
      this.renderEnt = null;
      resetRotation();
      return;
    } 
    this.render = q;
    if (this.place.getValBoolean()) {
      if (mc.player == null)
        return; 
      this.isActive = true;
      if (this.rotate.getValBoolean())
        lookAtPacket(q.getX() + 0.5D, q.getY() - 0.5D, q.getZ() + 0.5D, (EntityPlayer)mc.player); 
      RayTraceResult result = mc.world.rayTraceBlocks(new Vec3d(mc.player.posX, mc.player.posY + mc.player.getEyeHeight(), mc.player.posZ), new Vec3d(q.getX() + 0.5D, q.getY() - 0.5D, q.getZ() + 0.5D));
      if (this.raytrace.getValBoolean()) {
        if (result == null || result.sideHit == null) {
          q = null;
          this.f = null;
          this.render = null;
          resetRotation();
          this.isActive = false;
          return;
        } 
        this.f = result.sideHit;
      } 
      if (!offhand && mc.player.inventory.currentItem != crystalSlot) {
        if (this.autoSwitch.getValBoolean()) {
          if (this.noGappleSwitch.getValBoolean() && isEatingGap()) {
            this.isActive = false;
            resetRotation();
            return;
          } 
          this.isActive = true;
          mc.player.inventory.currentItem = crystalSlot;
          resetRotation();
          this.switchCooldown = true;
        } 
        return;
      } 
      if (this.switchCooldown) {
        this.switchCooldown = false;
        return;
      } 
      if (q != null && mc.player != null) {
        this.isActive = true;
        if (this.raytrace.getValBoolean() && this.f != null) {
          mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(q, this.f, offhand ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0.0F, 0.0F, 0.0F));
        } else {
          mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(q, EnumFacing.UP, offhand ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0.0F, 0.0F, 0.0F));
        } 
        if (ModuleManager.isModuleEnabled("AutoEz"))
          AutoEZ.INSTANCE.addTargetedPlayer(this.renderEnt.getName()); 
      } 
      this.isActive = false;
    } 
  }
  
  public void onWorldRender(RenderEvent event) {
    Color color = Rainbow.getColor();
    Color c = new Color(color.getRed(), color.getGreen(), color.getBlue(), (int)this.espA.getValDouble());
    if (this.render != null && mc.player != null) {
      ImpactPlusTessellator.prepare(7);
      if (this.rainbow.getValBoolean()) {
        drawCurrentBlock(this.render, c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha());
      } else {
        drawCurrentBlock(this.render, this.espR.getValInt(), this.espG.getValInt(), this.espB.getValInt(), this.espA.getValInt());
      } 
      ImpactPlusTessellator.release();
    } 
  }
  
  private boolean isEatingGap() {
    return (mc.player.getHeldItemMainhand().getItem() instanceof net.minecraft.item.ItemAppleGold && mc.player.isHandActive());
  }
  
  private void drawCurrentBlock(BlockPos render, int r, int g, int b, int a) {
    if (this.renderMode.getValString().equalsIgnoreCase("halfbox")) {
      ImpactPlusTessellator.drawHalfBox(render, r, g, b, a, 63);
    } else if (this.renderMode.getValString().equalsIgnoreCase("plane")) {
      ImpactPlusTessellator.drawBox(render, r, g, b, a, 1);
    } else {
      ImpactPlusTessellator.drawBox(render, r, g, b, a, 63);
    } 
  }
  
  private void lookAtPacket(double px, double py, double pz, EntityPlayer me) {
    double[] v = calculateLookAt(px, py, pz, me);
    setYawAndPitch((float)v[0], (float)v[1]);
  }
  
  private boolean canPlaceCrystal(BlockPos blockPos) {
    BlockPos boost = blockPos.add(0, 1, 0);
    BlockPos boost2 = blockPos.add(0, 2, 0);
    return ((mc.world.getBlockState(blockPos).getBlock() == Blocks.BEDROCK || mc.world.getBlockState(blockPos).getBlock() == Blocks.OBSIDIAN) && mc.world.getBlockState(boost).getBlock() == Blocks.AIR && mc.world.getBlockState(boost2).getBlock() == Blocks.AIR && mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(boost)).isEmpty() && mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(boost2)).isEmpty());
  }
  
  public static BlockPos getPlayerPos() {
    return new BlockPos(Math.floor(mc.player.posX), Math.floor(mc.player.posY), Math.floor(mc.player.posZ));
  }
  
  private List<BlockPos> findCrystalBlocks() {
    NonNullList<BlockPos> positions = NonNullList.create();
    positions.addAll((Collection)getSphere(getPlayerPos(), (float)this.placeRange.getValDouble(), (int)this.placeRange.getValDouble(), false, true, 0).stream().filter(this::canPlaceCrystal).collect(Collectors.toList()));
    return (List<BlockPos>)positions;
  }
  
  public List<BlockPos> getSphere(BlockPos loc, float r, int h, boolean hollow, boolean sphere, int plus_y) {
    List<BlockPos> circleblocks = new ArrayList<>();
    int cx = loc.getX();
    int cy = loc.getY();
    int cz = loc.getZ();
    for (int x = cx - (int)r; x <= cx + r; x++) {
      for (int z = cz - (int)r; z <= cz + r; ) {
        int y = sphere ? (cy - (int)r) : cy;
        for (;; z++) {
          if (y < (sphere ? (cy + r) : (cy + h))) {
            double dist = ((cx - x) * (cx - x) + (cz - z) * (cz - z) + (sphere ? ((cy - y) * (cy - y)) : 0));
            if (dist < (r * r) && (!hollow || dist >= ((r - 1.0F) * (r - 1.0F)))) {
              BlockPos l = new BlockPos(x, y + plus_y, z);
              circleblocks.add(l);
            } 
            y++;
            continue;
          } 
        } 
      } 
    } 
    return circleblocks;
  }
  
  public static float calculateDamage(double posX, double posY, double posZ, Entity entity) {
    float doubleExplosionSize = 12.0F;
    double distancedsize = entity.getDistance(posX, posY, posZ) / doubleExplosionSize;
    Vec3d vec3d = new Vec3d(posX, posY, posZ);
    double blockDensity = entity.world.getBlockDensity(vec3d, entity.getEntityBoundingBox());
    double v = (1.0D - distancedsize) * blockDensity;
    float damage = (int)((v * v + v) / 2.0D * 7.0D * doubleExplosionSize + 1.0D);
    double finald = 1.0D;
    if (entity instanceof EntityLivingBase)
      finald = getBlastReduction((EntityLivingBase)entity, getDamageMultiplied(damage), new Explosion((World)mc.world, null, posX, posY, posZ, 6.0F, false, true)); 
    return (float)finald;
  }
  
  public static float getBlastReduction(EntityLivingBase entity, float damage, Explosion explosion) {
    if (entity instanceof EntityPlayer) {
      EntityPlayer ep = (EntityPlayer)entity;
      DamageSource ds = DamageSource.causeExplosionDamage(explosion);
      damage = CombatRules.getDamageAfterAbsorb(damage, ep.getTotalArmorValue(), (float)ep.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue());
      int k = EnchantmentHelper.getEnchantmentModifierDamage(ep.getArmorInventoryList(), ds);
      float f = MathHelper.clamp(k, 0.0F, 20.0F);
      damage *= 1.0F - f / 25.0F;
      if (entity.isPotionActive(Potion.getPotionById(11)))
        damage -= damage / 4.0F; 
      return damage;
    } 
    damage = CombatRules.getDamageAfterAbsorb(damage, entity.getTotalArmorValue(), (float)entity.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue());
    return damage;
  }
  
  private static float getDamageMultiplied(float damage) {
    int diff = mc.world.getDifficulty().getId();
    return damage * ((diff == 0) ? 0.0F : ((diff == 2) ? 1.0F : ((diff == 1) ? 0.5F : 1.5F)));
  }
  
  public static float calculateDamage(EntityEnderCrystal crystal, Entity entity) {
    return calculateDamage(crystal.posX, crystal.posY, crystal.posZ, entity);
  }
  
  private static void setYawAndPitch(float yaw1, float pitch1) {
    yaw = yaw1;
    pitch = pitch1;
    isSpoofingAngles = true;
  }
  
  private static void resetRotation() {
    if (isSpoofingAngles) {
      yaw = mc.player.rotationYaw;
      pitch = mc.player.rotationPitch;
      isSpoofingAngles = false;
    } 
  }
  
  public static double[] calculateLookAt(double px, double py, double pz, EntityPlayer me) {
    double dirx = me.posX - px;
    double diry = me.posY - py;
    double dirz = me.posZ - pz;
    double len = Math.sqrt(dirx * dirx + diry * diry + dirz * dirz);
    dirx /= len;
    diry /= len;
    dirz /= len;
    double pitch = Math.asin(diry);
    double yaw = Math.atan2(dirz, dirx);
    pitch = pitch * 180.0D / Math.PI;
    yaw = yaw * 180.0D / Math.PI;
    yaw += 90.0D;
    return new double[] { yaw, pitch };
  }
  
  public void onEnable() {
    ImpactPlus.EVENT_BUS.subscribe(this);
    this.isActive = false;
    if (this.chat.getValBoolean() && mc.player != null)
      Command.sendClientMessage("AutoCrystal §2ON"); 
  }
  
  public void onDisable() {
    ImpactPlus.EVENT_BUS.unsubscribe(this);
    this.render = null;
    this.renderEnt = null;
    resetRotation();
    this.isActive = false;
    if (this.chat.getValBoolean())
      Command.sendClientMessage("AutoCrystal §4OFF"); 
  }
}
