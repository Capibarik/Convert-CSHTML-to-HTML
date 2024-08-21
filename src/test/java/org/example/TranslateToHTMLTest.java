package org.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class TranslateToHTMLTest {
    @Test
    public void testCreateEmptyHTMLFile() {
        String test_path = "G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\Translate CSHTML to HTML\\src\\test\\resources\\TranslateToHTMLTest\\testCreateEmptyHTMLFile";
        String input_folder_path = test_path + "\\in";
        String output_folder_path = test_path + "\\out";
        String layout_path = input_folder_path + "\\_Layout.cshtml";
        String path_1 = input_folder_path + "\\About.cshtml";
        String path_2 = input_folder_path + "\\Home.cshtml";
        String path_3 = input_folder_path + "\\Contact.cshtml";
        assertEquals(
                "G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\Translate CSHTML to HTML\\src\\test\\resources\\TranslateToHTMLTest\\testCreateEmptyHTMLFile\\out\\_Layout+About.html",
                TranslateToHTML.createEmptyHTMLFile(layout_path, path_1, output_folder_path)
        );
        assertEquals(
                "G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\Translate CSHTML to HTML\\src\\test\\resources\\TranslateToHTMLTest\\testCreateEmptyHTMLFile\\out\\_Layout+Home.html",
                TranslateToHTML.createEmptyHTMLFile(layout_path, path_2, output_folder_path)
        );
        assertEquals(
                "G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\Translate CSHTML to HTML\\src\\test\\resources\\TranslateToHTMLTest\\testCreateEmptyHTMLFile\\out\\_Layout+Contact.html",
                TranslateToHTML.createEmptyHTMLFile(layout_path, path_3, output_folder_path)
        );
    }
    @AfterAll
    public static void clearOutFolder() {
        File output_folder = new File("G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\Translate CSHTML to HTML\\src\\test\\resources\\TranslateToHTMLTest\\testCreateEmptyHTMLFile\\out");
        File[] list_files = output_folder.listFiles();
        boolean flag_delete = true;
        if (list_files != null) {
            for (File inner_file: list_files) {
                if (inner_file.isFile()) {
                    if (!inner_file.getName().endsWith("Contact.html")) {
                        if (!inner_file.delete()) {
                            flag_delete = false;
                        }
                    }
                }
            }
        }
        if (flag_delete) System.out.println("Файлы для тестирования были удалены");
    }
}
