package utility.ansi;

/**
 * SGR - Select Graphic Rendition
 */
public class SGR
        extends CSIPrefix {
    public static final String RESET = ESC + "0m";
    public static final String BOLD = ESC + "1m";
    public static final String FAINT = ESC + "2m";
    public static final String ITALIC = ESC + "3m";
    public static final String UNDERLINE = ESC + "4m";
}
