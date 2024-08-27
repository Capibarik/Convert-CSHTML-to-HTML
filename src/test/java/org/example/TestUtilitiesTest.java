package org.example;

import static utility.TestUtilities.compareString;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestUtilitiesTest {
    @Test
    public void testCompareStrings() {
        assertTrue(compareString("HTML", "HTML"));
        assertTrue(compareString("H T M L", "H TM L"));
        assertTrue(compareString("H T M L", "H T M L"));
        assertTrue(compareString("XML HTM L", "XML HT  ML"));
        assertTrue(compareString("", ""));
        assertTrue(compareString(
                "</main>\n</body>\n</html>", "</main></body></html>"));
        assertTrue(compareString(
                "</main>\n</body>\n</html>", "</main></body>\n\n</html>"));
        assertFalse(compareString(
                "</mainOAO>\n</body>\n</html>", "</main></body>\n\n</html>"));
        assertFalse(compareString("XML", "HTML"));
        assertFalse(compareString("", "H"));
        assertFalse(compareString("GODOT", "H"));
        assertFalse(compareString("H", "G"));
        assertFalse(compareString("HTML", "GODOT"));
        assertFalse(compareString("HTML", "GODOT"));
    }
}
