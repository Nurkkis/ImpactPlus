//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.module.modules.combat;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.friends.Friends;
import me.axua.impactplus.gui.settings.Setting;
import me.axua.impactplus.module.Module;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class AutoTrap extends Module {
  Setting rotate;
  
  Setting ec;
  
  Setting range;
  
  Setting bpt;
  
  int blocksPlaced;
  
  public AutoTrap() {
    super("AutoTrap", Module.Category.COMBAT, "Traps nearby players");
  }
  
  public void setup() {
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.rotate = new Setting("Rotate", this, true, "AutoTrapRotate"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.ec = new Setting("UseEchests", this, false, "AutoTrapUseEchests"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.range = new Setting("Range", this, 5.0D, 0.0D, 10.0D, false, "AutoTrapRange"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.bpt = new Setting("BlocksPerTick", this, 8.0D, 1.0D, 15.0D, true, "AutoTrapBPT"));
  }
  
  public void onUpdate() {
    mc.world.loadedEntityList.stream()
      .filter(e -> e instanceof net.minecraft.entity.player.EntityPlayer)
      .filter(e -> (mc.player.getDistance(e) <= this.range.getValDouble()))
      .filter(e -> (e != mc.player))
      .filter(e -> !Friends.isFriend(e.getName()))
      .sorted(Comparator.comparing(e -> Float.valueOf(mc.player.getDistance(e))))
      .forEach(e -> {
          Vec3d vec = Surround.getInterpolatedPos(e, mc.getRenderPartialTicks());
          BlockPos playerPos = new BlockPos(vec);
          BlockPos x = playerPos.add(1, 0, 0);
          BlockPos xMinus = playerPos.add(-1, 0, 0);
          BlockPos z = playerPos.add(0, 0, 1);
          BlockPos zMinus = playerPos.add(0, 0, -1);
          BlockPos up = playerPos.add(0, 2, 0);
          BlockPos xUp = x.up();
          BlockPos xMinusUp = xMinus.up();
          BlockPos zUp = z.up();
          BlockPos zMinusUp = zMinus.up();
          BlockPos xUp2 = xUp.up();
          BlockPos xMinusUp2 = xMinusUp.up();
          BlockPos zUp2 = zUp.up();
          BlockPos zMinusUp2 = zMinusUp.up();
          int newSlot = -1;
          for (int i = 0; i < 9; i++) {
            ItemStack stack = mc.player.inventory.getStackInSlot(i);
            if (stack != ItemStack.EMPTY && stack.getItem() instanceof ItemBlock) {
              Block block = ((ItemBlock)stack.getItem()).getBlock();
              if (!this.ec.getValBoolean() ? !(block instanceof net.minecraft.block.BlockObsidian) : (!(block instanceof net.minecraft.block.BlockObsidian) && !(block instanceof net.minecraft.block.BlockEnderChest))) {
                newSlot = i;
                break;
              } 
            } 
          } 
          if (newSlot == -1)
            return; 
          int oldSlot = mc.player.inventory.currentItem;
          mc.player.inventory.currentItem = newSlot;
          this.blocksPlaced = 0;
          if (this.blocksPlaced > this.bpt.getValDouble()) {
            this.blocksPlaced = 0;
            return;
          } 
          if (shouldPlace(x)) {
            Surround.placeBlockScaffold(x, this.rotate.getValBoolean());
            this.blocksPlaced++;
          } 
          if (shouldPlace(xMinus)) {
            Surround.placeBlockScaffold(xMinus, this.rotate.getValBoolean());
            this.blocksPlaced++;
          } 
          if (shouldPlace(z)) {
            Surround.placeBlockScaffold(z, this.rotate.getValBoolean());
            this.blocksPlaced++;
          } 
          if (shouldPlace(zMinus)) {
            Surround.placeBlockScaffold(zMinus, this.rotate.getValBoolean());
            this.blocksPlaced++;
          } 
          if (shouldPlace(xUp)) {
            Surround.placeBlockScaffold(xUp, this.rotate.getValBoolean());
            this.blocksPlaced++;
          } 
          if (shouldPlace(xMinusUp)) {
            Surround.placeBlockScaffold(xMinusUp, this.rotate.getValBoolean());
            this.blocksPlaced++;
          } 
          if (shouldPlace(zUp)) {
            Surround.placeBlockScaffold(zUp, this.rotate.getValBoolean());
            this.blocksPlaced++;
          } 
          if (shouldPlace(zMinusUp)) {
            Surround.placeBlockScaffold(zMinusUp, this.rotate.getValBoolean());
            this.blocksPlaced++;
          } 
          if (shouldPlace(xUp2)) {
            Surround.placeBlockScaffold(xUp2, this.rotate.getValBoolean());
            this.blocksPlaced++;
          } 
          if (shouldPlace(xMinusUp2)) {
            Surround.placeBlockScaffold(xMinusUp2, this.rotate.getValBoolean());
            this.blocksPlaced++;
          } 
          if (shouldPlace(zUp2)) {
            Surround.placeBlockScaffold(zUp2, this.rotate.getValBoolean());
            this.blocksPlaced++;
          } 
          if (shouldPlace(zMinusUp2)) {
            Surround.placeBlockScaffold(zMinusUp2, this.rotate.getValBoolean());
            this.blocksPlaced++;
          } 
          if (shouldPlace(up)) {
            Surround.placeBlockScaffold(up, this.rotate.getValBoolean());
            this.blocksPlaced++;
          } 
          mc.player.inventory.currentItem = oldSlot;
        });
  }
  
  private boolean shouldPlace(BlockPos pos) {
    List<Entity> entities = (List<Entity>)mc.world.getEntitiesWithinAABBExcludingEntity(null, new AxisAlignedBB(pos)).stream().filter(e -> !(e instanceof net.minecraft.entity.item.EntityItem)).filter(e -> !(e instanceof net.minecraft.entity.item.EntityXPOrb)).collect(Collectors.toList());
    boolean a = entities.isEmpty();
    boolean b = mc.world.getBlockState(pos).getMaterial().isReplaceable();
    boolean c = (this.blocksPlaced < this.bpt.getValDouble());
    return (a && b && c);
  }
}
