//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.util;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.opengl.GL11;

public class CustomTessellator extends Tessellator {
  public static CustomTessellator INSTANCE = new CustomTessellator();
  
  public CustomTessellator() {
    super(2097152);
  }
  
  public static void prepare(int mode) {
    prepareGL();
    begin(mode);
  }
  
  public static void prepareGL() {
    GL11.glBlendFunc(770, 771);
    GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
    GlStateManager.glLineWidth(1.5F);
    GlStateManager.disableTexture2D();
    GlStateManager.depthMask(false);
    GlStateManager.enableBlend();
    GlStateManager.disableDepth();
    GlStateManager.disableLighting();
    GlStateManager.disableCull();
    GlStateManager.enableAlpha();
    GlStateManager.color(1.0F, 1.0F, 1.0F);
  }
  
  public static void begin(int mode) {
    INSTANCE.getBuffer().begin(mode, DefaultVertexFormats.POSITION_COLOR);
  }
  
  public static void release() {
    render();
    releaseGL();
  }
  
  public static void render() {
    INSTANCE.draw();
  }
  
  public static void releaseGL() {
    GlStateManager.enableCull();
    GlStateManager.depthMask(true);
    GlStateManager.enableTexture2D();
    GlStateManager.enableBlend();
    GlStateManager.enableDepth();
  }
  
  public static void drawBox(BlockPos blockPos, int argb, int sides) {
    int a = argb >>> 24 & 0xFF;
    int r = argb >>> 16 & 0xFF;
    int g = argb >>> 8 & 0xFF;
    int b = argb & 0xFF;
    drawBox(blockPos, r, g, b, a, sides);
  }
  
  public static void drawBox(float x, float y, float z, int argb, int sides) {
    int a = argb >>> 24 & 0xFF;
    int r = argb >>> 16 & 0xFF;
    int g = argb >>> 8 & 0xFF;
    int b = argb & 0xFF;
    drawBox(INSTANCE.getBuffer(), x, y, z, 1.0F, 1.0F, 1.0F, r, g, b, a, sides);
  }
  
  public static void drawBox(BlockPos blockPos, int r, int g, int b, int a, int sides) {
    drawBox(INSTANCE.getBuffer(), blockPos.getX(), blockPos.getY(), blockPos.getZ(), 1.0F, 1.0F, 1.0F, r, g, b, a, sides);
  }
  
  public static BufferBuilder getBufferBuilder() {
    return INSTANCE.getBuffer();
  }
  
  public static void drawBox(BufferBuilder buffer, float x, float y, float z, float w, float h, float d, int r, int g, int b, int a, int sides) {
    if ((sides & 0x1) != 0) {
      buffer.pos((x + w), y, z).color(r, g, b, a).endVertex();
      buffer.pos((x + w), y, (z + d)).color(r, g, b, a).endVertex();
      buffer.pos(x, y, (z + d)).color(r, g, b, a).endVertex();
      buffer.pos(x, y, z).color(r, g, b, a).endVertex();
    } 
    if ((sides & 0x2) != 0) {
      buffer.pos((x + w), (y + h), z).color(r, g, b, a).endVertex();
      buffer.pos(x, (y + h), z).color(r, g, b, a).endVertex();
      buffer.pos(x, (y + h), (z + d)).color(r, g, b, a).endVertex();
      buffer.pos((x + w), (y + h), (z + d)).color(r, g, b, a).endVertex();
    } 
    if ((sides & 0x4) != 0) {
      buffer.pos((x + w), y, z).color(r, g, b, a).endVertex();
      buffer.pos(x, y, z).color(r, g, b, a).endVertex();
      buffer.pos(x, (y + h), z).color(r, g, b, a).endVertex();
      buffer.pos((x + w), (y + h), z).color(r, g, b, a).endVertex();
    } 
    if ((sides & 0x8) != 0) {
      buffer.pos(x, y, (z + d)).color(r, g, b, a).endVertex();
      buffer.pos((x + w), y, (z + d)).color(r, g, b, a).endVertex();
      buffer.pos((x + w), (y + h), (z + d)).color(r, g, b, a).endVertex();
      buffer.pos(x, (y + h), (z + d)).color(r, g, b, a).endVertex();
    } 
    if ((sides & 0x10) != 0) {
      buffer.pos(x, y, z).color(r, g, b, a).endVertex();
      buffer.pos(x, y, (z + d)).color(r, g, b, a).endVertex();
      buffer.pos(x, (y + h), (z + d)).color(r, g, b, a).endVertex();
      buffer.pos(x, (y + h), z).color(r, g, b, a).endVertex();
    } 
    if ((sides & 0x20) != 0) {
      buffer.pos((x + w), y, (z + d)).color(r, g, b, a).endVertex();
      buffer.pos((x + w), y, z).color(r, g, b, a).endVertex();
      buffer.pos((x + w), (y + h), z).color(r, g, b, a).endVertex();
      buffer.pos((x + w), (y + h), (z + d)).color(r, g, b, a).endVertex();
    } 
  }
  
