package org.example;


import utility.TestUtilities;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


public class TranslateToHTMLTest {
    @Test
    public void testCreateEmptyHTMLFile() {
        String test_path = "src\\test\\resources\\TranslateToHTMLTest\\testCreateEmptyHTMLFile";
        String input_folder_path = test_path + "\\in";
        String output_folder_path = test_path + "\\out";
        String layout_path = input_folder_path + "\\_Layout.cshtml";
        String path_1 = input_folder_path + "\\About.cshtml";
        String path_2 = input_folder_path + "\\Home.cshtml";
        String path_3 = input_folder_path + "\\Contact.cshtml";
        assertEquals("src\\test\\resources\\TranslateToHTMLTest\\testCreateEmptyHTMLFile\\out\\_Layout+About.html", TranslateToHTML.createEmptyHTMLFile(layout_path, path_1, output_folder_path));
        assertEquals("src\\test\\resources\\TranslateToHTMLTest\\testCreateEmptyHTMLFile\\out\\_Layout+Home.html", TranslateToHTML.createEmptyHTMLFile(layout_path, path_2, output_folder_path));
        assertEquals("src\\test\\resources\\TranslateToHTMLTest\\testCreateEmptyHTMLFile\\out\\_Layout+Contact.html", TranslateToHTML.createEmptyHTMLFile(layout_path, path_3, output_folder_path));
        assertThrows(RuntimeException.class, () -> TranslateToHTML.createEmptyHTMLFile("it", "does not", "exist"));
    }

    @Test
    public void testWriteNewHTMLFile()
            throws IOException {
        String layout_path = "src\\test\\resources\\TranslateToHTMLTest\\testWriteNewHTMLFile\\in\\_Layout.cshtml";
        String target_path1 = "src\\test\\resources\\TranslateToHTMLTest\\testWriteNewHTMLFile\\in\\Home.cshtml";
        String target_path2 = "src\\test\\resources\\TranslateToHTMLTest\\testWriteNewHTMLFile\\in\\LoginForm.cshtml";
        String out_folder_path = "src\\test\\resources\\TranslateToHTMLTest\\testWriteNewHTMLFile\\out";
        String path1_test_comparable = "src\\test\\resources\\TranslateToHTMLTest\\testWriteNewHTMLFile\\in\\_Layout+Home.html";
        String path1_created_comparable = TranslateToHTML.createEmptyHTMLFile(layout_path, target_path1, out_folder_path);
        String path2_test_comparable = "src\\test\\resources\\TranslateToHTMLTest\\testWriteNewHTMLFile\\in\\_Layout+LoginForm.html";
        String path2_created_comparable = TranslateToHTML.createEmptyHTMLFile(layout_path, target_path2, out_folder_path);
        TranslateToHTML.writeNewHTMLFile(layout_path, target_path1, path1_created_comparable);
        TranslateToHTML.writeNewHTMLFile(layout_path, target_path2, path2_created_comparable);
        assertTrue(TestUtilities.compareFiles(path1_test_comparable, path1_created_comparable, false));
        assertTrue(TestUtilities.compareFiles(path2_test_comparable, path2_created_comparable, false));
        assertFalse(TestUtilities.compareFiles(path1_test_comparable, path1_created_comparable, true));
        assertFalse(TestUtilities.compareFiles(path2_test_comparable, path2_created_comparable, true));
    }

    // Данные в файл не успели записаться, а его уже хотят удалить
    @AfterAll
    public static void clearOutFolder()
            throws IOException {
        TestUtilities.clearFolder(
                "src\\test\\resources\\TranslateToHTMLTest\\testCreateEmptyHTMLFile\\out",
                "_Layout+Home.html"
        );
    }
}
