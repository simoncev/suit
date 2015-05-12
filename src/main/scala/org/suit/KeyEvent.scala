/**
 * Copyright© Steven Dobay 2015
 */
package org.suit

import javax.swing.JComponent

/**
 * @author Steven Dobay
 *
 * @param cSource
 * @param cId
 * @param cWhen
 * @param keyCode
 * @param keyChar
 * @param extendedKeyCode
 * @param keyLocation
 * @param isAltHolded
 * @param isAltGraphHolded
 * @param isShiftHolded
 * @param isControlHolded
 * @param isActionKey
 * @param isTyped
 * @param isPressed
 * @param isReleased
 */
case class KeyEvent(private val cSource: Component,
  private val cId: Option[Int],
  private val cWhen: Long,
  keyCode: Int,
  keyChar: Char,
  extendedKeyCode: Int,
  keyLocation: Int,
  isAltHolded: Boolean,
  isAltGraphHolded: Boolean,
  isShiftHolded: Boolean,
  isControlHolded: Boolean,
  isActionKey: Boolean,
  isTyped: Boolean,
  isPressed: Boolean,
  isReleased: Boolean
 )
 extends Event(cSource, cId, cWhen)

object KeyEvent {
  import helpers.ComponentHelpers._
  import java.awt.event.{ KeyEvent => JKeyEvent }

  /**
   * Converts a java.awt.event.KeyEvent to org.suit.KeyEvent
   *
   * @param e
   * @param typed
   * @param pressed
   * @param released
   * @return with a new KeyEvent
   */
  def apply(e: JKeyEvent, typed: Boolean,
            pressed: Boolean, released: Boolean): KeyEvent =
   KeyEvent(
     e.getSource.asInstanceOf[JComponent].readSuitComponent,
     None, e.getWhen, e.getKeyCode, e.getKeyChar,
     e.getExtendedKeyCode, e.getKeyLocation, e.isAltDown,
     e.isAltGraphDown, e.isShiftDown, e.isControlDown, e.isActionKey,
     typed, pressed, released
   )

   /**
    *  Virtual key codes.
    *  The following code has been copied from
    *  http://grepcode.com/file_/repository.grepcode.com/java/root/jdk/openjdk/6-b14/java/awt/event/KeyEvent.java/?v=source
    */

  val VK_ENTER          = '\n';
  val VK_BACK_SPACE     = '\b';
  val VK_TAB            = '\t';
  val VK_CANCEL         = 0x03;
  val VK_CLEAR          = 0x0C;
  val VK_SHIFT          = 0x10;
  val VK_CONTROL        = 0x11;
  val VK_ALT            = 0x12;
  val VK_PAUSE          = 0x13;
  val VK_CAPS_LOCK      = 0x14;
  val VK_ESCAPE         = 0x1B;
  val VK_SPACE          = 0x20;
  val VK_PAGE_UP        = 0x21;
  val VK_PAGE_DOWN      = 0x22;
  val VK_END            = 0x23;
  val VK_HOME           = 0x24;

  /**
   * Constant for the non-numpad <b>left</b> arrow key.
   * @see #VK_KP_LEFT
   */
  val VK_LEFT           = 0x25;

  /**
   * Constant for the non-numpad <b>up</b> arrow key.
   * @see #VK_KP_UP
   */
  val VK_UP             = 0x26;

  /**
   * Constant for the non-numpad <b>right</b> arrow key.
   * @see #VK_KP_RIGHT
   */
  val VK_RIGHT          = 0x27;

  /**
   * Constant for the non-numpad <b>down</b> arrow key.
   * @see #VK_KP_DOWN
   */
  val VK_DOWN           = 0x28;

  /**
   * Constant for the comma key, ","
   */
  val VK_COMMA          = 0x2C;

  /**
   * Constant for the minus key, "-"
   * @since 1.2
   */
  val VK_MINUS          = 0x2D;

  /**
   * Constant for the period key, "."
   */
  val VK_PERIOD         = 0x2E;

  /**
   * Constant for the forward slash key, "/"
   */
  val VK_SLASH          = 0x2F;

