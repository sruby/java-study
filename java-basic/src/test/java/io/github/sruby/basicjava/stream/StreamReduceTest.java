package io.github.sruby.basicjava.stream;

import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * stream reduce test
 * >https://www.baeldung.com/java-stream-reduce
 *
 * @author Sruby
 * @date 5/7/2023 4:36 PM
 */
@State(Scope.Thread)
public class StreamReduceTest {
    /**
     * Identity – an element that is the initial value of the reduction (还原) operation and the default result if the stream is empty
     * Accumulator (蓄能器) – a function that takes two parameters: a partial result of the reduction (还原) operation and the next element of the stream
     * Combiner – a function used to combine the partial result of the reduction (还原) operation when the reduction (还原) is parallelized or when there's a mismatch between the types of the accumulator (蓄能器) arguments and the types of the accumulator (蓄能器) implementation
     *
     * the Integer value 0 is the identity
     */
    @Test
    public void testSimple() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int result = numbers
                .stream()
                .reduce(0, (subtotal, element) -> subtotal + element);
        assertThat(result).isEqualTo(21);
    }

//    @State(Scope.Thread)
    private final List<User> userList = createUsers();

    private List<User> createUsers() {
        return Arrays.asList(new User("A",1),new User("b",2));
    }

    @Benchmark
    public Integer executeReduceOnParallelizedStream() {
        return this.userList
                .parallelStream()
                .reduce(
                        0, (partialAgeResult, user) -> partialAgeResult + user.getAge(), Integer::sum);
    }

    @Benchmark
    public Integer executeReduceOnSequentialStream() {
        return this.userList
                .stream()
                .reduce(
                        0, (partialAgeResult, user) -> partialAgeResult + user.getAge(), Integer::sum);
    }

}
