/*
* TODO:
*  1. Найти cshtml-файл, который нужно транслировать в html-файл
*  2. Найти файл _Layout.cshtml
*  3. Соединить файлы _Layout.cshtml и cshtml-файл, который нужно транслировать, в html-файл
* */

package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {
    public static void main(String[] args)
            throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        String project_path = read(in, "Введите путь до ASP.NET-проекта: ");
        String project_name = getNameOfProject(project_path, ".sln");
        String project_folder_name = getOnlyFileName(project_name, ".sln");
        String cshtml_file_name = read(in, "Введите название cshtml-файла (без расширения): ");
        String cshtml_file_path = getFile(project_path, project_folder_name, cshtml_file_name + ".cshtml");
    }

    private static String getFile(String project_path, String project_folder_name, String file_name)
            throws FileNotFoundException {
        return getNameOfProject(project_path + "\\" + project_folder_name + "\\Views", file_name);
    }

    public static String read(Scanner in, String message) {
        System.out.print(message);
        return in.nextLine();
    }
    public static String getNameOfProject(String project_path, String filename_filter)
            throws FileNotFoundException {
        File project_folder = new File(project_path);
        if (!project_folder.exists())
            throw new FileNotFoundException("Папка проекта не найдена");
        else if (project_folder.isFile())
            throw new FileNotFoundException("Был передан файл вместо папки проекта");
        String project_name = searchingForNameOfProject(project_folder, filename_filter);
        if (project_name == null)
            throw new FileNotFoundException("Файл с расширением " + filename_filter + " не был найден");
        return project_name;
    }
    public static String searchingForNameOfProject(File start_file, String filename_filter) {
        File[] list_files = start_file.listFiles();
        if (list_files != null) {
            for (File inner_file: list_files) {
                if (inner_file.isFile()) {
                    String file_name = inner_file.getName();
                    if (file_name.endsWith(filename_filter))
                        return file_name;
                }
                else {
                    String result = searchingForNameOfProject(inner_file, filename_filter);
                    if (result != null)
                        return result;
                }
            }
        }
        return null;
    }
    public static String getOnlyFileName(String file_name, String extension) {
        int diff = file_name.length() - extension.length();
        if (diff < 0) return "";
        else return file_name.substring(0, diff);
    }
}