  /** VK_0 thru VK_9 are the same as ASCII '0' thru '9' (0x30 - 0x39) */
  val VK_0              = 0x30;
  val VK_1              = 0x31;
  val VK_2              = 0x32;
  val VK_3              = 0x33;
  val VK_4              = 0x34;
  val VK_5              = 0x35;
  val VK_6              = 0x36;
  val VK_7              = 0x37;
  val VK_8              = 0x38;
  val VK_9              = 0x39;

  /**
   * Constant for the semicolon key, ";"
   */
  val VK_SEMICOLON      = 0x3B;

  /**
   * Constant for the equals key, "="
   */
  val VK_EQUALS         = 0x3D;

  /** VK_A thru VK_Z are the same as ASCII 'A' thru 'Z' (0x41 - 0x5A) */
  val VK_A              = 0x41;
  val VK_B              = 0x42;
  val VK_C              = 0x43;
  val VK_D              = 0x44;
  val VK_E              = 0x45;
  val VK_F              = 0x46;
  val VK_G              = 0x47;
  val VK_H              = 0x48;
  val VK_I              = 0x49;
  val VK_J              = 0x4A;
  val VK_K              = 0x4B;
  val VK_L              = 0x4C;
  val VK_M              = 0x4D;
  val VK_N              = 0x4E;
  val VK_O              = 0x4F;
  val VK_P              = 0x50;
  val VK_Q              = 0x51;
  val VK_R              = 0x52;
  val VK_S              = 0x53;
  val VK_T              = 0x54;
  val VK_U              = 0x55;
  val VK_V              = 0x56;
  val VK_W              = 0x57;
  val VK_X              = 0x58;
  val VK_Y              = 0x59;
  val VK_Z              = 0x5A;

  /**
   * Constant for the open bracket key, "["
   */
  val VK_OPEN_BRACKET   = 0x5B;

  /**
   * Constant for the back slash key, "\"
   */
  val VK_BACK_SLASH     = 0x5C;

  /**
   * Constant for the close bracket key, "]"
   */
  val VK_CLOSE_BRACKET  = 0x5D;

  val VK_NUMPAD0        = 0x60;
  val VK_NUMPAD1        = 0x61;
  val VK_NUMPAD2        = 0x62;
  val VK_NUMPAD3        = 0x63;
  val VK_NUMPAD4        = 0x64;
  val VK_NUMPAD5        = 0x65;
  val VK_NUMPAD6        = 0x66;
  val VK_NUMPAD7        = 0x67;
  val VK_NUMPAD8        = 0x68;
  val VK_NUMPAD9        = 0x69;
  val VK_MULTIPLY       = 0x6A;
  val VK_ADD            = 0x6B;

  /**
   * This constant is obsolete, and is included only for backwards
   * compatibility.
   * @see #VK_SEPARATOR
   */
  val VK_SEPARATER      = 0x6C;

  /**
   * Constant for the Numpad Separator key.
   * @since 1.4
   */
  val VK_SEPARATOR      = VK_SEPARATER;

  val VK_SUBTRACT       = 0x6D;
  val VK_DECIMAL        = 0x6E;
  val VK_DIVIDE         = 0x6F;
  val VK_DELETE         = 0x7F; /* ASCII DEL */
  val VK_NUM_LOCK       = 0x90;
  val VK_SCROLL_LOCK    = 0x91;

  /** Constant for the F1 function key. */
  val VK_F1             = 0x70;

  /** Constant for the F2 function key. */
  val VK_F2             = 0x71;

  /** Constant for the F3 function key. */
  val VK_F3             = 0x72;

  /** Constant for the F4 function key. */
  val VK_F4             = 0x73;

  /** Constant for the F5 function key. */
  val VK_F5             = 0x74;

  /** Constant for the F6 function key. */
  val VK_F6             = 0x75;

  /** Constant for the F7 function key. */
  val VK_F7             = 0x76;

  /** Constant for the F8 function key. */
  val VK_F8             = 0x77;

  /** Constant for the F9 function key. */
  val VK_F9             = 0x78;

  /** Constant for the F10 function key. */
  val VK_F10            = 0x79;

  /** Constant for the F11 function key. */
  val VK_F11            = 0x7A;

