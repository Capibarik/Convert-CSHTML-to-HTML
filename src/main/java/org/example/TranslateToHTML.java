package org.example;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Inherited;
import java.util.Scanner;

public class TranslateToHTML {
    public static String translateFile(String layout_path, String target_path, String output_folder_path) {
        return "";
    }

    /**
     * Метод по трем параметрам: пути к файлу-шаблону с расширением "cshtml" и пути к файлу-туловищу,
     * имеющему то же расширение, а также по пути к папке, куда попадет созданный html-файл,-
     * создает пустой html-файл, если такой существует, и ничего не делает, если такой файл уже есть
     * @param layout_path путь к файлу-шаблону с расширением "cshtml"
     * @param target_path путь к файлу-туловищу с расширением "cshtml
     * @param output_folder_path путь к папке, в которой будет находиться html-файл
     * @return путь к выходному файлу, находящемуся в папке по пути {@code output_folder_path}
     */
    public static String createEmptyHTMLFile(String layout_path, String target_path, String output_folder_path) {
        File layout_file = new File(layout_path);
        File target_file = new File(target_path);
        String out_file_path = output_folder_path + "\\" +
                SearchCSHTML.stringsSubtract(layout_file.getName(), ".cshtml") + "+" +
                SearchCSHTML.stringsSubtract(target_file.getName(), ".cshtml") + ".html";
        File out_file;
        try {
            out_file = new File(out_file_path);
            out_file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return out_file_path;
    }
}
