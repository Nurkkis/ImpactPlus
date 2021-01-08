package me.axua.impactplus.module.modules.misc;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import me.axua.impactplus.module.Module;

public class Notifications extends Module {
  public Notifications() {
    super("Notifications", Module.Category.MISC, "Desktop notifications");
  }
  
  public static void sendNotification(String message, TrayIcon.MessageType messageType) {
    SystemTray tray = SystemTray.getSystemTray();
    Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
    TrayIcon icon = new TrayIcon(image, "Impact+");
    icon.setImageAutoSize(true);
    icon.setToolTip("Impact+");
    try {
      tray.add(icon);
    } catch (AWTException e) {
      e.printStackTrace();
    } 
    icon.displayMessage("Impact+", message, messageType);
  }
}
