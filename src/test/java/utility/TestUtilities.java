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
    public static void clearFolder(String folder_path, String ...name_exceptions) {
        File output_folder = new File(folder_path);
        File[] list_files = output_folder.listFiles();
        ArrayList<String> name_exceps = new ArrayList<>(Arrays.asList(name_exceptions));
        Collections.sort(name_exceps);
        System.out.println();
        if (list_files != null) {
            for (File inner_file : list_files) {
                if (inner_file.isFile()) {
                    if (Collections.binarySearch(name_exceps, inner_file.getName(), (o1, o2) -> {
                        if (o1.endsWith(o2) || o2.endsWith(o1)) return 0;
                        return -1;
                    }) < 0) {
                        if (inner_file.delete()) {
                            System.out.println(
                                    colorize("Файл " + inner_file.getPath() + " был удален", GREEN_TEXT())
                            );
                        }
                    }
                }
            }
        }
    }

    /**
     * Метод для сравнения двух HTML-файлов
     * @param file1_path
     * @param file2_path
     * @param precision {@code true} - при сравнении учитываются пробелы между html-тегами, {@code false} - при сравнении НЕ учитываются пробелы между html-тегами
     * @return
     */
    public static boolean compareHTMLFiles(String file1_path, String file2_path, boolean precision)
            throws IOException {
        if (precision) {
            return Files.mismatch(Paths.get(file1_path), Paths.get(file2_path)) == -1L;
        }
        else {
            File file1 = new File(file1_path);
            File file2 = new File(file2_path);
            // code of comparison
            Scanner file1_scanner = new Scanner(file1);
            Scanner file2_scanner = new Scanner(file2);
            String c1, c2;
            while (file1_scanner.hasNext() && file2_scanner.hasNext()) {
                c1 = file1_scanner.next();
                c2 = file2_scanner.next();

            }
            // if (something equals true) return true;
        }
        return false;
    }
    public static void printCode(String s) {
        for (int i = 0; i < s.length(); i++) {
            int code = s.charAt(i);
            System.out.print(code + " = " + s.charAt(i) + "; ");
        }
        System.out.println();
    }
}
