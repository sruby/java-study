package io.github.sruby.os;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * https://www.baeldung.com/run-shell-command-in-java
 * @date 2020/10/13 15:34
 */
@Slf4j
public class JavaExecuteShell {

    /**
     * execute cmd
     * @param commands
     */
    public static void executeCmd(String[] commands) throws Exception {
        ProcessBuilder builder = new ProcessBuilder(commands);
        Process process = builder.start();

        StreamGobbler streamGobbler =
                new StreamGobbler(process.getInputStream());
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(6, 10, 0,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(4), new ThreadFactoryBuilder().setNameFormat("log-%d").build(),new ThreadPoolExecutor.AbortPolicy());
        threadPoolExecutor.submit(streamGobbler);

        int exitCode = process.waitFor();
        if (0!=exitCode){
            throw new Exception("mvn build failed!");
        }
    }

    /**
     * execute cmd with no thread
     */
    public static void executeCmdNoThread(String[] commands) throws Exception {
        ProcessBuilder builder = new ProcessBuilder(commands);
        Process process = builder.start();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String content;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            while ( StringUtils.isNotBlank((content = bufferedReader.readLine()))){
                log.info(content);
                stringBuffer.append(content);
            }
        }catch (Exception e){
            log.error("",e);
        }

        int exitCode = process.waitFor();
        if (0!=exitCode){
            throw new Exception("process execute failed!");
        }

        if (!stringBuffer.toString().contains("BUILD SUCCESS")){
            throw new Exception("mvn build failed!");
        }
    }


    public static void execute() throws InterruptedException, IOException {
        boolean isWindows = System.getProperty("os.name")
                .toLowerCase().startsWith("windows");
        ProcessBuilder builder = new ProcessBuilder();
        if (isWindows) {
            builder.command("cmd","mvn", "clean", "install");
//            builder.command("java", "-version");
        } else {
            builder.command("sh", "-c", "ls");
        }
        builder.directory(new File("C:\\Users\\1\\Documents\\javaStudy"));
        Process process = builder.start();
        StreamGobbler streamGobbler =
                new StreamGobbler(process.getInputStream());
        Executors.newSingleThreadExecutor().submit(streamGobbler);
        int exitCode = process.waitFor();
        assert exitCode == 0;

    }

    public void exec() throws InterruptedException, IOException {
        boolean isWindows = System.getProperty("os.name")
                .toLowerCase().startsWith("windows");

        String homeDirectory = System.getProperty("user.home");
        Process process;
        if (isWindows) {
            process = Runtime.getRuntime()
                    .exec(String.format("cmd.exe /c dir %s", homeDirectory));
        } else {
            process = Runtime.getRuntime()
                    .exec(String.format("sh -c ls %s", homeDirectory));
        }
        StreamGobbler streamGobbler =
                new StreamGobbler(process.getInputStream());
        Executors.newSingleThreadExecutor().submit(streamGobbler);
        int exitCode = process.waitFor();
        assert exitCode == 0;
    }

//    private static class StreamGobbler implements Runnable {
//        private InputStream inputStream;
//        private Consumer<String> consumer;
//
//        public StreamGobbler(InputStream inputStream, Consumer<String> consumer) {
//            this.inputStream = inputStream;
//            this.consumer = consumer;
//        }
//
//        @Override
//        public void run() {
//            new BufferedReader(new InputStreamReader(inputStream)).lines()
//                    .forEach(consumer);
//        }
//    }
    private static class StreamGobbler implements Runnable {
        private InputStream inputStream;

        public StreamGobbler(InputStream inputStream) {
            this.inputStream = inputStream;
        }

        @Override
        public void run() {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String content;
            try {
                while ( StringUtils.isNotBlank((content = bufferedReader.readLine()))){
                    log.info(content);
                }
            }catch (Exception e){
                log.error("",e);
            }

        }
    }



}
