//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.util;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.command.Command;
import me.axua.impactplus.event.EventProcessor;
import me.axua.impactplus.friends.Friend;
import me.axua.impactplus.friends.Friends;
import me.axua.impactplus.gui.clickgui.ClickGUI;
import me.axua.impactplus.gui.clickgui.Panel;
import me.axua.impactplus.gui.settings.Setting;
import me.axua.impactplus.hud.HudComponentManager;
import me.axua.impactplus.macro.Macro;
import me.axua.impactplus.module.Module;
import me.axua.impactplus.module.ModuleManager;
import me.axua.impactplus.module.modules.chat.AutoEZ;
import me.axua.impactplus.module.modules.chat.AutoReply;
import me.axua.impactplus.util.font.CFontRenderer;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

public class ConfigUtils {
  Minecraft mc = Minecraft.getMinecraft();
  
  public File Osiris;
  
  public File Settings;
  
  public ConfigUtils() {
    this.Osiris = new File(this.mc.gameDir + File.separator + "ImpactPlus");
    if (!this.Osiris.exists())
      this.Osiris.mkdirs(); 
    this.Settings = new File(this.mc.gameDir + File.separator + "ImpactPlus" + File.separator + "Settings");
    if (!this.Settings.exists())
      this.Settings.mkdirs(); 
    loadMods();
    loadDrawn();
    loadSettingsList();
    loadBinds();
    loadFriends();
    loadGui();
    loadPrefix();
    loadRainbow();
    loadMacros();
    loadMsgs();
    loadAutoEZ();
    loadAutoReply();
    loadHudComponents();
    loadFont();
    loadClientname();
  }
  
  public void saveClientname() {
    try {
      File file = new File(this.Osiris.getAbsolutePath(), "ClientName.txt");
      BufferedWriter out = new BufferedWriter(new FileWriter(file));
      out.write(ImpactPlus.MODNAME);
      out.close();
    } catch (Exception exception) {}
  }
  
  public void loadClientname() {
    try {
      File file = new File(this.Osiris.getAbsolutePath(), "ClientName.txt");
      FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
      DataInputStream in = new DataInputStream(fstream);
      BufferedReader br = new BufferedReader(new InputStreamReader(in));
      String line;
      while ((line = br.readLine()) != null)
        ImpactPlus.MODNAME = line; 
      br.close();
    } catch (Exception var6) {
      var6.printStackTrace();
    } 
  }
  
  public void saveBinds() {
    try {
      File file = new File(this.Osiris.getAbsolutePath(), "Binds.txt");
      BufferedWriter out = new BufferedWriter(new FileWriter(file));
      Iterator<Module> var3 = ModuleManager.getModules().iterator();
      while (var3.hasNext()) {
        Module module = var3.next();
        out.write(module.getName() + ":" + Keyboard.getKeyName(module.getBind()));
        out.write("\r\n");
      } 
      out.close();
    } catch (Exception exception) {}
  }
  
  public void loadBinds() {
    try {
      File file = new File(this.Osiris.getAbsolutePath(), "Binds.txt");
      FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
      DataInputStream in = new DataInputStream(fstream);
      BufferedReader br = new BufferedReader(new InputStreamReader(in));
      String line;
      while ((line = br.readLine()) != null) {
        String curLine = line.trim();
        String name = curLine.split(":")[0];
        String bind = curLine.split(":")[1];
        for (Module m : ModuleManager.getModules()) {
          if (m != null && m.getName().equalsIgnoreCase(name))
            m.setBind(Keyboard.getKeyIndex(bind)); 
        } 
      } 
      br.close();
    } catch (Exception var11) {
      var11.printStackTrace();
    } 
  }
  
