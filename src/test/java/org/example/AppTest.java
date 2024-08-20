package org.example;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {
    @Test
    public void testGetOnlyFileName() {
        assertEquals("Clicker", App.stringsSubtract("Clicker.sln", ".sln"));
        assertEquals("Click", App.stringsSubtract("Clicker.sln", "er.sln"));
        assertEquals("VladCuptsov", App.stringsSubtract("VladCuptsov.programmer", ".programmer"));
        assertEquals("", App.stringsSubtract("VladBober", ".VladBoberCuptsov"));
        assertEquals("Clicker.sln", App.stringsSubtract("Clicker.sln", ""));
    }
    @Test
    public void testGetExtension() {
        assertEquals(".sln", App.getExtension("Clicker.sln"));
        assertEquals("", App.getExtension("Clicker"));
        assertEquals(".sln", App.getExtension("G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\ASProject\\Clicker.sln"));
        assertEquals("", App.getExtension("G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\ASProject\\Clicker"));
        assertEquals("", App.getExtension(""));
        assertEquals(".java", App.getExtension(".java"));
        assertEquals(".java", App.getExtension("G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\Translate CSHTML to HTML\\src\\main\\java\\org\\example\\App.java"));
        assertEquals("", App.getExtension("\\java"));
        assertEquals("", App.getExtension("\\"));
    }
    @Test
    public void testGetFilePath() throws FileNotFoundException {
        assertEquals("G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\Translate CSHTML to HTML\\src\\main\\java\\org\\example\\App.java", App.getFilePath("G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\Translate CSHTML to HTML\\src", ".java"));
        assertEquals("G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\Translate CSHTML to HTML\\pom.xml", App.getFilePath("G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\Translate CSHTML to HTML", "pom.xml"));
        assertEquals("G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\Translate CSHTML to HTML\\src\\test\\java\\org\\example\\AppTest.java", App.getFilePath("G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\Translate CSHTML to HTML\\src", "AppTest.java"));
    }

    @Test
    public void testSearchingForNameOfProject() {
        String path_with_sln = "G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\ASProject";
        String path_without_sln = "G:\\Жесткий диск\\ВУЗ ИГУ\\2 КУРС";
        String path_with_java = "G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\Translate CSHTML to HTML\\src";
        assertEquals("G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\ASProject\\Clicker.sln", App.searchingForPathOfProject(new File(path_with_sln), ".sln"));
        assertEquals("G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\ASProject\\Clicker.sln", App.searchingForPathOfProject(new File(path_with_sln), "Clicker.sln"));
        assertEquals("G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\Translate CSHTML to HTML\\src\\main\\java\\org\\example\\App.java", App.searchingForPathOfProject(new File(path_with_java), ".java"));
        assertEquals("", App.searchingForPathOfProject(new File(path_without_sln), ".sln"));
        assertEquals("", App.searchingForPathOfProject(new File(path_with_sln), ".absent"));
    }

    @Test
    public void testGetFolderPath() throws FileNotFoundException {
        assertEquals("G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\Translate CSHTML to HTML\\src\\main\\java\\org\\example\\App", App.getFolderPath("G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\Translate CSHTML to HTML\\src", ".java"));
        assertEquals("G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\ASProject\\Clicker", App.getFolderPath("G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\ASProject", ".sln"));
        assertThrows(FileNotFoundException.class, () -> App.getFolderPath("G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\ACMProject", ".sln"));
        assertThrows(FileNotFoundException.class, () -> App.getFolderPath("G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\ASProject", ".absent"));
    }
}
