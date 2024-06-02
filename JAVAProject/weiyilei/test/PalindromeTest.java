import javafx.application.Application;
import javafx.stage.Stage;
import static org.assertj.core.api.Assertions.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.*;


public class PalindromeTest {
    @Test
    public void testIsPalindrome() {
        Palindrome palindrome = new Palindrome();
        assertTrue(palindrome.judge("abccba"));
    }

    @Test
    public void testNotPalindrome() {
        Palindrome palindrome = new Palindrome();
        assertFalse(palindrome.judge("abc"));
    }

    @Test
    public void testEmptyPalindrome() {
        Palindrome palindrome = new Palindrome();
        assertFalse(palindrome.judge(""));
    }
}
