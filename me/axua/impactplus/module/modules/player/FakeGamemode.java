//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.module.modules.player;

import java.util.ArrayList;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.gui.settings.Setting;
import me.axua.impactplus.module.Module;
import net.minecraft.world.GameType;

public class FakeGamemode extends Module {
  Setting mode;
  
  private GameType gameType;
  
  public FakeGamemode() {
    super("FakeGamemode", Module.Category.PLAYER, "Fakes your current gamemode client side");
  }
  
  public void setup() {
    ArrayList<String> modes = new ArrayList<>();
    modes.add("Survival");
    modes.add("Adventure");
    modes.add("Creative");
    modes.add("Spectator");
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.mode = new Setting("Mode", this, "Box", modes, "FakeGamemodeMode"));
  }
  
  public void onUpdate() {
    if (mc.player == null)
      return; 
    if (this.mode.getValString().equalsIgnoreCase("Creative")) {
      mc.playerController.setGameType(this.gameType);
      mc.playerController.setGameType(GameType.CREATIVE);
    } else if (this.mode.getValString().equalsIgnoreCase("Survival")) {
      mc.playerController.setGameType(this.gameType);
      mc.playerController.setGameType(GameType.SURVIVAL);
    } else if (this.mode.getValString().equalsIgnoreCase("Adventure")) {
      mc.playerController.setGameType(this.gameType);
      mc.playerController.setGameType(GameType.ADVENTURE);
    } else if (this.mode.getValString().equalsIgnoreCase("Spectator")) {
      mc.playerController.setGameType(this.gameType);
      mc.playerController.setGameType(GameType.SPECTATOR);
    } 
  }
  
  public void onEnable() {
    if (mc.player == null) {
      disable();
    } else {
      this.gameType = mc.playerController.getCurrentGameType();
    } 
  }
  
  public void onDisable() {
    if (mc.player == null)
      return; 
    mc.playerController.setGameType(this.gameType);
  }
}
