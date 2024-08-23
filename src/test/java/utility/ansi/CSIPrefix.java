package utility.ansi;

/**
 * Класс CSIPrefix (CSI - Control Sequence Introducer) хранит единственную статическую переменную {@code ESC}.
 * Каждая команда CSI начинается с ESC[
 */
public class CSIPrefix {
    public static final String ESC = "\u001B[";
}
