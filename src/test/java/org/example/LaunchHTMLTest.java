package org.example;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LaunchHTMLTest {
    @Test
    public void testReadDataIni()
            throws IOException {
        String[] data = LaunchHTML.readDataIni("src\\test\\resources\\LaunchHTMLTest\\testconfig.ini");
        String[] comparable_data = new String[] {
                "OAO",
                "ABC",
                "BOBER",
                "KURWA",
                "PERDOLE"
        };
        for (int i = 0; i < data.length; i++) {
            assertEquals(comparable_data[i], data[i]);
        }
    }
}
