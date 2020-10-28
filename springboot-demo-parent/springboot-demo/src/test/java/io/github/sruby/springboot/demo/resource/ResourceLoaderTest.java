package io.github.sruby.springboot.demo.resource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.*;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

/**
 * @author sruby
 * @date 2020/10/28 16:21
 */
class ResourceLoaderTest {

    @Test
    public void testDefaultResourceLoader() {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("application.properties");
        Assertions.assertTrue(resource instanceof ClassPathResource);
        Assertions.assertTrue(resource.exists());

        Resource resource1 = resourceLoader.getResource("file:D:/application.properties");
        Assertions.assertTrue(resource1 instanceof UrlResource);

        Resource resource2 = resourceLoader.getResource("http://www.baidu.com");
        Assertions.assertTrue(resource2 instanceof UrlResource);
    }

    @Test
    public void testFileSystemResourceLoader() {
        FileSystemResourceLoader fileSystemResourceLoader = new FileSystemResourceLoader();
        Resource resource = fileSystemResourceLoader.getResource("application.properties");
        Assertions.assertTrue(resource instanceof FileSystemResource);
    }

    @Test
    public void testPathMatchingResourcePatternResolver() throws IOException {
        FileSystemResourceLoader resourceLoader = new FileSystemResourceLoader();
        //
        ResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver(resourceLoader);
        //support ant style path pattern
        Resource[] resources = patternResolver.getResources("**/*.class");
        Assertions.assertTrue(resources.length > 1);
    }

}