  public static void drawLines(BufferBuilder buffer, float x, float y, float z, float w, float h, float d, int r, int g, int b, int a, int sides) {
    if ((sides & 0x11) != 0) {
      buffer.pos(x, y, z).color(r, g, b, a).endVertex();
      buffer.pos(x, y, (z + d)).color(r, g, b, a).endVertex();
    } 
    if ((sides & 0x12) != 0) {
      buffer.pos(x, (y + h), z).color(r, g, b, a).endVertex();
      buffer.pos(x, (y + h), (z + d)).color(r, g, b, a).endVertex();
    } 
    if ((sides & 0x21) != 0) {
      buffer.pos((x + w), y, z).color(r, g, b, a).endVertex();
      buffer.pos((x + w), y, (z + d)).color(r, g, b, a).endVertex();
    } 
    if ((sides & 0x22) != 0) {
      buffer.pos((x + w), (y + h), z).color(r, g, b, a).endVertex();
      buffer.pos((x + w), (y + h), (z + d)).color(r, g, b, a).endVertex();
    } 
    if ((sides & 0x5) != 0) {
      buffer.pos(x, y, z).color(r, g, b, a).endVertex();
      buffer.pos((x + w), y, z).color(r, g, b, a).endVertex();
    } 
    if ((sides & 0x6) != 0) {
      buffer.pos(x, (y + h), z).color(r, g, b, a).endVertex();
      buffer.pos((x + w), (y + h), z).color(r, g, b, a).endVertex();
    } 
    if ((sides & 0x9) != 0) {
      buffer.pos(x, y, (z + d)).color(r, g, b, a).endVertex();
      buffer.pos((x + w), y, (z + d)).color(r, g, b, a).endVertex();
    } 
    if ((sides & 0xA) != 0) {
      buffer.pos(x, (y + h), (z + d)).color(r, g, b, a).endVertex();
      buffer.pos((x + w), (y + h), (z + d)).color(r, g, b, a).endVertex();
    } 
    if ((sides & 0x14) != 0) {
      buffer.pos(x, y, z).color(r, g, b, a).endVertex();
      buffer.pos(x, (y + h), z).color(r, g, b, a).endVertex();
    } 
    if ((sides & 0x24) != 0) {
      buffer.pos((x + w), y, z).color(r, g, b, a).endVertex();
      buffer.pos((x + w), (y + h), z).color(r, g, b, a).endVertex();
    } 
    if ((sides & 0x18) != 0) {
      buffer.pos(x, y, (z + d)).color(r, g, b, a).endVertex();
      buffer.pos(x, (y + h), (z + d)).color(r, g, b, a).endVertex();
    } 
    if ((sides & 0x28) != 0) {
      buffer.pos((x + w), y, (z + d)).color(r, g, b, a).endVertex();
      buffer.pos((x + w), (y + h), (z + d)).color(r, g, b, a).endVertex();
    } 
  }
}
