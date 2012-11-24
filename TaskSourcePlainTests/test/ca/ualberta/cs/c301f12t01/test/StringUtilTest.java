package ca.ualberta.cs.c301f12t01.test;

import static org.junit.Assert.*;
import java.util.Arrays;

import org.junit.Test;

import ca.ualberta.cs.c301f12t01.util.StringUtils;

/* TODO: write more tests and comments. */

public class StringUtilTest {

    @Test
    public void testOnStrings() {
        final String expectedResult = "String A, String B, String C"; 
        
        final String[] testStrings = new String[]{
                "String A",
                "String B",
                "String C"
        };
        
        String result = StringUtils.joinToString(Arrays.asList(testStrings), ", ");
        
        assertEquals(expectedResult, result);
    }
    
}
