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
        List<Integer> origin = extractDigitsFrom(value);
        List<Integer> destination = new ArrayList<>(origin);
        Collections.reverse(destination);
        return origin.equals(destination);
    }

    private static List<Integer> extractDigitsFrom(long value) {
        List<Integer> digits = new LinkedList<>();
        double power = 0.0;
        while (value > Math.pow(10, power)) {
            digits.add(Double.valueOf(value / Math.pow(10, power)).intValue() % 10);
            power++;
        }
        return digits;
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

    static long getSumOfDigitsOfBinary(int power) {
        BigDecimal result = BigDecimal.ZERO;
        BigDecimal total = BigDecimal.valueOf(2L).pow(power);
        while (total.compareTo(BigDecimal.ZERO) > 0) {
            result = result.add(total.remainder(BigDecimal.TEN));
            total = total.divide(BigDecimal.TEN, RoundingMode.FLOOR);
        }
        return result.longValue();
    }

    static long findBiggestValuePathFrom(String input) {
        int[][] triangle = convertToTriangle(input);
        int height = triangle.length;
        for (int i = height - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                triangle[i][j] += Math.max(triangle[i + 1][j], triangle[i + 1][j + 1]);
            }
        }
        return triangle[0][0];
    }

    private static int[][] convertToTriangle(String input) {
        return Stream
                .of(input.split("\\n"))
                .map(s -> Stream
                        .of(s.split("\\s"))
                        .mapToInt(Integer::valueOf)
                        .toArray()
                )
                .toArray(int[][]::new);
    }

    static int countSundayOnFirstOfMonth(int start, int end) {
        AtomicInteger counter = new AtomicInteger(0);
        IntStream
                .range(start, end + 1)
                .forEach(year -> IntStream
                        .range(1, 13)
                        .forEach(month -> {
                            if (LocalDate.of(year, month, 1).getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
                                counter.getAndIncrement();
                            }
                        }));
        return counter.get();
    }

    static int getSumOfDigitsOfFactorial(int num) {
        BigDecimal product = IntStream
                .range(1, num + 1)
                .mapToObj(BigDecimal::new)
                .reduce(BigDecimal::multiply)
                .orElse(BigDecimal.ZERO);
        return product.toString().chars().map(c -> c - 48).sum();
    }

    static long getSumOfFactorsFrom(long input) {
        return LongStream
                .range(1, input)
                .filter(i -> input % i == 0)
                .sum();
    }

    static long calAllScoresOfNames() {
        AtomicInteger counter = new AtomicInteger(1);
        Map<String, Integer> nameMap;
        try (Stream<String> stream = Files.lines(Paths.get("src/test/resources/names.txt"))) {
            nameMap = stream
                    .flatMap(s -> Stream.of(s.split(",")))
                    .map(s -> s.replaceAll("\"", ""))
                    .sorted(Comparator.comparing(String::toString))
                    .collect(Collectors.toMap(Function.identity(), s -> counter.getAndIncrement()));
        } catch (IOException ioe) {
            return 0L;
        }
        return nameMap
                .entrySet()
                .stream()
                .mapToLong(m -> m
                        .getKey()
                        .toLowerCase()
                        .chars()
                        .map(c -> c - 96)
                        .sum() * m.getValue()
                ).sum();
    }

    static int getSumOfNonAbundantNums(int upperLimit) {
        List<Integer> abundantNums = IntStream
                .rangeClosed(2, upperLimit)
                .filter(Solutions::isAbundantNum)
                .boxed()
                .collect(Collectors.toList());
        return IntStream.range(1, upperLimit)
                .filter(i -> {
                    boolean result = true;
                    for (Integer j : abundantNums) {
                        if (i > j && abundantNums.contains(i - j)) {
                            result = false;
                            break;
                        }
                    }
                    return result;
                })
                .sum();
    }

    private static boolean isAbundantNum(int num) {
        return IntStream
                .range(1, num)
                .filter(i -> num % i == 0)
                .sum() > num;
    }
}
