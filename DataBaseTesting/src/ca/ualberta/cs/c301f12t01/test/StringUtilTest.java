package ca.ualberta.cs.c301f12t01.test;

import java.util.Arrays;

import ca.ualberta.cs.c301f12t01.util.StringUtils;
import android.test.AndroidTestCase;

/* TODO: write more tests and comments. */

public class StringUtilTest extends AndroidTestCase {

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
