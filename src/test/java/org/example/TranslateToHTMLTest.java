package org.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import utility.TestUtilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
        assertEquals("G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\Translate CSHTML to HTML\\src\\test\\resources\\TranslateToHTMLTest\\testCreateEmptyHTMLFile\\out\\_Layout+About.html", TranslateToHTML.createEmptyHTMLFile(layout_path, path_1, output_folder_path));
        assertEquals("G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\Translate CSHTML to HTML\\src\\test\\resources\\TranslateToHTMLTest\\testCreateEmptyHTMLFile\\out\\_Layout+Home.html", TranslateToHTML.createEmptyHTMLFile(layout_path, path_2, output_folder_path));
        assertEquals("G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\Translate CSHTML to HTML\\src\\test\\resources\\TranslateToHTMLTest\\testCreateEmptyHTMLFile\\out\\_Layout+Contact.html", TranslateToHTML.createEmptyHTMLFile(layout_path, path_3, output_folder_path));
    }

    @Test
    public void testWriteNewHTMLFile()
            throws IOException {
        String layout_path = "G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\Translate CSHTML to HTML\\src\\test\\resources\\TranslateToHTMLTest\\testWriteNewHTMLFile\\in\\_Layout.cshtml";
        String target_path1 = "G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\Translate CSHTML to HTML\\src\\test\\resources\\TranslateToHTMLTest\\testWriteNewHTMLFile\\in\\Home.cshtml";
        String target_path2 = "G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\Translate CSHTML to HTML\\src\\test\\resources\\TranslateToHTMLTest\\testWriteNewHTMLFile\\in\\LoginForm.cshtml";
        String out_folder_path = "G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\Translate CSHTML to HTML\\src\\test\\resources\\TranslateToHTMLTest\\testWriteNewHTMLFile\\out";
        String path1_test_comparable = "G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\Translate CSHTML to HTML\\src\\test\\resources\\TranslateToHTMLTest\\testWriteNewHTMLFile\\in\\_Layout+Home.html";
        String path1_created_comparable = TranslateToHTML.createEmptyHTMLFile(layout_path, target_path1, out_folder_path);
        String path2_test_comparable = "G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\Translate CSHTML to HTML\\src\\test\\resources\\TranslateToHTMLTest\\testWriteNewHTMLFile\\in\\_Layout+LoginForm.html";
        String path2_created_comparable = TranslateToHTML.createEmptyHTMLFile(layout_path, target_path2, out_folder_path);
        TranslateToHTML.writeNewHTMLFile(layout_path, target_path1, path1_created_comparable);
        TranslateToHTML.writeNewHTMLFile(layout_path, target_path2, path2_created_comparable);
        assertEquals(-1, Files.mismatch(Paths.get(path1_test_comparable), Paths.get(path1_created_comparable)));
        assertNotEquals(-1, Files.mismatch(Paths.get(path2_test_comparable), Paths.get(path2_created_comparable)));
    }

    @AfterAll
    public static void clearOutFolder() {
        TestUtilities.clearFolder(
                "G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\Translate CSHTML to HTML\\src\\test\\resources\\TranslateToHTMLTest\\testCreateEmptyHTMLFile\\out",
                "Home.html"
        );
        TestUtilities.clearFolder(
                "G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\Translate CSHTML to HTML\\src\\test\\resources\\TranslateToHTMLTest\\testWriteNewHTMLFile\\out"
        );
    }
}
