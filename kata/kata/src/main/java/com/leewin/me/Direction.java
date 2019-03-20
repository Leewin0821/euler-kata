import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author 苏渺
 */
public enum Direction {
    /**
     * Enum function for problem 11
     */
    LEFT, RIGHT, TOP, DOWN, UPPER_LEFT, UPPER_RIGHT, LOWER_LEFT, LOWER_RIGHT;

    private static BiFunction<Integer, Integer, List<Integer>> productLeft =
            (step, size) -> IntStream
                    .range(0, size)
                    .map((i) -> -1 * i)
                    .boxed()
                    .collect(Collectors.toList());

    private static BiFunction<Integer, Integer, List<Integer>> productRight =
            (step, size) -> IntStream
                    .range(0, size)
                    .boxed()
                    .collect(Collectors.toList());

    private static BiFunction<Integer, Integer, List<Integer>> productTop =
            (step, size) -> IntStream
                    .range(0, size)
                    .map((i) -> -step * i)
                    .boxed()
                    .collect(Collectors.toList());

    private static BiFunction<Integer, Integer, List<Integer>> productDown =
            (step, size) -> IntStream
                    .range(0, size)
                    .map((i) -> step * i)
                    .boxed()
                    .collect(Collectors.toList());

    private static BiFunction<Integer, Integer, List<Integer>> productUpperLeft =
            (step, size) -> IntStream
                    .range(0, size)
                    .map((i) -> -step * i - i)
                    .boxed()
                    .collect(Collectors.toList());

    private static BiFunction<Integer, Integer, List<Integer>> productLowerLeft =
            (step, size) -> IntStream
                    .range(0, size)
                    .map((i) -> step * i - i)
                    .boxed()
                    .collect(Collectors.toList());

    private static BiFunction<Integer, Integer, List<Integer>> productUpperRight =
            (step, size) -> IntStream
                    .range(0, size)
                    .map((i) -> -step * i + i)
                    .boxed()
                    .collect(Collectors.toList());

    private static BiFunction<Integer, Integer, List<Integer>> productLowerRight =
            (step, size) -> IntStream
                    .range(0, size)
                    .map((i) -> step * i + i)
                    .boxed()
                    .collect(Collectors.toList());

    static {
        LEFT.vector = productLeft;
        RIGHT.vector = productRight;
        TOP.vector = productTop;
        DOWN.vector = productDown;
        UPPER_LEFT.vector = productUpperLeft;
        UPPER_RIGHT.vector = productUpperRight;
        LOWER_LEFT.vector = productLowerLeft;
        LOWER_RIGHT.vector = productLowerRight;
    }

    private BiFunction<Integer, Integer, List<Integer>> vector;

    public BiFunction<Integer, Integer, List<Integer>> getVector() {
        return vector;
    }
}