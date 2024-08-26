package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TranslateToHTML {
    /**
     *
     * @param layout_path
     * @param target_path
     * @param output_folder_path
     * @return если файл был создан и сформирован, то путь к этому файлу; иначе пустую строку
     * @throws FileNotFoundException
     */
    public static String translateFile(String layout_path, String target_path, String output_folder_path)
            throws FileNotFoundException {
        String out_file_path = createEmptyHTMLFile(layout_path, target_path, output_folder_path);
        boolean isFileCreated = writeNewHTMLFile(layout_path, target_path, out_file_path);
        if (isFileCreated) return out_file_path;
        return "";
    }

    /**
     * Метод по трем параметрам: пути к файлу-шаблону с расширением "cshtml" и пути к файлу-туловищу,
     * имеющему то же расширение, а также по пути к папке, куда попадет созданный html-файл,-
     * создает пустой html-файл, если такой не существует; и ничего не делает, если такой файл уже есть
     *
     * @param layout_path        путь к файлу-шаблону с расширением "cshtml"
     * @param target_path        путь к файлу-туловищу с расширением "cshtml
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
            System.out.println("HTML-файл создан");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return out_file_path;
    }

    public static boolean writeNewHTMLFile(String layout_path, String target_path, String out_file_path)
            throws FileNotFoundException {
        Scanner layout_scanner = new Scanner(new File(layout_path)).useDelimiter("");
        PrintWriter out_file = new PrintWriter(out_file_path);
        StringBuffer barbecue = new StringBuffer();
        String c;
        boolean isLayoutClosed = false;
        while (layout_scanner.hasNext()) {
            c = layout_scanner.next();
            barbecue.append(c);
            if (c.equals(">")) {
                out_file.print(barbecue);
                if (barbecue.toString().trim().equals("<body>")) {
                    isLayoutClosed = true;
                    break;
                }
                barbecue = new StringBuffer();
            }
        }
        if (!isLayoutClosed) return false;          // HTML-файл пуст
        Scanner target_scanner = new Scanner(new File(target_path)).useDelimiter("");
        boolean permissionOnWrite = false;
        while (target_scanner.hasNext()) {
            c = target_scanner.next();
            if (c.equals("<")) {
                permissionOnWrite = true;
            }
            if (permissionOnWrite) {
                out_file.print(c);
            }
        }
        out_file.print("</body>\n</html>");
        out_file.close();
        return true;            // HTML-файл сформирован
    }

}
