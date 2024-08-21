package org.example;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class LaunchHTML {
    public static void main(String[] args)
            throws FileNotFoundException {
        String[] files_for_translation = readData();
        String path_to_file = translation(files_for_translation);
        execute(path_to_file);
    }
    /**
     * Метод, который считывает и возвращает данные, введенные пользователем
     *
     * @param in      объект {@link Scanner} для считывания данных из консоли
     * @param message сообщения, которое просит пользователя ввести данные
     * @return возвращает строку - данные введенные пользователем
     */
    public static String read(Scanner in, String message) {
        System.out.print(message);
        return in.nextLine();
    }
    private static String[] readData()
            throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        String project_path = read(in, "Введите путь до ASP.NET-проекта: ");
        String cshtml_file_name = read(in, "Введите полное название cshtml-файла: ");
        String project_folder_path = SearchCSHTML.getFolderPath(project_path, ".sln");
        String cshtml_file_path = SearchCSHTML.getFilePath(project_folder_path + "\\Views", cshtml_file_name);
        String cshtml_layout_path = SearchCSHTML.getFilePath(project_folder_path + "\\Views", "_Layout.cshtml");
        return new String[]{cshtml_file_path, cshtml_layout_path};
    }
    private static String translation(String[] files_for_translation) {
        String layout = files_for_translation[0];
        String file_body = files_for_translation[1];
        String path_to_file = TranslateToHTML.translateFile(layout, file_body);
        return path_to_file;
    }
    private static void execute(String path_to_file) {
        System.out.println("Execute " + path_to_file);
    }
}
