package me.zero.alpine;

public interface EventBus {
  void subscribe(Object paramObject);
  
  void subscribe(Object... paramVarArgs);
  
  void subscribe(Iterable<Object> paramIterable);
  
  void unsubscribe(Object paramObject);
  
  void unsubscribe(Object... paramVarArgs);
  
  void unsubscribe(Iterable<Object> paramIterable);
  
  void post(Object paramObject);
  
  void attach(EventBus paramEventBus);
  
  void detach(EventBus paramEventBus);
}