  public void saveMacros() {
    try {
      File file = new File(this.Osiris.getAbsolutePath(), "Macros.txt");
      BufferedWriter out = new BufferedWriter(new FileWriter(file));
      Iterator<Macro> var3 = (ImpactPlus.getInstance()).macroManager.getMacros().iterator();
      while (var3.hasNext()) {
        Macro m = var3.next();
        out.write(Keyboard.getKeyName(m.getKey()) + ":" + m.getValue().replace(" ", "_"));
        out.write("\r\n");
      } 
      out.close();
    } catch (Exception exception) {}
  }
  
  public void loadMacros() {
    try {
      File file = new File(this.Osiris.getAbsolutePath(), "Macros.txt");
      FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
      DataInputStream in = new DataInputStream(fstream);
      BufferedReader br = new BufferedReader(new InputStreamReader(in));
      String line;
      while ((line = br.readLine()) != null) {
        String curLine = line.trim();
        String bind = curLine.split(":")[0];
        String value = curLine.split(":")[1];
        (ImpactPlus.getInstance()).macroManager.addMacro(new Macro(Keyboard.getKeyIndex(bind), value.replace("_", " ")));
      } 
      br.close();
    } catch (Exception var11) {
      var11.printStackTrace();
    } 
  }
  
  public void saveMods() {
    try {
      File file = new File(this.Osiris.getAbsolutePath(), "EnabledModules.txt");
      BufferedWriter out = new BufferedWriter(new FileWriter(file));
      Iterator<Module> var3 = ModuleManager.getModules().iterator();
      while (var3.hasNext()) {
        Module module = var3.next();
        if (module.isEnabled()) {
          out.write(module.getName());
          out.write("\r\n");
        } 
      } 
      out.close();
    } catch (Exception exception) {}
  }
  
  public void saveFriends() {
    try {
      File file = new File(this.Osiris.getAbsolutePath(), "Friends.txt");
      BufferedWriter out = new BufferedWriter(new FileWriter(file));
      Iterator<Friend> var3 = Friends.getFriends().iterator();
      while (var3.hasNext()) {
        Friend f = var3.next();
        out.write(f.getName());
        out.write("\r\n");
      } 
      out.close();
    } catch (Exception exception) {}
  }
  
  public void loadFriends() {
    try {
      File file = new File(this.Osiris.getAbsolutePath(), "Friends.txt");
      FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
      DataInputStream in = new DataInputStream(fstream);
      BufferedReader br = new BufferedReader(new InputStreamReader(in));
      Friends.friends.clear();
      String line;
      while ((line = br.readLine()) != null)
        (ImpactPlus.getInstance()).friends.addFriend(line); 
      br.close();
    } catch (Exception var6) {
      var6.printStackTrace();
    } 
  }
  
  public void saveGui() {
    try {
      File file = new File(this.Osiris.getAbsolutePath(), "Gui.txt");
      BufferedWriter out = new BufferedWriter(new FileWriter(file));
      Iterator<Panel> var3 = ClickGUI.panels.iterator();
      while (var3.hasNext()) {
        Panel p = var3.next();
        out.write(p.title + ":" + p.x + ":" + p.y + ":" + p.extended);
        out.write("\r\n");
      } 
      out.close();
    } catch (Exception exception) {}
  }
  
  public void loadGui() {
    try {
      File file = new File(this.Osiris.getAbsolutePath(), "Gui.txt");
      FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
      DataInputStream in = new DataInputStream(fstream);
      BufferedReader br = new BufferedReader(new InputStreamReader(in));
      String line;
      while ((line = br.readLine()) != null) {
        String curLine = line.trim();
        String name = curLine.split(":")[0];
        String x = curLine.split(":")[1];
        String y = curLine.split(":")[2];
        String e = curLine.split(":")[3];
        double x1 = Double.parseDouble(x);
        double y1 = Double.parseDouble(y);
        boolean ext = Boolean.parseBoolean(e);
        Panel p = ClickGUI.getPanelByName(name);
        if (p != null) {
          p.x = x1;
          p.y = y1;
          p.extended = ext;
        } 
      } 
      br.close();
    } catch (Exception var17) {
      var17.printStackTrace();
    } 
  }
  
