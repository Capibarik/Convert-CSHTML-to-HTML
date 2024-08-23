package utility;

import utility.ansi.Colors;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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
                            System.out.println("Файл " + inner_file.getPath() + " был удален");
                        }
                    }
                }
            }
        }
    }
}