  /** Constant for the F12 function key. */
  val VK_F12            = 0x7B;

  /**
   * Constant for the F13 function key.
   * @since 1.2
   */
  /* F13 - F24 are used on IBM 3270 keyboard; use random range for constants. */
  val VK_F13            = 0xF000;

  /**
   * Constant for the F14 function key.
   * @since 1.2
   */
  val VK_F14            = 0xF001;

  /**
   * Constant for the F15 function key.
   * @since 1.2
   */
  val VK_F15            = 0xF002;

  /**
   * Constant for the F16 function key.
   * @since 1.2
   */
  val VK_F16            = 0xF003;

  /**
   * Constant for the F17 function key.
   * @since 1.2
   */
  val VK_F17            = 0xF004;

  /**
   * Constant for the F18 function key.
   * @since 1.2
   */
  val VK_F18            = 0xF005;

  /**
   * Constant for the F19 function key.
   * @since 1.2
   */
  val VK_F19            = 0xF006;

  /**
   * Constant for the F20 function key.
   * @since 1.2
   */
  val VK_F20            = 0xF007;

  /**
   * Constant for the F21 function key.
   * @since 1.2
   */
  val VK_F21            = 0xF008;

  /**
   * Constant for the F22 function key.
   * @since 1.2
   */
  val VK_F22            = 0xF009;

  /**
   * Constant for the F23 function key.
   * @since 1.2
   */
  val VK_F23            = 0xF00A;

  /**
   * Constant for the F24 function key.
   * @since 1.2
   */
  val VK_F24            = 0xF00B;

  val VK_PRINTSCREEN    = 0x9A;
  val VK_INSERT         = 0x9B;
  val VK_HELP           = 0x9C;
  val VK_META           = 0x9D;

  val VK_BACK_QUOTE     = 0xC0;
  val VK_QUOTE          = 0xDE;

  /**
   * Constant for the numeric keypad <b>up</b> arrow key.
   * @see #VK_UP
   * @since 1.2
   */
  val VK_KP_UP          = 0xE0;

  /**
   * Constant for the numeric keypad <b>down</b> arrow key.
   * @see #VK_DOWN
   * @since 1.2
   */
  val VK_KP_DOWN        = 0xE1;

  /**
   * Constant for the numeric keypad <b>left</b> arrow key.
   * @see #VK_LEFT
   * @since 1.2
   */
  val VK_KP_LEFT        = 0xE2;

  /**
   * Constant for the numeric keypad <b>right</b> arrow key.
   * @see #VK_RIGHT
   * @since 1.2
   */
  val VK_KP_RIGHT       = 0xE3;

  /* For European keyboards */
  /** @since 1.2 */
  val VK_DEAD_GRAVE               = 0x80;
  /** @since 1.2 */
  val VK_DEAD_ACUTE               = 0x81;
  /** @since 1.2 */
  val VK_DEAD_CIRCUMFLEX          = 0x82;
  /** @since 1.2 */
  val VK_DEAD_TILDE               = 0x83;
  /** @since 1.2 */
  val VK_DEAD_MACRON              = 0x84;
  /** @since 1.2 */
  val VK_DEAD_BREVE               = 0x85;
  /** @since 1.2 */
  val VK_DEAD_ABOVEDOT            = 0x86;
  /** @since 1.2 */
  val VK_DEAD_DIAERESIS           = 0x87;
  /** @since 1.2 */
  val VK_DEAD_ABOVERING           = 0x88;
  /** @since 1.2 */
  val VK_DEAD_DOUBLEACUTE         = 0x89;
  /** @since 1.2 */
  val VK_DEAD_CARON               = 0x8a;
  /** @since 1.2 */
  val VK_DEAD_CEDILLA             = 0x8b;
  /** @since 1.2 */
  val VK_DEAD_OGONEK              = 0x8c;
  /** @since 1.2 */
  val VK_DEAD_IOTA                = 0x8d;
  /** @since 1.2 */
  val VK_DEAD_VOICED_SOUND        = 0x8e;
  /** @since 1.2 */
  val VK_DEAD_SEMIVOICED_SOUND    = 0x8f;

