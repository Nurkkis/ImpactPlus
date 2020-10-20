//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.module.modules.misc;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.command.Command;
import me.axua.impactplus.gui.settings.Setting;
import me.axua.impactplus.module.Module;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.init.SoundEvents;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;

public class StashFinder extends Module {
  private ArrayList<BlockPos> chestPositions;
  
  private Map<Long, Integer> stashMap;
  
  public Setting chests;
  
  public Setting file;
  
  public Setting shulker;
  
  public Setting sound;
  
  public StashFinder() {
    super("StashFinder", Module.Category.MISC, "Logs chest and shulker locations (Kinda broken)");
    this.chestPositions = new ArrayList<>();
    this.stashMap = new HashMap<>();
  }
  
  public void setup() {
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.chests = new Setting("Chests", this, 10.0D, 2.0D, 20.0D, true, "StashFinderChests"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.file = new Setting("Log to file", this, true, "StashFinderFile"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.shulker = new Setting("Log shulkers", this, true, "StashFinderShulker"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.sound = new Setting("Play Sound", this, true, "StashFinderSound"));
  }
  
  public void onEnable() {
    this.chestPositions.clear();
    this.stashMap.clear();
  }
  
  public void onUpdate() {
    for (TileEntity tileEntity : mc.world.loadedTileEntityList) {
      BlockPos pos = tileEntity.getPos();
      if (tileEntity instanceof net.minecraft.tileentity.TileEntityChest || tileEntity instanceof net.minecraft.tileentity.TileEntityShulkerBox) {
        boolean alreadyAdded = false;
        for (BlockPos p : this.chestPositions) {
          if (p.equals(pos))
            alreadyAdded = true; 
        } 
        if (alreadyAdded)
          continue; 
        this.chestPositions.add(pos);
        int chunkX = pos.getX() / 16;
        int chunkZ = pos.getZ() / 16;
        long chunk = ChunkPos.asLong(chunkX, chunkZ);
        if (!this.stashMap.containsKey(Long.valueOf(chunk)))
          this.stashMap.put(Long.valueOf(chunk), Integer.valueOf(0)); 
        int DENSITY = this.chests.getValInt();
        int count = ((Integer)this.stashMap.get(Long.valueOf(chunk))).intValue() + 1;
        if (this.shulker.getValBoolean() && tileEntity instanceof net.minecraft.tileentity.TileEntityShulkerBox && count < DENSITY)
          count = DENSITY; 
        this.stashMap.put(Long.valueOf(chunk), Integer.valueOf(count));
        if (count == DENSITY) {
          if (tileEntity instanceof net.minecraft.tileentity.TileEntityShulkerBox) {
            Command.sendClientMessage("1 or more Shulker Boxs found at " + pos.toString());
          } else {
            Command.sendClientMessage(count + " or more Chests found at " + pos.toString());
          } 
          if (this.sound.getValBoolean())
            mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getRecord(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F)); 
          if (this.file.getValBoolean())
            try {
              BufferedWriter writer = new BufferedWriter(new FileWriter("ImpactPlus\\StashFinder.txt", true));
              String line = "";
              Calendar calendar = Calendar.getInstance();
              SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
              line = line + "[" + formatter.format(calendar.getTime()) + "|";
              if (mc.getCurrentServerData() != null)
                line = line + (mc.getCurrentServerData()).serverIP + "|"; 
              switch (mc.player.dimension) {
                case 0:
                  line = line + "Overworld";
                  break;
                case 1:
                  line = line + "End";
                  break;
                case -1:
                  line = line + "Nether";
                  break;
              } 
              line = line + "] ";
              if (tileEntity instanceof net.minecraft.tileentity.TileEntityShulkerBox) {
                line = line + "Shulker Box found at " + pos.toString();
              } else {
                line = line + count + " or more Chests found at " + pos.toString();
              } 
              writer.write(line);
              writer.newLine();
              writer.close();
            } catch (IOException e) {
              e.printStackTrace();
            }  
        } 
      } 
    } 
  }
}
