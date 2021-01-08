//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.module.modules.misc;

import me.axua.impactplus.ImpactPlusRpc;
import me.axua.impactplus.command.Command;
import me.axua.impactplus.module.Module;

public class RpcModule extends Module {
  public RpcModule() {
    super("DiscordRPC", Module.Category.MISC, "Discord Rich Presence");
    setDrawn(false);
  }
  
  public void onEnable() {
    ImpactPlusRpc.init();
    if (mc.player != null)
      Command.sendClientMessage("discord rpc started"); 
  }
  
  public void onDisable() {
    Command.sendClientMessage("you need to restart your game disable rpc");
  }
}
