package me.axua.impactplus.macro;

import java.util.ArrayList;
import java.util.List;

public class MacroManager {
  List<Macro> macros = new ArrayList<>();
  
  public List<Macro> getMacros() {
    return this.macros;
  }
  
  public Macro getMacroByValue(String v) {
    Macro m = getMacros().stream().filter(mm -> (mm.getValue() == v)).findFirst().orElse(null);
    return m;
  }
  
  public Macro getMacroByKey(int key) {
    Macro m = getMacros().stream().filter(mm -> (mm.getKey() == key)).findFirst().orElse(null);
    return m;
  }
  
  public void addMacro(Macro m) {
    this.macros.add(m);
  }
  
  public void delMacro(Macro m) {
    this.macros.remove(m);
  }
}
