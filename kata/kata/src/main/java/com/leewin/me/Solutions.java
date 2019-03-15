import java.util.*;
import java.util.stream.IntStream;

/**
 * @author 苏渺
 */
class Solutions {
    private static TreeMap<Integer, Long> valueMap = new TreeMap<>();

    static Long fibonacci(int param) {
        Long value = valueMap.get(param);
        if (Objects.isNull(value)) {
            switch (param) {
                case 0:
                    value = 0L;
                    valueMap.put(param, value);
                    break;
                case 1:
                    value = 1L;
                    valueMap.put(param, value);
                    break;
                case 2:
                    value = 2L;
                    valueMap.put(param, value);
                    break;
                default:
                    value = fibonacci(param - 2) + fibonacci(param - 1);
                    valueMap.put(param, value);
                    break;
            }
        }
        return value;
    }

    static List<Long> getPrimeFactorsOf(Long num) {
        Long origin = num;
        List<Long> results = new ArrayList<>();
        for (Long index = 2L; index <= Math.sqrt(num); index++) {
            if (num % index == 0) {
                results.add(index);
                num = num / index;
            }
        }
        Long times = results.stream().reduce((pre, next) -> pre * next).orElse(0L);
        results.add((origin / times));
        return results;
    }

    static Integer getPalindromeFromDigits(Integer digit) {
        int maxValue = Double.valueOf(Math.pow(10, digit)).intValue();
        int minValue = Double.valueOf(Math.pow(10, digit - 1)).intValue();
        return IntStream
                .range(minValue, maxValue)
                .map(outer -> IntStream.range(minValue, maxValue)
                                        .filter(inner -> isPalindrome(outer * inner))
                                        .map(inner -> outer * inner)
                                        .max()
                                        .orElse(0))
                .max()
                .orElse(0);
    }

    static boolean isPalindrome(int value) {
        double power = 0.0;
        List<Integer> origin = new LinkedList<>();
        while (value > Math.pow(10, power)) {
            origin.add(Double.valueOf(value / Math.pow(10, power)).intValue() % 10);
            power++;
        }
        List<Integer> destination = new ArrayList<>(origin);
        Collections.reverse(destination);
        return origin.equals(destination);
    }

    static int getSmallestLcm(int start, int end) {
        return IntStream.range(start, end).reduce((pre, next) -> pre * next / gcd(pre, next)).orElse(0);
    }

    static int gcd(int smaller, int larger) {
        if (smaller == 0) {
            return larger;
        } else {
            return gcd(larger % smaller, smaller);
        }
    }

    static int getLargestPrimeNumberOfIndex(int index) {
        int start = 2;
        List<Integer> list = new ArrayList<>();
        list.add(2);
        while (list.size() < index) {
            start++;
            if (isPrime(start)) {
                list.add(start);
            }
        }
        return list.stream().mapToInt(i -> i).max().orElse(0);
    }

    private static boolean isPrime(int num) {
        if (num > 2 && num % 2 == 0) {
            return false;
        }
        int top = Double.valueOf(Math.sqrt(num)).intValue() + 1;
        for (int i = 3; i < top; i += 2) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

}