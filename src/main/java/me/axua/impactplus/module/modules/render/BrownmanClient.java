//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.module.modules.render;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.gui.settings.Setting;
import me.axua.impactplus.module.Module;
import net.minecraft.client.gui.Gui;

public class BrownmanClient extends Module {
  Setting x;
  
  Setting y;
  
  public BrownmanClient() {
    super("BrownmanClient", Module.Category.RENDER, "BrownmenClient Hud");
    setDrawn(false);
  }
  
  public void setup() {
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.x = new Setting("X", this, 3.0D, 0.0D, 1000.0D, true, "BrownmanClientHudX"));
    (ImpactPlus.getInstance()).settingsManager.rSetting(this.y = new Setting("Y", this, 150.0D, 0.0D, 1000.0D, true, "BrownmanClientHudY"));
  }
  
  public void onRender() {
    Gui.drawRect(this.x.getValInt(), this.y.getValInt(), this.x.getValInt() + 162, this.y.getValInt() + 12, -10740224);
    mc.fontRenderer.drawString("ï½‚ï½’ï½?ï½—ï½Žï½?ï½?ï½Ž " + ChatFormatting.BOLD + "client-b2019.2.0", this.x.getValInt() + 3, this.y.getValInt() + 3, -16777216);
    mc.fontRenderer.drawString("ï½‚ï½’ï½?ï½—ï½Žï½?ï½?ï½Ž " + ChatFormatting.BOLD + "client-b2019.2.0", this.x.getValInt() + 2, this.y.getValInt() + 2, -1);
    mc.fontRenderer.drawStringWithShadow("handtooltip", (this.x.getValInt() + 2), (this.y.getValInt() + 16), -13447886);
    mc.fontRenderer.drawStringWithShadow("peekbypass [drop]", (this.x.getValInt() + 2), (this.y.getValInt() + 26), -13447886);
    mc.fontRenderer.drawStringWithShadow("packetafk", (this.x.getValInt() + 2), (this.y.getValInt() + 36), -13447886);
  }
}
