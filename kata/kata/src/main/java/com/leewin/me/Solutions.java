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

    static long getLargestProductBySize(String sample, int size) {
        List<Long> integers = sample.chars().map(Character::getNumericValue).filter(i -> i >= 0).mapToLong(Long::valueOf).boxed().collect(Collectors.toList());
        List<List<Long>> valueGroup = new ArrayList();
        IntStream.range(0, integers.size() - size + 1).forEach(index -> valueGroup.add(integers.subList(index, index + size)));
        return valueGroup
                .stream()
                .map(value -> value.stream().reduce((pre, next) -> pre * next).orElse(0L))
                .mapToLong(Long::valueOf)
                .max()
                .orElse(0);
    }

    static List<Integer> getPythagoreanTripletBySum(int sum) {
        List<Integer> list = new ArrayList<>();
        IntStream.range(1, Math.round(sum >> 1)).forEach(c -> fitPythagoreanFormula(c, sum, list));
        return list;
    }

    private static void fitPythagoreanFormula(int c, int sum, List<Integer> list) {
        IntStream.range(1, c).forEach(b -> {
            int a = sum - b - c;
            if (a < b && a * a + b * b == c * c) {
                list.add(a);
                list.add(b);
                list.add(c);
            }
        });
    }

    static long getSumOfPrimesBelow(int upperLimit) {
        return IntStream
                .range(2, upperLimit)
                .filter(Solutions::isPrime)
                .mapToLong(Long::valueOf)
                .sum();
    }

    static int getGreatestProductFrom(String input, int size, int step) {
        List<Integer> list = convertToList(input);
        return IntStream
                .range(0, list.size())
                .map(i -> Stream
                        .of(Direction.values())
                        .map(direction -> direction.getVector().apply(step, size))
                        .filter(v -> {
                            List<Integer> coordinates = extractCoordinate(i, v)
                                    .map(j -> j % step)
                                    .boxed()
                                    .collect(Collectors.toList());
                            int max = coordinates.stream().mapToInt(Integer::intValue).max().orElse(0);
                            int min = coordinates.stream().mapToInt(Integer::intValue).min().orElse(0);
                            return Math.abs(max - min) <= 3;
                        })
                        .map(v -> extractCoordinate(i, v)
                                .map(list::get)
                                .reduce((pre, next) -> pre * next)
                                .orElse(0)
                        )
                        .mapToInt(Integer::intValue)
                        .max()
                        .orElse(0)
                )
                .max()
                .orElse(0);
    }

    private static IntStream extractCoordinate(Integer index, List<Integer> vector) {
        return vector
                .stream()
                .mapToInt(j -> j + index)
                .filter(j -> j >= 0 && j < 400);
    }

    private static List<Integer> convertToList(String input) {
        return Stream
                .of(input.split("\\s"))
                .mapToInt(Integer::valueOf)
                .boxed()
                .collect(Collectors.toList());
    }

    static long getTriangleNumOverDivisor(int divisorNum) {
        List<Long> divisors = new ArrayList<>();
        AtomicLong counter = new AtomicLong(3L);
        long angleNum = 3L;
        while(divisors.size() < divisorNum) {
            divisors.clear();
            angleNum += counter.getAndIncrement();
            for (long i = 1; i <= Math.round(Math.sqrt(angleNum)); i++) {
                if (angleNum % i == 0) {
                    divisors.add(i);
                    divisors.add(angleNum % i);
                }
            }
        }
        return angleNum;
    }

    static long getChainFromStartNum(long start) {
        AtomicLong counter = new AtomicLong(1L);
        return getNumByRule(start, counter);
    }

    private static long getNumByRule(long input, AtomicLong counter) {
        while (input != 1L) {
            if (input % 2 == 0) {
                counter.getAndIncrement();
                return getNumByRule(input / 2, counter);
            } else {
                counter.getAndIncrement();
                return getNumByRule(3 * input + 1, counter);
            }
        }
        return counter.get();
    }
}
