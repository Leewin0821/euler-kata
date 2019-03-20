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

    @Test
    public void shouldGetPythagoreanTriplet() {
        List<Integer> nums1 = Solutions.getPythagoreanTripletBySum(12);
        List<Integer> nums2 = Solutions.getPythagoreanTripletBySum(1000);
        assertEquals(60, nums1.stream().mapToInt(Integer::intValue).reduce((pre, next) -> pre * next).orElse(0));
        assertEquals(31875000, nums2.stream().mapToInt(Integer::intValue).reduce((pre, next) -> pre * next).orElse(0));
    }

    @Test
    public void shouldGetSumOfPrimesBelowValue() {
        long result1 = Solutions.getSumOfPrimesBelow(10);
        long result2 = Solutions.getSumOfPrimesBelow(2000000);
        assertEquals(17L, result1);
        assertEquals(142913828922L, result2);
    }

    @Test
    public void shouldGetGreatestProductOfSameDirection() {
        int size = 4;
        int step = 20;
        String input = "08 02 22 97 38 15 00 40 00 75 04 05 07 78 52 12 50 77 91 08\n" +
                "49 49 99 40 17 81 18 57 60 87 17 40 98 43 69 48 04 56 62 00\n" +
                "81 49 31 73 55 79 14 29 93 71 40 67 53 88 30 03 49 13 36 65\n" +
                "52 70 95 23 04 60 11 42 69 24 68 56 01 32 56 71 37 02 36 91\n" +
                "22 31 16 71 51 67 63 89 41 92 36 54 22 40 40 28 66 33 13 80\n" +
                "24 47 32 60 99 03 45 02 44 75 33 53 78 36 84 20 35 17 12 50\n" +
                "32 98 81 28 64 23 67 10 26 38 40 67 59 54 70 66 18 38 64 70\n" +
                "67 26 20 68 02 62 12 20 95 63 94 39 63 08 40 91 66 49 94 21\n" +
                "24 55 58 05 66 73 99 26 97 17 78 78 96 83 14 88 34 89 63 72\n" +
                "21 36 23 09 75 00 76 44 20 45 35 14 00 61 33 97 34 31 33 95\n" +
                "78 17 53 28 22 75 31 67 15 94 03 80 04 62 16 14 09 53 56 92\n" +
                "16 39 05 42 96 35 31 47 55 58 88 24 00 17 54 24 36 29 85 57\n" +
                "86 56 00 48 35 71 89 07 05 44 44 37 44 60 21 58 51 54 17 58\n" +
                "19 80 81 68 05 94 47 69 28 73 92 13 86 52 17 77 04 89 55 40\n" +
                "04 52 08 83 97 35 99 16 07 97 57 32 16 26 26 79 33 27 98 66\n" +
                "88 36 68 87 57 62 20 72 03 46 33 67 46 55 12 32 63 93 53 69\n" +
                "04 42 16 73 38 25 39 11 24 94 72 18 08 46 29 32 40 62 76 36\n" +
                "20 69 36 41 72 30 23 88 34 62 99 69 82 67 59 85 74 04 36 16\n" +
                "20 73 35 29 78 31 90 01 74 31 49 71 48 86 81 16 23 57 05 54\n" +
                "01 70 54 71 83 51 54 69 16 92 33 48 61 43 52 01 89 19 67 48";
        int result = Solutions.getGreatestProductFrom(input, size, step);
        assertEquals(70600674, result);
    }
}