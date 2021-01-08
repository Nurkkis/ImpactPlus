//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package me.axua.impactplus.module.modules.misc;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.axua.impactplus.ImpactPlus;
import me.axua.impactplus.command.Command;
import me.axua.impactplus.friends.Friends;
import me.axua.impactplus.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Mouse;

public class MiddleClickFriends extends Module {
  @EventHandler
  private Listener<InputEvent.MouseInputEvent> listener;
  
  public MiddleClickFriends() {
    super("MCF", Module.Category.MISC, "Middle click players to add / remove them as a friend");
    this.listener = new Listener(event -> {
          if (mc.objectMouseOver.typeOfHit.equals(RayTraceResult.Type.ENTITY) && mc.objectMouseOver.entityHit instanceof net.minecraft.entity.player.EntityPlayer && Mouse.getEventButton() == 2)
            if (Friends.isFriend(mc.objectMouseOver.entityHit.getName())) {
              (ImpactPlus.getInstance()).friends.delFriend(mc.objectMouseOver.entityHit.getName());
              Command.sendClientMessage(ChatFormatting.RED + "Removed " + mc.objectMouseOver.entityHit.getName() + " from friends list");
            } else {
              (ImpactPlus.getInstance()).friends.addFriend(mc.objectMouseOver.entityHit.getName());
              Command.sendClientMessage(ChatFormatting.GREEN + "Added " + mc.objectMouseOver.entityHit.getName() + " to friends list");
            }  
        }new java.util.function.Predicate[0]);
  }
  
  public void onEnable() {
    ImpactPlus.EVENT_BUS.subscribe(this);
  }
  
  public void onDisable() {
    ImpactPlus.EVENT_BUS.unsubscribe(this);
  }
}
