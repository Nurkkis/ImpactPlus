package me.axua.impactplus.util.font;

import java.util.regex.Pattern;

public enum ChatColor {
  BLACK('0', true),
  DARK_BLUE('1', true),
  DARK_GREEN('2', true),
  DARK_AQUA('3', true),
  DARK_RED('4', true),
  DARK_PURPLE('5', true),
  GOLD('6', true),
  GRAY('7', true),
  DARK_GRAY('8', true),
  BLUE('9', true),
  GREEN('a', true),
  AQUA('b', true),
  RED('c', true),
  LIGHT_PURPLE('d', true),
  YELLOW('e', true),
  WHITE('f', true),
  MAGIC('k', true),
  BOLD('l', true),
  STRIKETHROUGH('m', true),
  UNDERLINE('n', true),
  ITALIC('o', true),
  RESET('r', true);

  public static final char COLOR_CHAR = '\u00a7';
  
  private final char code;
  
  private final boolean isFormat;
  
  private final String toString;
  
  ChatColor(char code, boolean isFormat) {
    this.code = code;
    this.isFormat = isFormat;
    this.toString = new String(new char[] { '\u00a7', code });
  }
  
  public static String stripColor(String input) {
    return (input == null) ? null : 
      Pattern.compile("(?i)\u00a7[0-9A-FK-OR]").matcher(input).replaceAll("");
  }
  
  public static String translateAlternateColorCodes(char altColorChar, String textToTranslate) {
    char[] b = textToTranslate.toCharArray();
    int bound = b.length - 1;
    for (int i = 0; i < bound; i++) {
      if (b[i] == altColorChar && "0123456789AaBbCcDdEeFfKkLlMmNnOoRr".indexOf(b[i + 1]) > -1) {
        b[i] = '\u00a7';
        b[i + 1] = Character.toLowerCase(b[i + 1]);
      } 
    } 
    return new String(b);
  }
  
  public char getChar() {
    return this.code;
  }
  
  public String toString() {
    return this.toString;
  }
  
  public boolean isFormat() {
    return this.isFormat;
  }
  
  public boolean isColor() {
    return (!this.isFormat && this != RESET);
  }
}
