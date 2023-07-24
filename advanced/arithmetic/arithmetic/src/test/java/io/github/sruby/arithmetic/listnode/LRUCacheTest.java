package io.github.sruby.arithmetic.listnode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * test
 *
 * @author Sruby
 * @date 2023/7/24 21:52
 */
class LRUCacheTest {

    /**
     * ["LRUCache","get","put","get","put","put","get","get"]
     * [[2],[2],[2,6],[1],[1,5],[1,2],[1],[2]]
     * 预期结果
     * [null,-1,null,-1,null,null,2,6]
     */
    @Test
    public void test() {
        LRUCache lruCache = new LRUCache(2);
        int value = lruCache.get(2);
        lruCache.put(2,6);
        lruCache.get(1);
        lruCache.put(1,5);
        lruCache.put(1,2);
        lruCache.get(1);
        int result = lruCache.get(2);
        assertEquals(result,6);
    }

}