  public void saveHudComponents() {
    try {
      File file = new File(this.Osiris.getAbsolutePath(), "HudComponents.txt");
      BufferedWriter out = new BufferedWriter(new FileWriter(file));
      Iterator<Panel> var3 = HudComponentManager.hudComponents.iterator();
      while (var3.hasNext()) {
        Panel p = var3.next();
        out.write(p.title + ":" + p.x + ":" + p.y + ":" + p.extended + ":" + p.isHudComponentPinned);
        out.write("\r\n");
      } 
      out.close();
    } catch (Exception exception) {}
  }
  
  public void loadHudComponents() {
    try {
      File file = new File(this.Osiris.getAbsolutePath(), "HudComponents.txt");
      FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
      DataInputStream in = new DataInputStream(fstream);
      BufferedReader br = new BufferedReader(new InputStreamReader(in));
      String line;
      while ((line = br.readLine()) != null) {
        String curLine = line.trim();
        String name = curLine.split(":")[0];
        String x = curLine.split(":")[1];
        String y = curLine.split(":")[2];
        String e = curLine.split(":")[3];
        String pin = curLine.split(":")[4];
        double x1 = Double.parseDouble(x);
        double y1 = Double.parseDouble(y);
        boolean ex = Boolean.parseBoolean(e);
        boolean pinned = Boolean.parseBoolean(pin);
        Panel p = HudComponentManager.getHudComponentByName(name);
        if (p != null) {
          p.x = x1;
          p.y = y1;
          p.extended = ex;
          p.isHudComponentPinned = pinned;
        } 
      } 
      br.close();
    } catch (Exception var17) {
      var17.printStackTrace();
    } 
  }
  
  public void savePrefix() {
    try {
      File file = new File(this.Osiris.getAbsolutePath(), "CommandPrefix.txt");
      BufferedWriter out = new BufferedWriter(new FileWriter(file));
      out.write(Command.getPrefix());
      out.write("\r\n");
      out.close();
    } catch (Exception exception) {}
  }
  
  public void loadPrefix() {
    try {
      File file = new File(this.Osiris.getAbsolutePath(), "CommandPrefix.txt");
      FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
      DataInputStream in = new DataInputStream(fstream);
      BufferedReader br = new BufferedReader(new InputStreamReader(in));
      String line;
      while ((line = br.readLine()) != null)
        Command.setPrefix(line); 
      br.close();
    } catch (Exception var6) {
      var6.printStackTrace();
    } 
  }
  
  public void saveFont() {
    try {
      File file = new File(this.Osiris.getAbsolutePath(), "Font.txt");
      BufferedWriter out = new BufferedWriter(new FileWriter(file));
      out.write(ImpactPlus.fontRenderer.getFontName() + ":" + ImpactPlus.fontRenderer.getFontSize());
      out.write("\r\n");
      out.close();
    } catch (Exception exception) {}
  }
  
  public void loadFont() {
    try {
      File file = new File(this.Osiris.getAbsolutePath(), "Font.txt");
      FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
      DataInputStream in = new DataInputStream(fstream);
      BufferedReader br = new BufferedReader(new InputStreamReader(in));
      String line;
      while ((line = br.readLine()) != null) {
        String name = line.split(":")[0];
        String size = line.split(":")[1];
        int sizeInt = Integer.parseInt(size);
        ImpactPlus.fontRenderer = new CFontRenderer(new Font(name, 0, sizeInt), true, false);
        ImpactPlus.fontRenderer.setFont(new Font(name, 0, sizeInt));
        ImpactPlus.fontRenderer.setAntiAlias(true);
        ImpactPlus.fontRenderer.setFractionalMetrics(false);
        ImpactPlus.fontRenderer.setFontName(name);
        ImpactPlus.fontRenderer.setFontSize(sizeInt);
      } 
      br.close();
    } catch (Exception var6) {
      var6.printStackTrace();
    } 
  }
  
