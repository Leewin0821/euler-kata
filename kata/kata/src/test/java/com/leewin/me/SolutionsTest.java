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

    @Test
    public void shouldGetLargestProductBySize() {
        String sample = "73167176531330624919225119674426574742355349194934\n" +
                "96983520312774506326239578318016984801869478851843\n" +
                "85861560789112949495459501737958331952853208805511\n" +
                "12540698747158523863050715693290963295227443043557\n" +
                "66896648950445244523161731856403098711121722383113\n" +
                "62229893423380308135336276614282806444486645238749\n" +
                "30358907296290491560440772390713810515859307960866\n" +
                "70172427121883998797908792274921901699720888093776\n" +
                "65727333001053367881220235421809751254540594752243\n" +
                "52584907711670556013604839586446706324415722155397\n" +
                "53697817977846174064955149290862569321978468622482\n" +
                "83972241375657056057490261407972968652414535100474\n" +
                "82166370484403199890008895243450658541227588666881\n" +
                "16427171479924442928230863465674813919123162824586\n" +
                "17866458359124566529476545682848912883142607690042\n" +
                "24219022671055626321111109370544217506941658960408\n" +
                "07198403850962455444362981230987879927244284909188\n" +
                "84580156166097919133875499200524063689912560717606\n" +
                "05886116467109405077541002256983155200055935729725\n" +
                "71636269561882670428252483600823257530420752963450";
        long result = Solutions.getLargestProductBySize(sample, 4);
        long result2 = Solutions.getLargestProductBySize(sample, 13);
        assertEquals(5832L, result);
        assertEquals(23514624000L, result2);
    }
}