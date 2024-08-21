package org.example;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchCSHTMLTest {
    @Test
    public void testGetOnlyFileName() {
        assertEquals("Clicker", SearchCSHTML.stringsSubtract("Clicker.sln", ".sln"));
        assertEquals("Click", SearchCSHTML.stringsSubtract("Clicker.sln", "er.sln"));
        assertEquals("VladCuptsov", SearchCSHTML.stringsSubtract("VladCuptsov.programmer", ".programmer"));
        assertEquals("", SearchCSHTML.stringsSubtract("VladBober", ".VladBoberCuptsov"));
        assertEquals("Clicker.sln", SearchCSHTML.stringsSubtract("Clicker.sln", ""));
    }
    @Test
    public void testGetExtension() {
        assertEquals(".sln", SearchCSHTML.getExtension("Clicker.sln"));
        assertEquals("", SearchCSHTML.getExtension("Clicker"));
        assertEquals(".sln", SearchCSHTML.getExtension("G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\ASProject\\Clicker.sln"));
        assertEquals("", SearchCSHTML.getExtension("G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\ASProject\\Clicker"));
        assertEquals("", SearchCSHTML.getExtension(""));
        assertEquals(".java", SearchCSHTML.getExtension(".java"));
        assertEquals(".js", SearchCSHTML.getExtension("G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\ASProject\\Clicker\\wwwroot\\js\\site.js"));
        assertEquals("", SearchCSHTML.getExtension("\\java"));
        assertEquals("", SearchCSHTML.getExtension("\\"));
    }
    @Test
    public void testGetFilePath() throws FileNotFoundException {
        assertEquals("G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\ASProject\\Clicker\\wwwroot\\favicon.ico", SearchCSHTML.getFilePath("G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\ASProject\\Clicker\\wwwroot", ".ico"));
        assertEquals("G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\Translate CSHTML to HTML\\pom.xml", SearchCSHTML.getFilePath("G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\Translate CSHTML to HTML", "pom.xml"));
        assertEquals("G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\ASProject\\Clicker\\wwwroot\\css\\site.css", SearchCSHTML.getFilePath("G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\ASProject\\Clicker\\wwwroot", "site.css"));
    }

    @Test
    public void testSearchingForNameOfProject() {
        String path_with_sln = "G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\ASProject";
        String path_without_sln = "G:\\Жесткий диск\\ВУЗ ИГУ\\2 КУРС";
        assertEquals("G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\ASProject\\Clicker.sln", SearchCSHTML.searchingForPathOfProject(new File(path_with_sln), ".sln"));
        assertEquals("G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\ASProject\\Clicker.sln", SearchCSHTML.searchingForPathOfProject(new File(path_with_sln), "Clicker.sln"));
        assertEquals("G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\ASProject\\README.md", SearchCSHTML.searchingForPathOfProject(new File(path_with_sln), "ME.md"));
        assertEquals("", SearchCSHTML.searchingForPathOfProject(new File(path_without_sln), ".sln"));
        assertEquals("", SearchCSHTML.searchingForPathOfProject(new File(path_with_sln), ".absent"));
    }

    @Test
    public void testGetFolderPath() throws FileNotFoundException {
        assertEquals("G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\ASProject\\Clicker\\wwwroot\\img\\Mountains", SearchCSHTML.getFolderPath("G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\ASProject\\Clicker\\wwwroot", ".jpg"));
        assertEquals("G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\ASProject\\Clicker", SearchCSHTML.getFolderPath("G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\ASProject", ".sln"));
        assertThrows(FileNotFoundException.class, () -> SearchCSHTML.getFolderPath("G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\ACMProject", ".sln"));
        assertThrows(FileNotFoundException.class, () -> SearchCSHTML.getFolderPath("G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\ASProject", ".absent"));
    }
}