  public void saveAutoEZ() {
    try {
      File file = new File(this.Osiris.getAbsolutePath(), "AutoEzMessage.txt");
      BufferedWriter out = new BufferedWriter(new FileWriter(file));
      for (String s : AutoEZ.getAutoEzMessages()) {
        out.write(s);
        out.write("\r\n");
      } 
      out.close();
    } catch (Exception exception) {}
  }
  
  public void loadAutoEZ() {
    try {
      File file = new File(this.Osiris.getAbsolutePath(), "AutoEzMessage.txt");
      FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
      DataInputStream in = new DataInputStream(fstream);
      BufferedReader br = new BufferedReader(new InputStreamReader(in));
      String line;
      while ((line = br.readLine()) != null)
        AutoEZ.addAutoEzMessage(line); 
      br.close();
    } catch (Exception var6) {
      var6.printStackTrace();
    } 
  }
  
  public void saveAutoReply() {
    try {
      File file = new File(this.Osiris.getAbsolutePath(), "AutoReplyMessage.txt");
      BufferedWriter out = new BufferedWriter(new FileWriter(file));
      out.write(AutoReply.getReply());
      out.close();
    } catch (Exception exception) {}
  }
  
  public void loadAutoReply() {
    try {
      File file = new File(this.Osiris.getAbsolutePath(), "AutoReplyMessage.txt");
      FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
      DataInputStream in = new DataInputStream(fstream);
      BufferedReader br = new BufferedReader(new InputStreamReader(in));
      String line;
      while ((line = br.readLine()) != null)
        AutoReply.setReply(line); 
      br.close();
    } catch (Exception var6) {
      var6.printStackTrace();
    } 
  }
  
  public void saveRainbow() {
    try {
      File file = new File(this.Osiris.getAbsolutePath(), "RainbowSpeed.txt");
      BufferedWriter out = new BufferedWriter(new FileWriter(file));
      out.write(EventProcessor.INSTANCE.getRainbowSpeed() + "");
      out.close();
    } catch (Exception exception) {}
  }
  
  public void loadRainbow() {
    try {
      File file = new File(this.Osiris.getAbsolutePath(), "RainbowSpeed.txt");
      FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
      DataInputStream in = new DataInputStream(fstream);
      BufferedReader br = new BufferedReader(new InputStreamReader(in));
      String line;
      while ((line = br.readLine()) != null)
        EventProcessor.INSTANCE.setRainbowSpeed(Integer.parseInt(line)); 
      br.close();
    } catch (Exception var6) {
      var6.printStackTrace();
    } 
  }
  
  public void saveMsgs() {
    try {
      File file = new File(this.Osiris.getAbsolutePath(), "ClientMessages.txt");
      BufferedWriter out = new BufferedWriter(new FileWriter(file));
      out.write(Command.MsgWaterMark + "");
      out.write(",");
      out.write(Command.cf.getName());
      out.close();
    } catch (Exception exception) {}
  }
  
  public void loadMsgs() {
    try {
      File file = new File(this.Osiris.getAbsolutePath(), "ClientMessages.txt");
      FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
      DataInputStream in = new DataInputStream(fstream);
      BufferedReader br = new BufferedReader(new InputStreamReader(in));
      String line;
      while ((line = br.readLine()) != null) {
        String curLine = line.trim();
        String watermark = curLine.split(",")[0];
        String color = curLine.split(",")[1];
        boolean w = Boolean.parseBoolean(watermark);
        ChatFormatting c = ChatFormatting.getByName(color);
        Command.cf = c;
        Command.MsgWaterMark = w;
      } 
      br.close();
    } catch (Exception var11) {
      var11.printStackTrace();
    } 
  }
  
