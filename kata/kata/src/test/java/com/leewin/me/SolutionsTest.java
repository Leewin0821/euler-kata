import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author 苏渺
 */
public class SolutionsTest {
    @Test
    public void shouldPassFibonacci() {
        assertThat(Solutions.fibonacci(1)).isEqualTo(1L);
        assertThat(Solutions.fibonacci(2)).isEqualTo(2L);
        assertThat(Solutions.fibonacci(3)).isEqualTo(3L);
        assertThat(Solutions.fibonacci(4)).isEqualTo(5L);
        assertThat(Solutions.fibonacci(5)).isEqualTo(8L);
        assertThat(Solutions.fibonacci(6)).isEqualTo(13L);
        assertThat(Solutions.fibonacci(7)).isEqualTo(21L);
        assertThat(Solutions.fibonacci(8)).isEqualTo(34L);
        assertThat(Solutions.fibonacci(9)).isEqualTo(55L);
        assertThat(Solutions.fibonacci(10)).isEqualTo(89L);
    }

    @Test
    public void shouldGetPrimeFactor() {
        List<Long> expectedList = Arrays.asList(5L, 7L, 13L, 29L);
        assertThat(Solutions.getPrimeFactorsOf(13195L)).isEqualTo(expectedList);
        List<Long> factors = Solutions.getPrimeFactorsOf(600851475143L);
        assertThat(factors.stream().mapToLong(f -> f).max().orElse(0L)).isEqualTo(6857L);
    }

    @Test
    public void shouldGetLargestPalindromeNumber() {
        assertThat(Solutions.getPalindromeFromDigits(2)).isEqualTo(9009);
        assertThat(Solutions.getPalindromeFromDigits(3)).isEqualTo(906609);
    }

    @Test
    public void shouldPassPalindrome() {
        assertTrue(Solutions.isPalindrome(9009));
    }

    @Test
    public void shouldGetSmallestLcm() {
        int result = Solutions.getSmallestLcm(1, 10);
        int result2 = Solutions.getSmallestLcm(1, 20);
        assertEquals(2520, result);
        assertEquals(232792560, result2);
    }

    @Test
    public void shouldGetGcd() {
        int result = Solutions.gcd(462, 1071);
        assertEquals(21, result);
    }

    @Test
    public void shouldGetLargestPrimeNumberOfGivenIndex() {
        int result = Solutions.getLargestPrimeNumberOfIndex(6);
        int result2 = Solutions.getLargestPrimeNumberOfIndex(10001);
        assertEquals(13, result);
        assertEquals(104743, result2);
    }
}