  /** @since 1.2 */
  val VK_AMPERSAND                = 0x96;
  /** @since 1.2 */
  val VK_ASTERISK                 = 0x97;
  /** @since 1.2 */
  val VK_QUOTEDBL                 = 0x98;
  /** @since 1.2 */
  val VK_LESS   = 0x99;

  /** @since 1.2 */
  val VK_GREATER                  = 0xa0;
  /** @since 1.2 */
  val VK_BRACELEFT                = 0xa1;
  /** @since 1.2 */
  val VK_BRACERIGHT               = 0xa2;

  /**
   * Constant for the "@" key.
   * @since 1.2
   */
  val VK_AT     = 0x0200;

  /**
   * Constant for the ":" key.
   * @since 1.2
   */
  val VK_COLON  = 0x0201;

  /**
   * Constant for the "^" key.
   * @since 1.2
   */
  val VK_CIRCUMFLEX               = 0x0202;

  /**
   * Constant for the "$" key.
   * @since 1.2
   */
  val VK_DOLLAR = 0x0203;

  /**
   * Constant for the Euro currency sign key.
   * @since 1.2
   */
  val VK_EURO_SIGN                = 0x0204;

  /**
   * Constant for the "!" key.
   * @since 1.2
   */
  val VK_EXCLAMATION_MARK         = 0x0205;

  /**
   * Constant for the inverted exclamation mark key.
   * @since 1.2
   */
  val VK_INVERTED_EXCLAMATION_MARK = 0x0206;

  /**
   * Constant for the "(" key.
   * @since 1.2
   */
  val VK_LEFT_PARENTHESIS         = 0x0207;

  /**
   * Constant for the "#" key.
   * @since 1.2
   */
  val VK_NUMBER_SIGN              = 0x0208;

  /**
   * Constant for the "+" key.
   * @since 1.2
   */
  val VK_PLUS   = 0x0209;

  /**
   * Constant for the ")" key.
   * @since 1.2
   */
  val VK_RIGHT_PARENTHESIS        = 0x020A;

  /**
   * Constant for the "_" key.
   * @since 1.2
   */
  val VK_UNDERSCORE               = 0x020B;

  /**
   * Constant for the Microsoft Windows "Windows" key.
   * It is used for both the left and right version of the key.
   * @see #getKeyLocation()
   * @since 1.5
   */
  val VK_WINDOWS                  = 0x020C;

  /**
   * Constant for the Microsoft Windows Context Menu key.
   * @since 1.5
   */
  val VK_CONTEXT_MENU             = 0x020D;

  /* for input method support on Asian Keyboards */

  /* not clear what this means - listed in Microsoft Windows API */
  val VK_FINAL  = 0x0018;

  /** Constant for the Convert function key. */
  /* Japanese PC 106 keyboard, Japanese Solaris keyboard: henkan */
  val VK_CONVERT                  = 0x001C;

  /** Constant for the Don't Convert function key. */
  /* Japanese PC 106 keyboard: muhenkan */
  val VK_NONCONVERT               = 0x001D;

  /** Constant for the Accept or Commit function key. */
  /* Japanese Solaris keyboard: kakutei */
  val VK_ACCEPT = 0x001E;

  /* not clear what this means - listed in Microsoft Windows API */
  val VK_MODECHANGE               = 0x001F;

  /* replaced by VK_KANA_LOCK for Microsoft Windows and Solaris;
     might still be used on other platforms */
  val VK_KANA   = 0x0015;

  /* replaced by VK_INPUT_METHOD_ON_OFF for Microsoft Windows and Solaris;
     might still be used for other platforms */
  val VK_KANJI  = 0x0019;

  /**
   * Constant for the Alphanumeric function key.
   * @since 1.2
   */
  /* Japanese PC 106 keyboard: eisuu */
  val VK_ALPHANUMERIC             = 0x00F0;

  /**
   * Constant for the Katakana function key.
   * @since 1.2
   */
  /* Japanese PC 106 keyboard: katakana */
  val VK_KATAKANA                 = 0x00F1;

  /**
   * Constant for the Hiragana function key.
   * @since 1.2
   */
  /* Japanese PC 106 keyboard: hiragana */
  val VK_HIRAGANA                 = 0x00F2;

