//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.mixin.mixins;

import me.axua.impactplus.module.ModuleManager;
import me.axua.impactplus.module.modules.render.ShulkerPreview;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = {GuiScreen.class}, priority = 9999)
public class MixinGuiScreen {
  @Shadow
  public Minecraft mc;
  
  @Inject(method = {"Lnet/minecraft/client/gui/GuiScreen;drawWorldBackground(I)V"}, at = {@At("HEAD")}, cancellable = true)
  private void drawWorldBackgroundWrapper(int tint, CallbackInfo ci) {
    if (this.mc.world != null && ModuleManager.isModuleEnabled("CleanGui"))
      ci.cancel(); 
  }
  
  private void drawGradientRectP(int left, int top, int right, int bottom, int startColor, int endColor) {
    float f = (startColor >> 24 & 0xFF) / 255.0F;
    float f1 = (startColor >> 16 & 0xFF) / 255.0F;
    float f2 = (startColor >> 8 & 0xFF) / 255.0F;
    float f3 = (startColor & 0xFF) / 255.0F;
    float f4 = (endColor >> 24 & 0xFF) / 255.0F;
    float f5 = (endColor >> 16 & 0xFF) / 255.0F;
    float f6 = (endColor >> 8 & 0xFF) / 255.0F;
    float f7 = (endColor & 0xFF) / 255.0F;
    GlStateManager.disableTexture2D();
    GlStateManager.enableBlend();
    GlStateManager.disableAlpha();
    GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
    GlStateManager.shadeModel(7425);
    Tessellator tessellator = Tessellator.getInstance();
    BufferBuilder bufferbuilder = tessellator.getBuffer();
    bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
    bufferbuilder.pos(right, top, 300.0D).color(f1, f2, f3, f).endVertex();
    bufferbuilder.pos(left, top, 300.0D).color(f1, f2, f3, f).endVertex();
    bufferbuilder.pos(left, bottom, 300.0D).color(f5, f6, f7, f4).endVertex();
    bufferbuilder.pos(right, bottom, 300.0D).color(f5, f6, f7, f4).endVertex();
    tessellator.draw();
    GlStateManager.shadeModel(7424);
    GlStateManager.disableBlend();
    GlStateManager.enableAlpha();
    GlStateManager.enableTexture2D();
  }
  
  @Inject(method = {"renderToolTip"}, at = {@At("HEAD")}, cancellable = true)
  public void renderToolTip(ItemStack is, int x, int y, CallbackInfo ci) {
    if (ModuleManager.isModuleEnabled("ShulkerPreview") && is.getItem() instanceof net.minecraft.item.ItemShulkerBox) {
      NBTTagCompound tagCompound = is.getTagCompound();
      if (tagCompound != null && tagCompound.hasKey("BlockEntityTag", 10)) {
        NBTTagCompound blockEntityTag = tagCompound.getCompoundTag("BlockEntityTag");
        if (blockEntityTag.hasKey("Items", 9)) {
          ci.cancel();
          ShulkerPreview.nbt = blockEntityTag;
          ShulkerPreview.itemStack = is;
          ShulkerPreview.active = true;
          ShulkerPreview.pinned = Keyboard.isKeyDown(42);
          if (!ShulkerPreview.pinned) {
            ShulkerPreview.drawX = x;
            ShulkerPreview.drawY = y;
          } 
        } 
      } 
    } 
  }
}