  public void saveDrawn() {
    try {
      File file = new File(this.Osiris.getAbsolutePath(), "Drawn.txt");
      BufferedWriter out = new BufferedWriter(new FileWriter(file));
      Iterator<Module> var3 = ModuleManager.getModules().iterator();
      while (var3.hasNext()) {
        Module module = var3.next();
        out.write(module.getName() + ":" + module.isDrawn());
        out.write("\r\n");
      } 
      out.close();
    } catch (Exception exception) {}
  }
  
  public void loadDrawn() {
    try {
      File file = new File(this.Osiris.getAbsolutePath(), "Drawn.txt");
      FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
      DataInputStream in = new DataInputStream(fstream);
      BufferedReader br = new BufferedReader(new InputStreamReader(in));
      String line;
      while ((line = br.readLine()) != null) {
        String curLine = line.trim();
        String name = curLine.split(":")[0];
        String isOn = curLine.split(":")[1];
        boolean drawn = Boolean.parseBoolean(isOn);
        for (Module m : ModuleManager.getModules()) {
          if (m.getName().equalsIgnoreCase(name))
            m.setDrawn(drawn); 
        } 
      } 
      br.close();
    } catch (Exception var11) {
      var11.printStackTrace();
    } 
  }
  
  public void writeCrash(String alah) {
    try {
      SimpleDateFormat format = new SimpleDateFormat("dd_MM_yyyy-HH_mm_ss");
      Date date = new Date();
      File file = new File(this.Osiris.getAbsolutePath(), "crashlog-".concat(format.format(date)).concat(".bruh"));
      BufferedWriter outWrite = new BufferedWriter(new FileWriter(file));
      outWrite.write(alah);
      outWrite.close();
    } catch (Exception exception) {}
  }
  
  public void loadMods() {
    try {
      File file = new File(this.Osiris.getAbsolutePath(), "EnabledModules.txt");
      FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
      DataInputStream in = new DataInputStream(fstream);
      BufferedReader br = new BufferedReader(new InputStreamReader(in));
      String line;
      while ((line = br.readLine()) != null) {
        Iterator<Module> var6 = ModuleManager.getModules().iterator();
        while (var6.hasNext()) {
          Module m = var6.next();
          if (m.getName().equals(line))
            m.enable(); 
        } 
      } 
      br.close();
    } catch (Exception var8) {
      var8.printStackTrace();
    } 
  }
  
  public void saveSettingsList() {
    try {
      File file = new File(this.Settings.getAbsolutePath(), "Number.txt");
      BufferedWriter out = new BufferedWriter(new FileWriter(file));
      Iterator<Setting> var3 = (ImpactPlus.getInstance()).settingsManager.getSettings().iterator();
      while (var3.hasNext()) {
        Setting i = var3.next();
        if (i.isSlider())
          out.write(i.getId() + ":" + i.getValDouble() + ":" + i.getParentMod().getName() + "\r\n"); 
      } 
      out.close();
    } catch (Exception exception) {}
    try {
      File file = new File(this.Settings.getAbsolutePath(), "Boolean.txt");
      BufferedWriter out = new BufferedWriter(new FileWriter(file));
      Iterator<Setting> var3 = (ImpactPlus.getInstance()).settingsManager.getSettings().iterator();
      while (var3.hasNext()) {
        Setting i = var3.next();
        if (i.isCheck())
          out.write(i.getId() + ":" + i.getValBoolean() + ":" + i.getParentMod().getName() + "\r\n"); 
      } 
      out.close();
    } catch (Exception exception) {}
    try {
      File file = new File(this.Settings.getAbsolutePath(), "String.txt");
      BufferedWriter out = new BufferedWriter(new FileWriter(file));
      Iterator<Setting> var3 = (ImpactPlus.getInstance()).settingsManager.getSettings().iterator();
      while (var3.hasNext()) {
        Setting i = var3.next();
        if (i.isCombo())
          out.write(i.getId() + ":" + i.getValString() + ":" + i.getParentMod().getName() + "\r\n"); 
      } 
      out.close();
    } catch (Exception exception) {}
    try {
      File file = new File(this.Settings.getAbsolutePath(), "Color.txt");
      BufferedWriter out = new BufferedWriter(new FileWriter(file));
      Iterator<Setting> var3 = (ImpactPlus.getInstance()).settingsManager.getSettings().iterator();
      while (var3.hasNext()) {
        Setting i = var3.next();
        if (i.isColorPicker())
          out.write(i.getId() + ":" + i.getValColor().getRGB() + ":" + i.getParentMod().getName() + "\r\n"); 
      } 
      out.close();
    } catch (Exception exception) {}
  }
  
