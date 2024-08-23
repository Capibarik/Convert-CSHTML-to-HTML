package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LaunchHTML {
    public static void main(String[] args)
            throws FileNotFoundException {
        String[] files_for_translation = readData("G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\Translate CSHTML to HTML\\src\\main\\resources\\config");
        String path_to_file = translation(files_for_translation);
        execute(path_to_file);
    }
    private static String[] readData(String config_file_path)
            throws FileNotFoundException {
        Scanner in = new Scanner(new File(config_file_path));
        String project_path = in.nextLine();
        String cshtml_file_name = in.nextLine();
        String output_folder_path = in.nextLine();
        String project_folder_path = SearchCSHTML.getFolderPath(project_path, ".sln");
        String cshtml_file_path = SearchCSHTML.getFilePath(project_folder_path + "\\Views", cshtml_file_name);
        String cshtml_layout_path = SearchCSHTML.getFilePath(project_folder_path + "\\Views", "_Layout.cshtml");
        return new String[]{cshtml_layout_path, cshtml_file_path, output_folder_path};
    }
    private static String translation(String[] files_for_translation)
            throws FileNotFoundException {
        String layout = files_for_translation[0];
        String file_body = files_for_translation[1];
        String output_folder_path = files_for_translation[2];
        String path_to_file = TranslateToHTML.translateFile(layout, file_body, output_folder_path);
        return path_to_file;
    }
    private static void execute(String path_to_file) {
        if (!path_to_file.isEmpty()) {
            System.out.println("Запускается файл " + path_to_file);
        }
        else {
            System.out.println("html-файл не был создан");
        }
    }
}
