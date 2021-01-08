//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;

public class ImpactPlusTessellator extends Tessellator {
  public static ImpactPlusTessellator INSTANCE = new ImpactPlusTessellator();
  
  public ImpactPlusTessellator() {
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
    GlStateManager.color(1.0F, 1.0F, 1.0F);
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
  }
  
  public static void drawBox(AxisAlignedBB bb, int argb, int sides) {
    int a = argb >>> 24 & 0xFF;
    int r = argb >>> 16 & 0xFF;
    int g = argb >>> 8 & 0xFF;
    int b = argb & 0xFF;
    drawBox(INSTANCE.getBuffer(), bb, r, g, b, a, sides);
  }
  
  public static void drawBox(BlockPos blockPos, int argb, int sides) {
    int a = argb >>> 24 & 0xFF;
    int r = argb >>> 16 & 0xFF;
    int g = argb >>> 8 & 0xFF;
    int b = argb & 0xFF;
    drawBox(blockPos, r, g, b, a, sides);
  }
  
  public static void drawHalfBox(BlockPos blockPos, int argb, int sides) {
    int a = argb >>> 24 & 0xFF;
    int r = argb >>> 16 & 0xFF;
    int g = argb >>> 8 & 0xFF;
    int b = argb & 0xFF;
    drawHalfBox(blockPos, r, g, b, a, sides);
  }
  
