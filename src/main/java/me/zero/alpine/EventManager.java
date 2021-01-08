package me.zero.alpine;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;

public class EventManager implements EventBus {
  private final Map<Object, List<Listener>> SUBSCRIPTION_CACHE = new HashMap<>();
  
  private final Map<Class<?>, List<Listener>> SUBSCRIPTION_MAP = new HashMap<>();
  
  private final List<EventBus> ATTACHED_BUSES = new ArrayList<>();
  
  public void subscribe(Object object) {
    List<Listener> listeners = this.SUBSCRIPTION_CACHE.computeIfAbsent(object, o -> (List)Arrays.<Field>stream(o.getClass().getDeclaredFields()).filter(EventManager::isValidField).map(()).filter(Objects::nonNull).collect(Collectors.toList()));
    listeners.forEach(this::subscribe);
    if (!this.ATTACHED_BUSES.isEmpty())
      this.ATTACHED_BUSES.forEach(bus -> bus.subscribe(object)); 
  }
  
  public void subscribe(Object... objects) {
    Arrays.<Object>stream(objects).forEach(this::subscribe);
  }
  
  public void subscribe(Iterable<Object> objects) {
    objects.forEach(this::subscribe);
  }
  
  public void unsubscribe(Object object) {
    List<Listener> objectListeners = this.SUBSCRIPTION_CACHE.get(object);
    if (objectListeners == null)
      return; 
    this.SUBSCRIPTION_MAP.values().forEach(listeners -> {
          Objects.requireNonNull(objectListeners);
          listeners.removeIf(objectListeners::contains);
        });
    if (!this.ATTACHED_BUSES.isEmpty())
      this.ATTACHED_BUSES.forEach(bus -> bus.unsubscribe(object)); 
  }
  
  public void unsubscribe(Object... objects) {
    Arrays.<Object>stream(objects).forEach(this::unsubscribe);
  }
  
  public void unsubscribe(Iterable<Object> objects) {
    objects.forEach(this::unsubscribe);
  }
  
  public void post(Object event) {
    List<Listener> listeners = this.SUBSCRIPTION_MAP.get(event.getClass());
    if (listeners != null)
      listeners.forEach(listener -> listener.invoke(event)); 
    if (!this.ATTACHED_BUSES.isEmpty())
      this.ATTACHED_BUSES.forEach(bus -> bus.post(event)); 
  }
  
  public void attach(EventBus bus) {
    if (!this.ATTACHED_BUSES.contains(bus))
      this.ATTACHED_BUSES.add(bus); 
  }
  
  public void detach(EventBus bus) {
    if (this.ATTACHED_BUSES.contains(bus))
      this.ATTACHED_BUSES.remove(bus); 
  }
  
  private static boolean isValidField(Field field) {
    return (field.isAnnotationPresent((Class)EventHandler.class) && Listener.class.isAssignableFrom(field.getType()));
  }
  
  private static Listener asListener(Object object, Field field) {
    try {
      boolean accessible = field.isAccessible();
      field.setAccessible(true);
      Listener listener = (Listener)field.get(object);
      field.setAccessible(accessible);
      if (listener == null)
        return null; 
      if (listener.getPriority() > 5 || listener.getPriority() < 1)
        throw new RuntimeException("Event Priority out of bounds! %s"); 
      return listener;
    } catch (IllegalAccessException e) {
      return null;
    } 
  }
  
  private void subscribe(Listener listener) {
    List<Listener> listeners = this.SUBSCRIPTION_MAP.computeIfAbsent(listener.getTarget(), target -> new ArrayList());
    int index = 0;
    for (; index < listeners.size() && 
      listener.getPriority() >= ((Listener)listeners.get(index)).getPriority(); index++);
    listeners.add(index, listener);
  }
}
