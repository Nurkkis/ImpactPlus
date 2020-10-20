package me.axua.impactplus.mixin.mixins;

import me.axua.impactplus.ImpactPlus;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({GuiMainMenu.class})
public class MixinGuiMainMenu extends GuiScreen {
  String s = "Impact+ v2.4";
  
  @Inject(method = {"drawScreen"}, at = {@At("TAIL")}, cancellable = true)
  public void drawText(int mouseX, int mouseY, float partialTicks, CallbackInfo ci) {
    ImpactPlus.CustomFont2.drawStringWithShadow(this.s, 2.0F, 0.0F, -11184641);
  }
}
