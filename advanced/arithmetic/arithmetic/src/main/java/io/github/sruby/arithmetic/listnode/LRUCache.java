package io.github.sruby.arithmetic.listnode;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 146. LRU Cache
 * @author sruby
 */
public class LRUCache {
    private Map<Integer, Integer> cache = new LinkedHashMap<>();
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (cache.containsKey(key)){
            Integer value = cache.remove(key);
            cache.put(key,value);
            return value;
        }
        return  -1;
    }

    public void put(int key, int value) {
        //先做值是否存在的判断，再做容量判断，如果先做容量判断，如何存在相同的值会导致先淘汰
        if (cache.containsKey(key)){
            cache.put(key,value);
            makeReacently(key);
            return ;
        }

        if (cache.size() >= capacity){
            //获取链表头部的数据，最老的数据
            Integer oldestKey = cache.keySet().iterator().next();
            cache.remove(oldestKey);
        }
        cache.put(key,value);
    }

    private void makeReacently(int key) {
        Integer value = cache.remove(key);
        //添加到链表尾部
        cache.put(key,value);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