  public static void drawHalfBox(float x, float y, float z, int argb, int sides) {
    int a = argb >>> 24 & 0xFF;
    int r = argb >>> 16 & 0xFF;
    int g = argb >>> 8 & 0xFF;
    int b = argb & 0xFF;
    drawBox(INSTANCE.getBuffer(), x, y, z, 1.0F, 0.5F, 1.0F, r, g, b, a, sides);
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
  
  public static void drawHalfBox(BlockPos blockPos, int r, int g, int b, int a, int sides) {
    drawBox(INSTANCE.getBuffer(), blockPos.getX(), blockPos.getY(), blockPos.getZ(), 1.0F, 0.5F, 1.0F, r, g, b, a, sides);
  }
  
  public static void drawBox(Vec3d vec3d, int r, int g, int b, int a, int sides) {
    drawBox(INSTANCE.getBuffer(), (float)vec3d.x, (float)vec3d.y, (float)vec3d.z, 1.0F, 1.0F, 1.0F, r, g, b, a, sides);
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
  
  public static void drawBox(BufferBuilder buffer, AxisAlignedBB bb, int r, int g, int b, int a, int sides) {
    if ((sides & 0x1) != 0) {
      buffer.pos(bb.maxX, bb.minY, bb.minZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.maxX, bb.minY, bb.maxZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.minX, bb.minY, bb.maxZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.minX, bb.minY, bb.minZ).color(r, g, b, a).endVertex();
    } 
    if ((sides & 0x2) != 0) {
      buffer.pos(bb.maxX, bb.maxY, bb.minZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.minX, bb.maxY, bb.minZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.minX, bb.maxY, bb.maxZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.maxX, bb.maxY, bb.maxZ).color(r, g, b, a).endVertex();
    } 
    if ((sides & 0x4) != 0) {
      buffer.pos(bb.maxX, bb.minY, bb.minZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.minX, bb.minY, bb.minZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.minX, bb.maxY, bb.minZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.maxX, bb.maxY, bb.minZ).color(r, g, b, a).endVertex();
    } 
    if ((sides & 0x8) != 0) {
      buffer.pos(bb.minX, bb.minY, bb.maxZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.maxX, bb.minY, bb.maxZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.maxX, bb.maxY, bb.maxZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.minX, bb.maxY, bb.maxZ).color(r, g, b, a).endVertex();
    } 
    if ((sides & 0x10) != 0) {
      buffer.pos(bb.minX, bb.minY, bb.minZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.minX, bb.minY, bb.maxZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.minX, bb.maxY, bb.maxZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.minX, bb.maxY, bb.minZ).color(r, g, b, a).endVertex();
    } 
    if ((sides & 0x20) != 0) {
      buffer.pos(bb.maxX, bb.minY, bb.maxZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.maxX, bb.minY, bb.minZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.maxX, bb.maxY, bb.minZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.maxX, bb.maxY, bb.maxZ).color(r, g, b, a).endVertex();
    } 
  }
  
  public static void drawSmallBox(Vec3d vec3d, int r, int g, int b, int a, int sides) {
    drawBox(INSTANCE.getBuffer(), (float)vec3d.x, (float)vec3d.y, (float)vec3d.z, 0.3F, 0.3F, 0.3F, r, g, b, a, sides);
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
  
  public static void drawBoundingBox(AxisAlignedBB bb, float width, int argb) {
    int a = argb >>> 24 & 0xFF;
    int r = argb >>> 16 & 0xFF;
    int g = argb >>> 8 & 0xFF;
    int b = argb & 0xFF;
    drawBoundingBox(bb, width, r, g, b, a);
  }
  
  public static void drawBoundingBox(AxisAlignedBB bb, float width, int red, int green, int blue, int alpha) {
    GlStateManager.pushMatrix();
    GlStateManager.enableBlend();
    GlStateManager.disableDepth();
    GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
    GlStateManager.disableTexture2D();
    GlStateManager.depthMask(false);
    GlStateManager.glLineWidth(width);
    BufferBuilder bufferbuilder = INSTANCE.getBuffer();
    bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
    bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
    render();
    GlStateManager.depthMask(true);
    GlStateManager.enableDepth();
    GlStateManager.enableTexture2D();
    GlStateManager.disableBlend();
    GlStateManager.popMatrix();
  }
  
  public static void drawBoundingBoxBottom(AxisAlignedBB bb, float width, int argb) {
    int a = argb >>> 24 & 0xFF;
    int r = argb >>> 16 & 0xFF;
    int g = argb >>> 8 & 0xFF;
    int b = argb & 0xFF;
    drawBoundingBoxBottom(bb, width, r, g, b, a);
  }
  
  public static void drawBoundingBoxBottom(AxisAlignedBB bb, float width, int red, int green, int blue, int alpha) {
    GlStateManager.pushMatrix();
    GlStateManager.enableBlend();
    GlStateManager.disableDepth();
    GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
    GlStateManager.disableTexture2D();
    GlStateManager.depthMask(false);
    GlStateManager.glLineWidth(width);
    BufferBuilder bufferbuilder = INSTANCE.getBuffer();
    bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
    bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
    render();
    GlStateManager.depthMask(true);
    GlStateManager.enableDepth();
    GlStateManager.enableTexture2D();
    GlStateManager.disableBlend();
    GlStateManager.popMatrix();
  }
  
  public static void drawBoundingBoxHalf(AxisAlignedBB bb, float width, int argb) {
    int a = argb >>> 24 & 0xFF;
    int r = argb >>> 16 & 0xFF;
    int g = argb >>> 8 & 0xFF;
    int b = argb & 0xFF;
    drawBoundingBoxHalf(bb, width, r, g, b, a);
  }
  
  public static void drawBoundingBoxHalf(AxisAlignedBB bb, float width, int red, int green, int blue, int alpha) {
    GlStateManager.pushMatrix();
    GlStateManager.enableBlend();
    GlStateManager.disableDepth();
    GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
    GlStateManager.disableTexture2D();
    GlStateManager.depthMask(false);
    GlStateManager.glLineWidth(width);
    BufferBuilder bufferbuilder = INSTANCE.getBuffer();
    bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
    bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.minX, bb.maxY - 0.5D, bb.minZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.minX, bb.maxY - 0.5D, bb.maxZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.maxX, bb.maxY - 0.5D, bb.maxZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.minX, bb.maxY - 0.5D, bb.maxZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.maxX, bb.maxY - 0.5D, bb.maxZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.maxX, bb.maxY - 0.5D, bb.minZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.maxX, bb.maxY - 0.5D, bb.minZ).color(red, green, blue, alpha).endVertex();
    bufferbuilder.pos(bb.minX, bb.maxY - 0.5D, bb.minZ).color(red, green, blue, alpha).endVertex();
    render();
    GlStateManager.depthMask(true);
    GlStateManager.enableDepth();
    GlStateManager.enableTexture2D();
    GlStateManager.disableBlend();
    GlStateManager.popMatrix();
  }
  
  public static void drawBoundingBoxBlockPos(BlockPos bp, float width, int r, int g, int b, int alpha) {
    GlStateManager.pushMatrix();
    GlStateManager.enableBlend();
    GlStateManager.disableDepth();
    GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
    GlStateManager.disableTexture2D();
    GlStateManager.depthMask(false);
    GL11.glEnable(2848);
    GL11.glHint(3154, 4354);
    GL11.glLineWidth(width);
    Minecraft mc = Minecraft.getMinecraft();
    double x = bp.getX() - (mc.getRenderManager()).viewerPosX;
    double y = bp.getY() - (mc.getRenderManager()).viewerPosY;
    double z = bp.getZ() - (mc.getRenderManager()).viewerPosZ;
    AxisAlignedBB bb = new AxisAlignedBB(x, y, z, x + 1.0D, y + 1.0D, z + 1.0D);
    Tessellator tessellator = Tessellator.getInstance();
    BufferBuilder bufferbuilder = tessellator.getBuffer();
    bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
    bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(r, g, b, alpha).endVertex();
    bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(r, g, b, alpha).endVertex();
    bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(r, g, b, alpha).endVertex();
    bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(r, g, b, alpha).endVertex();
    bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(r, g, b, alpha).endVertex();
    tessellator.draw();
    bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
    bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(r, g, b, alpha).endVertex();
    bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(r, g, b, alpha).endVertex();
    bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(r, g, b, alpha).endVertex();
    bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(r, g, b, alpha).endVertex();
    bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(r, g, b, alpha).endVertex();
    tessellator.draw();
    bufferbuilder.begin(1, DefaultVertexFormats.POSITION_COLOR);
    bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(r, g, b, alpha).endVertex();
    bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(r, g, b, alpha).endVertex();
    bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(r, g, b, alpha).endVertex();
    bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(r, g, b, alpha).endVertex();
    bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(r, g, b, alpha).endVertex();
    bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(r, g, b, alpha).endVertex();
    bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(r, g, b, alpha).endVertex();
    bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(r, g, b, alpha).endVertex();
    tessellator.draw();
    GL11.glDisable(2848);
    GlStateManager.depthMask(true);
    GlStateManager.enableDepth();
    GlStateManager.enableTexture2D();
    GlStateManager.disableBlend();
    GlStateManager.popMatrix();
  }
}
