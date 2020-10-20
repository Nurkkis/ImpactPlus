package me.axua.impactplus;

import java.awt.Font;
import me.axua.impactplus.command.CommandManager;
import me.axua.impactplus.event.EventProcessor;
import me.axua.impactplus.friends.Friends;
import me.axua.impactplus.gui.clickgui.ClickGUI;
import me.axua.impactplus.gui.settings.SettingsManager;
import me.axua.impactplus.hud.HudComponentManager;
import me.axua.impactplus.macro.MacroManager;
import me.axua.impactplus.module.ModuleManager;
import me.axua.impactplus.util.CapeUtils;
import me.axua.impactplus.util.ConfigUtils;
import me.axua.impactplus.util.TpsUtils;
import me.axua.impactplus.util.font.CFontRenderer;
import me.axua.impactplus.util.font.SalFontRenderer;
import me.zero.alpine.EventBus;
import me.zero.alpine.EventManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

@Mod(modid = "impactplus", name = "Impact+", version = "v2.4", clientSideOnly = true)
public class ImpactPlus {
  public static final String MODID = "impactplus";
  
  public static String MODNAME = "Impact+";
  
  public static final String MODVER = "v2.4";
  
  public static final String FORGENAME = "Impact+";
  
  public static final Logger log = LogManager.getLogger(MODNAME);
  
  public ClickGUI clickGui;
  
  public SettingsManager settingsManager;
  
  public Friends friends;
  
  public ModuleManager moduleManager;
  
  public ConfigUtils configUtils;
  
  public CapeUtils capeUtils;
  
  public MacroManager macroManager;
  
  EventProcessor eventProcessor;
  
  public static CFontRenderer fontRenderer;
  
  public static final EventBus EVENT_BUS = (EventBus)new EventManager();
  
  public static SalFontRenderer CustomFont2;
  
  @Instance
  private static ImpactPlus INSTANCE;
  
  public ImpactPlus() {
    INSTANCE = this;
  }
  
  @EventHandler
  public void preInit(FMLPreInitializationEvent event) {}
  
  @EventHandler
  public void init(FMLInitializationEvent event) {
    this.eventProcessor = new EventProcessor();
    this.eventProcessor.init();
    fontRenderer = new CFontRenderer(new Font("Arial", 0, 20), true, false);
    TpsUtils tpsUtils = new TpsUtils();
    this.settingsManager = new SettingsManager();
    log.info("Settings initialized!");
    this.friends = new Friends();
    log.info("Friends initialized!");
    this.moduleManager = new ModuleManager();
    log.info("Modules initialized!");
    this.clickGui = new ClickGUI();
    HudComponentManager hudComponentManager = new HudComponentManager(0.0D, 0.0D, this.clickGui);
    log.info("ClickGUI initialized!");
    this.macroManager = new MacroManager();
    log.info("Macros initialized!");
    this.configUtils = new ConfigUtils();
    Runtime.getRuntime().addShutdownHook(new ShutDown());
    log.info("Config loaded!");
    CommandManager.initCommands();
    log.info("Commands initialized!");
    log.info("Initialization complete!\n");
    CustomFont2 = new SalFontRenderer("Muli-SemiBold.ttf", 17.0F);
  }
  
  @EventHandler
  public void postInit(FMLPostInitializationEvent event) {
    Display.setTitle(MODNAME + " " + "v2.4");
    this.capeUtils = new CapeUtils();
    log.info("Capes initialised!");
    log.info("PostInitialization complete!\n");
  }
  
  public static ImpactPlus getInstance() {
    return INSTANCE;
  }
}
