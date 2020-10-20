//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.event;

import com.google.common.collect.Maps;
import com.mojang.realmsclient.gui.ChatFormatting;
import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.command.Command;
import me.axua.impactplus.command.CommandManager;
import me.axua.impactplus.event.events.PacketEvent;
import me.axua.impactplus.event.events.PlayerJoinEvent;
import me.axua.impactplus.event.events.PlayerLeaveEvent;
import me.axua.impactplus.event.events.RenderEvent2;
import me.axua.impactplus.gui.clickgui.Panel;
import me.axua.impactplus.hud.HudComponentManager;
import me.axua.impactplus.macro.Macro;
import me.axua.impactplus.module.ModuleManager;
import me.axua.impactplus.module.modules.render.ShulkerPreview;
import me.axua.impactplus.util.CustomTessellator;
import me.axua.impactplus.util.EntityUtil;
import me.axua.impactplus.util.Wrapper;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketPlayerListItem;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.client.config.GuiUtils;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class EventProcessor {
  public static EventProcessor INSTANCE;
  
  Minecraft mc = Minecraft.getMinecraft();
  
  CommandManager commandManager = new CommandManager();
  
  float hue = 0.0F;
  
  Color c;
  
  int rgb;
  
  int speed = 2;
  
  @EventHandler
  private Listener<PacketEvent.Receive> receiveListener;
  
  private final Map<String, String> uuidNameCache;
  
  public int getRgb() {
    return this.rgb;
  }
  
  public Color getC() {
    return this.c;
  }
  
  public void setRainbowSpeed(int s) {
    this.speed = s;
  }
  
  public int getRainbowSpeed() {
    return this.speed;
  }
  
  @SubscribeEvent
  public void onTick(TickEvent.ClientTickEvent event) {
    this.c = Color.getHSBColor(this.hue, 1.0F, 1.0F);
    this.rgb = Color.HSBtoRGB(this.hue, 1.0F, 1.0F);
    this.hue += this.speed / 2000.0F;
    if (this.mc.player != null)
      ModuleManager.onUpdate(); 
  }
  
  @SubscribeEvent
  public void onRender(RenderGameOverlayEvent.Post event) {
    ImpactPlus.EVENT_BUS.post(event);
    if (event.getType() == RenderGameOverlayEvent.ElementType.HOTBAR) {
      ModuleManager.onRender();
      for (Panel p : HudComponentManager.hudComponents) {
        if (p.isHudComponent && p.isHudComponentPinned && p.visible && !(this.mc.currentScreen instanceof me.axua.impactplus.gui.clickgui.ClickGUI))
          p.drawHud(); 
      } 
    } 
  }
  
  @SubscribeEvent
  public void onGuiRenderScreen(GuiScreenEvent event) {
    if (ShulkerPreview.active || ShulkerPreview.pinned) {
      NonNullList<ItemStack> nonnulllist = NonNullList.withSize(27, ItemStack.EMPTY);
      ItemStackHelper.loadAllItems(ShulkerPreview.nbt, nonnulllist);
      GlStateManager.enableBlend();
      GlStateManager.disableRescaleNormal();
      RenderHelper.disableStandardItemLighting();
      GlStateManager.disableLighting();
      GlStateManager.disableDepth();
      (this.mc.getRenderItem()).zLevel = 300.0F;
      int oldX = ShulkerPreview.drawX;
      int oldY = ShulkerPreview.drawY;
      Gui.drawRect(oldX + 9, oldY - 14, oldX + 173, oldY + 52, -1441722095);
      this.mc.renderEngine.bindTexture(new ResourceLocation("textures/gui/container/shulker_box.png"));
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.ingameGUI.drawTexturedModalRect(oldX + 10, oldY - 4, 7, 17, 162, 54);
      this.mc.fontRenderer.drawString(ShulkerPreview.itemStack.getDisplayName(), oldX + 12, oldY - 12, 16777215);
      GlStateManager.enableBlend();
      GlStateManager.enableTexture2D();
      GlStateManager.enableLighting();
      GlStateManager.enableDepth();
      RenderHelper.enableGUIStandardItemLighting();
      for (int i = 0; i < nonnulllist.size(); i++) {
        int iX = oldX + i % 9 * 18 + 11;
        int iY = oldY + i / 9 * 18 - 11 + 8;
        ItemStack itemStack = (ItemStack)nonnulllist.get(i);
        this.mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack, iX, iY);
        this.mc.getRenderItem().renderItemOverlayIntoGUI(this.mc.fontRenderer, itemStack, iX, iY, null);
        if (ShulkerPreview.pinned && 
          isPointInRegion(iX, iY, 18, 18, ShulkerPreview.mouseX, ShulkerPreview.mouseY)) {
          FontRenderer font = itemStack.getItem().getFontRenderer(itemStack);
          GuiUtils.preItemToolTip(itemStack);
          event.getGui().drawHoveringText(event.getGui().getItemToolTip(itemStack), iX, iY);
          GuiUtils.postItemToolTip();
        } 
      } 
      RenderHelper.disableStandardItemLighting();
      (this.mc.getRenderItem()).zLevel = 0.0F;
      GlStateManager.enableLighting();
      GlStateManager.enableDepth();
      RenderHelper.enableStandardItemLighting();
      GlStateManager.enableRescaleNormal();
      ShulkerPreview.active = false;
    } 
  }
  
  @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
  public void onKeyInput(InputEvent.KeyInputEvent event) {
    if (Keyboard.getEventKeyState()) {
      if (Keyboard.getEventKey() == 0 || Keyboard.getEventKey() == 0)
        return; 
      ModuleManager.onBind(Keyboard.getEventKey());
      (ImpactPlus.getInstance()).macroManager.getMacros().forEach(m -> {
            if (m.getKey() == Keyboard.getEventKey())
              m.onMacro(); 
          });
    } 
  }
  
  @SubscribeEvent
  public void onMouseInput(InputEvent.MouseInputEvent event) {
    if (Mouse.getEventButtonState())
      ImpactPlus.EVENT_BUS.post(event); 
  }
  
  @SubscribeEvent(priority = EventPriority.HIGHEST)
  public void onChatSent(ClientChatEvent event) {
    if (event.getMessage().startsWith(Command.getPrefix())) {
      event.setCanceled(true);
      try {
        this.mc.ingameGUI.getChatGUI().addToSentMessages(event.getMessage());
        this.commandManager.callCommand(event.getMessage().substring(1));
      } catch (Exception e) {
        e.printStackTrace();
        Command.sendClientMessage(ChatFormatting.DARK_RED + "Error: " + e.getMessage());
      } 
    } 
  }
  
  @SubscribeEvent
  public void onRenderScreen(RenderGameOverlayEvent.Text event) {
    ImpactPlus.EVENT_BUS.post(event);
  }
  
  @SubscribeEvent
  public void onChatReceived(ClientChatReceivedEvent event) {
    ImpactPlus.EVENT_BUS.post(event);
  }
  
  @SubscribeEvent
  public void onAttackEntity(AttackEntityEvent event) {
    ImpactPlus.EVENT_BUS.post(event);
  }
  
  @SubscribeEvent
  public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
    ImpactPlus.EVENT_BUS.post(event);
  }
  
  @SubscribeEvent
  public void onDrawBlockHighlight(DrawBlockHighlightEvent event) {
    ImpactPlus.EVENT_BUS.post(event);
  }
  
  @SubscribeEvent
  public void onRenderBlockOverlay(RenderBlockOverlayEvent event) {
    ImpactPlus.EVENT_BUS.post(event);
  }
  
  @SubscribeEvent
  public void onLivingDamage(LivingDamageEvent event) {
    ImpactPlus.EVENT_BUS.post(event);
  }
  
  @SubscribeEvent
  public void onLivingEntityUseItemFinish(LivingEntityUseItemEvent.Finish event) {
    ImpactPlus.EVENT_BUS.post(event);
  }
  
  @SubscribeEvent
  public void onInputUpdate(InputUpdateEvent event) {
    ImpactPlus.EVENT_BUS.post(event);
  }
  
  @SubscribeEvent
  public void onLivingDeath(LivingDeathEvent event) {
    ImpactPlus.EVENT_BUS.post(event);
  }
  
  @SubscribeEvent
  public void onWorldUnload(WorldEvent.Unload event) {
    ImpactPlus.EVENT_BUS.post(event);
  }
  
  @SubscribeEvent
  public void onWorldLoad(WorldEvent.Load event) {
    ImpactPlus.EVENT_BUS.post(event);
  }
  
  public EventProcessor() {
    this.receiveListener = new Listener(event -> {
          if (event.getPacket() instanceof SPacketPlayerListItem) {
            SPacketPlayerListItem packet = (SPacketPlayerListItem)event.getPacket();
            if (packet.getAction() == SPacketPlayerListItem.Action.ADD_PLAYER)
              for (SPacketPlayerListItem.AddPlayerData playerData : packet.getEntries()) {
                if (playerData.getProfile().getId() != this.mc.session.getProfile().getId())
                  (new Thread(())).start(); 
              }  
            if (packet.getAction() == SPacketPlayerListItem.Action.REMOVE_PLAYER)
              for (SPacketPlayerListItem.AddPlayerData playerData : packet.getEntries()) {
                if (playerData.getProfile().getId() != this.mc.session.getProfile().getId())
                  (new Thread(())).start(); 
              }  
          } 
        }new java.util.function.Predicate[0]);
    this.uuidNameCache = Maps.newConcurrentMap();
    INSTANCE = this;
  }
  
  public String resolveName(String uuid) {
    uuid = uuid.replace("-", "");
    if (this.uuidNameCache.containsKey(uuid))
      return this.uuidNameCache.get(uuid); 
    String url = "https://api.mojang.com/user/profiles/" + uuid + "/names";
    try {
      String nameJson = IOUtils.toString(new URL(url));
      if (nameJson != null && nameJson.length() > 0) {
        JSONArray jsonArray = (JSONArray)JSONValue.parseWithException(nameJson);
        if (jsonArray != null) {
          JSONObject latestName = (JSONObject)jsonArray.get(jsonArray.size() - 1);
          if (latestName != null)
            return latestName.get("name").toString(); 
        } 
      } 
    } catch (IOException|org.json.simple.parser.ParseException e) {
      e.printStackTrace();
    } 
    return null;
  }
  
  public void init() {
    ImpactPlus.EVENT_BUS.subscribe(this);
    MinecraftForge.EVENT_BUS.register(this);
  }
  
  private boolean isMouseOverSlot(Slot slotIn, int mouseX, int mouseY) {
    return isPointInRegion(slotIn.xPos, slotIn.yPos, 16, 16, mouseX, mouseY);
  }
  
  protected boolean isPointInRegion(int rectX, int rectY, int rectWidth, int rectHeight, int pointX, int pointY) {
    int i = ShulkerPreview.guiLeft;
    int j = ShulkerPreview.guiTop;
    pointX -= i;
    pointY -= j;
    return (pointX >= rectX - 1 && pointX < rectX + rectWidth + 1 && pointY >= rectY - 1 && pointY < rectY + rectHeight + 1);
  }
  
  @SubscribeEvent
  public void onWorldRender(RenderWorldLastEvent event) {
    if (event.isCanceled())
      return; 
    ModuleManager.onWorldRender(event);
    GlStateManager.disableTexture2D();
    GlStateManager.enableBlend();
    GlStateManager.disableAlpha();
    GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
    GlStateManager.shadeModel(7425);
    GlStateManager.disableDepth();
    GlStateManager.glLineWidth(1.0F);
    Vec3d renderPos = EntityUtil.getInterpolatedPos((Entity)Wrapper.getPlayer(), event.getPartialTicks());
    RenderEvent2 l_Event = new RenderEvent2((Tessellator)CustomTessellator.INSTANCE, renderPos);
    l_Event.resetTranslation();
    ImpactPlus.EVENT_BUS.post(l_Event);
    GlStateManager.glLineWidth(1.0F);
    GlStateManager.shadeModel(7424);
    GlStateManager.disableBlend();
    GlStateManager.enableAlpha();
    GlStateManager.enableTexture2D();
    GlStateManager.enableDepth();
    GlStateManager.enableCull();
    CustomTessellator.releaseGL();
  }
}
