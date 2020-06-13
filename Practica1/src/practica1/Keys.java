/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1;

/**
 *
 * @author Carlos
 */
public abstract class Keys{
    
    public static final int ESC = 27;
    public static final int BACKSPACE = 127;
    public static final int RIGHT = 67;
    public static final int LEFT = 68;
    public static final int HOME = 72;
    public static final int END = 70;
    public static final int INSERT = 50;
    public static final int DELETE = 51;
    public static final int CORCHETE = 91;
    public static final int TILDE = 126; //simbolo ~
    public static final int ENTER = 13;

    public static final int SEC_RIGHT = 2001;
    public static final int SEC_LEFT = 2002;
    public static final int SEC_HOME = 2003;
    public static final int SEC_FIN = 2004;
    public static final int SEC_INSERT = 2005;
    public static final int SEC_DELETE = 2006;
    public static final int SEC_CHARACTER = 2007;

    public static final String ANSI_RIGHT = "\033[C";
    public static final String ANSI_LEFT = "\033[D";
    public static final String ANSI_INSERT = "\033[2~";
    public static final String ANSI_BACKSPACE = "\b";
    public static final String ANSI_DELETE = "\033[P";
    public static final String ANSI_HOME = "\033[H";
    public static final String ANSI_END = "\033[F";
    public static final String ANSI_BLANK = "\033[@";
}