  public void loadSettingsList() {
    try {
      File file = new File(this.Settings.getAbsolutePath(), "Number.txt");
      FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
      DataInputStream in = new DataInputStream(fstream);
      BufferedReader br = new BufferedReader(new InputStreamReader(in));
      String line;
      while ((line = br.readLine()) != null) {
        String curLine = line.trim();
        String name = curLine.split(":")[0];
        String isOn = curLine.split(":")[1];
        String m = curLine.split(":")[2];
        for (Module mm : ModuleManager.getModules()) {
          if (mm != null && mm.getName().equalsIgnoreCase(m)) {
            Setting mod = (ImpactPlus.getInstance()).settingsManager.getSettingByID(name);
            mod.setValDouble(Double.parseDouble(isOn));
          } 
        } 
      } 
      br.close();
    } catch (Exception var13) {
      var13.printStackTrace();
    } 
    try {
      File file = new File(this.Settings.getAbsolutePath(), "Color.txt");
      FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
      DataInputStream in = new DataInputStream(fstream);
      BufferedReader br = new BufferedReader(new InputStreamReader(in));
      String line;
      while ((line = br.readLine()) != null) {
        String curLine = line.trim();
        String name = curLine.split(":")[0];
        int color = Integer.parseInt(curLine.split(":")[1]);
        String m = curLine.split(":")[2];
        for (Module mm : ModuleManager.getModules()) {
          if (mm != null && mm.getName().equalsIgnoreCase(m)) {
            Setting mod = (ImpactPlus.getInstance()).settingsManager.getSettingByID(name);
            mod.setValColor(new Color(color));
          } 
        } 
      } 
      br.close();
    } catch (Exception var13) {
      var13.printStackTrace();
    } 
    try {
      File file = new File(this.Settings.getAbsolutePath(), "Boolean.txt");
      FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
      DataInputStream in = new DataInputStream(fstream);
      BufferedReader br = new BufferedReader(new InputStreamReader(in));
      String line;
      while ((line = br.readLine()) != null) {
        String curLine = line.trim();
        String name = curLine.split(":")[0];
        String isOn = curLine.split(":")[1];
        String m = curLine.split(":")[2];
        for (Module mm : ModuleManager.getModules()) {
          if (mm != null && mm.getName().equalsIgnoreCase(m)) {
            Setting mod = (ImpactPlus.getInstance()).settingsManager.getSettingByID(name);
            mod.setValBoolean(Boolean.parseBoolean(isOn));
          } 
        } 
      } 
      br.close();
    } catch (Exception var12) {
      var12.printStackTrace();
    } 
    try {
      File file = new File(this.Settings.getAbsolutePath(), "String.txt");
      FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
      DataInputStream in = new DataInputStream(fstream);
      BufferedReader br = new BufferedReader(new InputStreamReader(in));
      String line;
      while ((line = br.readLine()) != null) {
        String curLine = line.trim();
        String name = curLine.split(":")[0];
        String isOn = curLine.split(":")[1];
        String m = curLine.split(":")[2];
        for (Module mm : ModuleManager.getModules()) {
          if (mm != null && mm.getName().equalsIgnoreCase(m)) {
            Setting mod = (ImpactPlus.getInstance()).settingsManager.getSettingByID(name);
            mod.setValString(isOn);
          } 
        } 
      } 
      br.close();
    } catch (Exception var11) {
      var11.printStackTrace();
    } 
  }
}
