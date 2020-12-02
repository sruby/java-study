package io.github.sruby.guava;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @description: test
 * @author: sruby
 * @create: 2020-12-01 11:24
 */
@Slf4j
public class BloomFilterTest {
    @Test
    public void testMightContain(){
        BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(),1500,0.01);
        bloomFilter.put(1);
        log.info("result:{}",bloomFilter.mightContain(1));
        log.info("result:{}",bloomFilter.mightContain(2));
    }
}
