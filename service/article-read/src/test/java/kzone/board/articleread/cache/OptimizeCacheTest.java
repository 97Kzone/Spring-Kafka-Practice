package kzone.board.articleread.cache;

import lombok.*;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.assertj.core.api.Assertions.*;

class OptimizeCacheTest {
    @Test
    void parseDataTest() {
        parseDataTest("data", 10);
        parseDataTest(3L, 10);
        parseDataTest(3, 10);
        parseDataTest(new TestClass("TEST"), 10);
    }

    void parseDataTest(Object data, long ttlSeconds) {
        // given
        OptimizeCache optimizeCache = OptimizeCache.of(data, Duration.ofSeconds(ttlSeconds));
        System.out.println("optimizeCache = " + optimizeCache);

        // when
        Object resolvedData = optimizeCache.parseData(data.getClass());

        // then
        System.out.println("resolvedData = " + resolvedData);
        assertThat(resolvedData).isEqualTo(data);
    }

    @Getter
    @ToString
    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    static class TestClass {
        String testData;
    }
}