  /**
   * Constant for the Full-Width Characters function key.
   * @since 1.2
   */
  /* Japanese PC 106 keyboard: zenkaku */
  val VK_FULL_WIDTH               = 0x00F3;

  /**
   * Constant for the Half-Width Characters function key.
   * @since 1.2
   */
  /* Japanese PC 106 keyboard: hankaku */
  val VK_HALF_WIDTH               = 0x00F4;

  /**
   * Constant for the Roman Characters function key.
   * @since 1.2
   */
  /* Japanese PC 106 keyboard: roumaji */
  val VK_ROMAN_CHARACTERS         = 0x00F5;

  /**
   * Constant for the All Candidates function key.
   * @since 1.2
   */
  /* Japanese PC 106 keyboard - VK_CONVERT + ALT: zenkouho */
  val VK_ALL_CANDIDATES           = 0x0100;

  /**
   * Constant for the Previous Candidate function key.
   * @since 1.2
   */
  /* Japanese PC 106 keyboard - VK_CONVERT + SHIFT: maekouho */
  val VK_PREVIOUS_CANDIDATE       = 0x0101;

  /**
   * Constant for the Code Input function key.
   * @since 1.2
   */
  /* Japanese PC 106 keyboard - VK_ALPHANUMERIC + ALT: kanji bangou */
  val VK_CODE_INPUT               = 0x0102;

  /**
   * Constant for the Japanese-Katakana function key.
   * This key switches to a Japanese input method and selects its Katakana input mode.
   * @since 1.2
   */
  /* Japanese Macintosh keyboard - VK_JAPANESE_HIRAGANA + SHIFT */
  val VK_JAPANESE_KATAKANA        = 0x0103;

  /**
   * Constant for the Japanese-Hiragana function key.
   * This key switches to a Japanese input method and selects its Hiragana input mode.
   * @since 1.2
   */
  /* Japanese Macintosh keyboard */
  val VK_JAPANESE_HIRAGANA        = 0x0104;

  /**
   * Constant for the Japanese-Roman function key.
   * This key switches to a Japanese input method and selects its Roman-Direct input mode.
   * @since 1.2
   */
  /* Japanese Macintosh keyboard */
  val VK_JAPANESE_ROMAN           = 0x0105;

  /**
   * Constant for the locking Kana function key.
   * This key locks the keyboard into a Kana layout.
   * @since 1.3
   */
  /* Japanese PC 106 keyboard with special Windows driver - eisuu + Control; Japanese Solaris keyboard: kana */
  val VK_KANA_LOCK                = 0x0106;

  /**
   * Constant for the input method on/off key.
   * @since 1.3
   */
  /* Japanese PC 106 keyboard: kanji. Japanese Solaris keyboard: nihongo */
  val VK_INPUT_METHOD_ON_OFF      = 0x0107;

  /* for Sun keyboards */
  /** @since 1.2 */
  val VK_CUT    = 0xFFD1;
  /** @since 1.2 */
  val VK_COPY   = 0xFFCD;
  /** @since 1.2 */
  val VK_PASTE  = 0xFFCF;
  /** @since 1.2 */
  val VK_UNDO   = 0xFFCB;
  /** @since 1.2 */
  val VK_AGAIN  = 0xFFC9;
  /** @since 1.2 */
  val VK_FIND   = 0xFFD0;
  /** @since 1.2 */
  val VK_PROPS  = 0xFFCA;
  /** @since 1.2 */
  val VK_STOP   = 0xFFC8;

  /**
   * Constant for the Compose function key.
   * @since 1.2
   */
  val VK_COMPOSE                  = 0xFF20;

  /**
   * Constant for the AltGraph function key.
   * @since 1.2
   */
  val VK_ALT_GRAPH                = 0xFF7E;

  /**
   * Constant for the Begin key.
   * @since 1.5
   */
  val VK_BEGIN  = 0xFF58;

  /**
   * This value is used to indicate that the keyCode is unknown.
   * KEY_TYPED events do not have a keyCode value; this value
   * is used instead.
   */
  val VK_UNDEFINED      = 0x0;
}
