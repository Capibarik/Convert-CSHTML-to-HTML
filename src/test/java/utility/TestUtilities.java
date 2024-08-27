package utility;


import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class TestUtilities {
    public static void clearFolder(String folder_path, String ...name_exceptions)
            throws IOException {
        File output_folder = new File(folder_path);
        File[] list_files = output_folder.listFiles();
        ArrayList<String> name_exceps = new ArrayList<>(Arrays.asList(name_exceptions));
        Collections.sort(name_exceps);
        System.out.println();
        if (list_files != null) {
            for (File inner_file : list_files) {
                if (inner_file.isFile()) {
                    if (Collections.binarySearch(name_exceps, inner_file.getName()) < 0) {
                        Files.delete(Paths.get(inner_file.getPath()));
                        System.out.println(
                                colorize("Delete: ", YELLOW_TEXT()) + colorize(inner_file.getPath(), ITALIC())
                        );
                    }
                }
            }
        }
    }

    /**
     * Метод для сравнения двух файлов. Если {@code precision = true}, то сравнение производится при
     * помощи метода {@link Files#mismatch mismatch}. Иначе же сравнение файлов производится без учета пробелов.
     * То есть значение имеет лишь порядок символов в обоих файлах, они должны быть равны между собой. Например,
     * строка {@code "HTML"} (при {@code precision = false}) равна строке {@code "H T ML"}, строка {@code "THIS METHOD"}
     * равна строке {@code "TH IS METH   OD"}.
     * @param file1_path
     * @param file2_path
     * @param precision {@code true} - если при сравнении учитываются пробелы, {@code false} - при сравнении НЕ учитываются пробелы.
     * @return
     */
    public static boolean compareFiles(String file1_path, String file2_path, boolean precision)
            throws IOException {
        if (precision) {
            return Files.mismatch(Paths.get(file1_path), Paths.get(file2_path)) == -1L;
        }
        else {
            File file1 = new File(file1_path);
            File file2 = new File(file2_path);
            // code of comparison
            Scanner file1_scanner = new Scanner(file1).useDelimiter("");
            Scanner file2_scanner = new Scanner(file2).useDelimiter("");
            boolean next1 = true, next2 = true;
            String c1 = null, c2 = null;
            while (file1_scanner.hasNext() && file2_scanner.hasNext()) {
                if (next1) c1 = file1_scanner.next();
                if (next2) c2 = file2_scanner.next();
                if (c1.matches("\\s") && c2.matches("\\S")) {
                    next2 = false;
                    continue;
                }
                if (c1.matches("\\S") && c2.matches("\\s")) {
                    next1 = false;
                    continue;
                }
                if (c1.matches("\\s") && c2.matches("\\s")) {
                    next1 = true;
                    next2 = true;
                    continue;
                }
                if (c1.matches("\\S") && c2.matches("\\S")) {
                    if (!c1.equals(c2)) {
                        return false;
                    }
                    next1 = true;
                    next2 = true;
                }
            }
        }
        return true;
    }

    /**
     * Метод для сравнения двух строк, не учитывая пробелы. Создан для такого же метода сравнения файлов.
     * @param s1 первая строка.
     * @param s2 вторая строка.
     * @return {@code true} - если строки равны, не учитывая пробелов; {@code false} - если строки без пробелов не равны.
     */
    public static boolean compareString(String s1, String s2) {
        if (s1.isEmpty() ^ s2.isEmpty()) return false;
        if (s1.isEmpty() & s2.isEmpty()) return true;
        int i1 = 0;
        int i2 = 0;
        String c1 = null, c2 = null;
        boolean next1 = true, next2 = true;
        while (i1 < s1.length() && i2 < s2.length()) {
            if (next1) c1 = String.valueOf(s1.charAt(i1));
            if (next2) c2 = String.valueOf(s2.charAt(i2));
            if (c1.matches("\\s") && c2.matches("\\S")) {
                next2 = false;
                i1++;
                continue;
            }
            if (c1.matches("\\S") && c2.matches("\\s")) {
                next1 = false;
                i2++;
                continue;
            }
            if (c1.matches("\\s") && c2.matches("\\s")) {
                next1 = true;
                next2 = true;
                i1++;
                i2++;
                continue;
            }
            if (c1.matches("\\S") && c2.matches("\\S")) {
                if (!c1.equals(c2)) {
                    return false;
                }
                next1 = true;
                next2 = true;
                i1++;
                i2++;
            }
        }
        return true;
    }
}
