package com.scai.filemanager_wave2_v02;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamsTest {

    @Test
    void mappingStreams() {
        Stream.of("a", "b", "c")
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }

    @Test
    void peekStreams() {
        Stream.of("a", "b", "c")
                .map(String::toUpperCase)
                .peek(System.out::println)
                .map(String::toLowerCase)
                .forEach(System.out::println);
    }

    @Test
    void filterStreams() {
        Stream.of("a", "b", "c")
                .filter(s -> s.equals("b"))
                .forEach(System.out::println);
    }

    @Test
    void flatMapStreams() {
        Stream.of("a", "b", "c")
                .flatMap(s -> Stream.of(s.toUpperCase(), s.toLowerCase()))
                .forEach(System.out::println);

        System.out.println("----");

        System.out.println(IntStream.range(0, 100).boxed()
                .sorted(Comparator.comparing(Integer::intValue))
                .reduce(Integer::max));

        System.out.println("----");
    }

}
