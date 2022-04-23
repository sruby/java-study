package io.github.sruby.json;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Gson test
 *
 * @author Sruby
 * @date 22/4/2022 09:41
 */
public class JsonTest {
    @Test
    public void testObject(){
        new Gson().fromJson("{\"a\":1}", HashMap.class);
    }

    @Test
    public void testArray(){
        HashMap[] hashMaps = new Gson().fromJson("[{\"a\":1},{\"b\":1}]", HashMap[].class);
        List<HashMap> hashMaps1 = Arrays.asList(hashMaps);
    }
}
