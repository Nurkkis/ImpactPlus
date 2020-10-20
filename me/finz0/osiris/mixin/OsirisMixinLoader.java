package me.finz0.osiris.mixin;

import java.util.Map;
import javax.annotation.Nullable;
import me.axua.impactplus.ImpactPlus;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

public class OsirisMixinLoader implements IFMLLoadingPlugin {
  private static boolean isObfuscatedEnvironment = false;
  
  public OsirisMixinLoader() {
    ImpactPlus.log.info("ImpactPlus mixins initialized");
    MixinBootstrap.init();
    Mixins.addConfiguration("mixins.impactplus.json");
    MixinEnvironment.getDefaultEnvironment().setObfuscationContext("searge");
    ImpactPlus.log.info(MixinEnvironment.getDefaultEnvironment().getObfuscationContext());
  }
  
  public String[] getASMTransformerClass() {
    return new String[0];
  }
  
  public String getModContainerClass() {
    return null;
  }
  
  @Nullable
  public String getSetupClass() {
    return null;
  }
  
  public void injectData(Map<String, Object> data) {
    isObfuscatedEnvironment = ((Boolean)data.get("runtimeDeobfuscationEnabled")).booleanValue();
  }
  
  public String getAccessTransformerClass() {
    return null;
  }
}
