package me.axua.impactplus.hud;

import java.util.ArrayList;
import java.util.List;
import me.axua.impactplus.gui.clickgui.ClickGUI;
import me.axua.impactplus.gui.clickgui.Panel;
import me.axua.impactplus.hud.components.ArmorComponent;
import me.axua.impactplus.hud.components.BiomeComponent;
import me.axua.impactplus.hud.components.BpsComponent;
import me.axua.impactplus.hud.components.ClosestComponent;
import me.axua.impactplus.hud.components.CoordsComponent;
import me.axua.impactplus.hud.components.CrystalsComponent;
import me.axua.impactplus.hud.components.DateComponent;
import me.axua.impactplus.hud.components.DimensionComponent;
import me.axua.impactplus.hud.components.DirectionComponent;
import me.axua.impactplus.hud.components.DubsComponent;
import me.axua.impactplus.hud.components.DurabilityComponent;
import me.axua.impactplus.hud.components.EntitysComponent;
import me.axua.impactplus.hud.components.ExpComponent;
import me.axua.impactplus.hud.components.FpsComponent;
import me.axua.impactplus.hud.components.GamemodeComponent;
import me.axua.impactplus.hud.components.GappsComponent;
import me.axua.impactplus.hud.components.HoleComponent;
import me.axua.impactplus.hud.components.InventoryComponent;
import me.axua.impactplus.hud.components.ModListComponent;
import me.axua.impactplus.hud.components.PingComponent;
import me.axua.impactplus.hud.components.PlayerCountComponent;
import me.axua.impactplus.hud.components.PlayerPreviewComponent;
import me.axua.impactplus.hud.components.PotionEffectsComponent;
import me.axua.impactplus.hud.components.PvpinfoComponent;
import me.axua.impactplus.hud.components.ServerIpComponent;
import me.axua.impactplus.hud.components.TextRadarComponent;
import me.axua.impactplus.hud.components.TimeComponent;
import me.axua.impactplus.hud.components.TotemsComponent;
import me.axua.impactplus.hud.components.TpsComponent;
import me.axua.impactplus.hud.components.WatermarkComponent;
import me.axua.impactplus.hud.components.WelcomerComponent;

public class HudComponentManager {
  public static List<Panel> hudComponents;
  
  public HudComponentManager(double ix, double iy, ClickGUI parent) {
    hudComponents = new ArrayList<>();
    addComponent((Panel)new WatermarkComponent(ix + 2.0D, iy + 2.0D, parent));
    addComponent((Panel)new WelcomerComponent(ix + 2.0D, iy + 12.0D, parent));
    addComponent((Panel)new FpsComponent(ix + 2.0D, iy + 32.0D, parent));
    addComponent((Panel)new TpsComponent(ix + 2.0D, iy + 42.0D, parent));
    addComponent((Panel)new BpsComponent(ix + 2.0D, iy + 52.0D, parent));
    addComponent((Panel)new PingComponent(ix + 2.0D, iy + 62.0D, parent));
    addComponent((Panel)new DurabilityComponent(ix + 2.0D, iy + 72.0D, parent));
    addComponent((Panel)new PlayerCountComponent(ix + 2.0D, iy + 82.0D, parent));
    addComponent((Panel)new PlayerPreviewComponent(ix + 31.0D, iy + 177.0D, parent));
    addComponent((Panel)new ServerIpComponent(ix + 2.0D, iy + 102.0D, parent));
    addComponent((Panel)new BiomeComponent(ix + 2.0D, iy + 112.0D, parent));
    addComponent((Panel)new DimensionComponent(ix + 2.0D, iy + 122.0D, parent));
    addComponent((Panel)new GamemodeComponent(ix + 2.0D, iy + 132.0D, parent));
    addComponent((Panel)new ModListComponent(ix + 2.0D, iy + 272.0D, parent));
    addComponent((Panel)new HoleComponent(ix + 2.0D, iy + 282.0D, parent));
    addComponent((Panel)new GappsComponent(ix + 2.0D, iy + 292.0D, parent));
    addComponent((Panel)new CrystalsComponent(ix + 2.0D, iy + 302.0D, parent));
    addComponent((Panel)new ExpComponent(ix + 2.0D, iy + 312.0D, parent));
    addComponent((Panel)new PvpinfoComponent(ix + 2.0D, iy + 322.0D, parent));
    addComponent((Panel)new TimeComponent(ix + 2.0D, iy + 332.0D, parent));
    addComponent((Panel)new DateComponent(ix + 2.0D, iy + 342.0D, parent));
    addComponent((Panel)new DubsComponent(ix + 2.0D, iy + 352.0D, parent));
    addComponent((Panel)new EntitysComponent(ix + 2.0D, iy + 362.0D, parent));
    addComponent((Panel)new ClosestComponent(ix + 2.0D, iy + 372.0D, parent));
    addComponent((Panel)new ArmorComponent(ix + 2.0D, iy + 382.0D, parent));
    addComponent((Panel)new InventoryComponent(ix + 572.0D, iy + 437.0D, parent));
    addComponent((Panel)new TextRadarComponent(ix + 957.0D, iy + 143.0D, parent));
    addComponent((Panel)new PotionEffectsComponent(ix + 957.0D, iy + 457.0D, parent));
    addComponent((Panel)new DirectionComponent(ix + 957.0D, iy + 467.0D, parent));
    addComponent((Panel)new CoordsComponent(ix + 957.0D, iy + 477.0D, parent));
    addComponent((Panel)new TotemsComponent(ix + 572.0D, iy + 426.0D, parent));
  }
  
  private void addComponent(Panel p) {
    hudComponents.add(p);
  }
  
  public static Panel getHudComponentByName(String name) {
    Panel pa = null;
    for (Panel p : hudComponents) {
      if (p.title.equalsIgnoreCase(name))
        pa = p; 
    } 
    return pa;
  }
}
