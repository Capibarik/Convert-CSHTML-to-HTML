package org.example;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {
    @Test
    public void testGetOnlyFileName() {
        assertEquals("Clicker", App.getOnlyFileName("Clicker.sln", ".sln"));
        assertEquals("Click", App.getOnlyFileName("Clicker.sln", "er.sln"));
        assertEquals("VladCuptsov",
                App.getOnlyFileName("VladCuptsov.programmer", ".programmer")
        );
        assertEquals("", App.getOnlyFileName("VladBober", ".VladBoberCuptsov"));
        assertEquals("Clicker.sln", App.getOnlyFileName("Clicker.sln", ""));
    }
    @Test
    public void testGetFile() {

    }
    @Test
    public void testSearchingForNameOfProject() {
        File file_with_sln = new File("G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\ASProject");
        File file_with_no_sln = new File("G:\\Жесткий диск\\ВУЗ ИГУ\\2 КУРС");
        File file_with_java = new File("G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\Translate CSHTML to HTML\\src");
        assertEquals("Clicker.sln", App.searchingForNameOfProject(
                file_with_sln, ".sln")
        );
        assertEquals("Clicker.sln", App.searchingForNameOfProject(
                file_with_sln, "Clicker.sln")
        );
        assertEquals("App.java", App.searchingForNameOfProject(
                file_with_java, ".java")
        );
        assertNull(App.searchingForNameOfProject(
                file_with_no_sln, ".sln")
        );
        assertNull(App.searchingForNameOfProject(
                file_with_sln, ".absent")
        );
    }
    @Test
    public void testGetNameOfProject() throws FileNotFoundException {
        assertEquals("Clicker.sln", App.getNameOfProject(
                "G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\ASProject",
                ".sln")
        );
        assertThrows(FileNotFoundException.class, () -> App.getNameOfProject(
                "G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\ACMProject",
                ".sln")
        );
        assertThrows(FileNotFoundException.class, () -> App.getNameOfProject(
                "G:\\Жесткий диск\\ВУЗ ИГУ\\1 КУРС\\ВЕБ-ТЕХНОЛОГИИ\\Повторение\\ASProject",
                ".absent")
        );
    }
}
