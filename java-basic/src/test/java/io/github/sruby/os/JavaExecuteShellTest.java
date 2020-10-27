package io.github.sruby.os;

import org.junit.Test;

import java.io.IOException;

/**
 * @date 2020/10/13 15:36
 */
public class JavaExecuteShellTest {
    private static String pomPath = "C:\\Users\\1\\Documents\\javaStudy\\demo\\pom.xml";

    @Test
    public void execute() throws IOException, InterruptedException {
        JavaExecuteShell.execute();
    }

    @Test
    public void executeCmd() throws Exception {
        String[] commands = {"mvn.cmd", "package", "-f", pomPath, "-Dmaven.test.skip=true"};
        JavaExecuteShell.executeCmd(commands);
    }

    @Test
    public void executeCmdNoThread() throws Exception {
        String[] commands = {"mvn.cmd", "package", "-f", pomPath, "-Dmaven.test.skip=true"};
        JavaExecuteShell.executeCmdNoThread(commands);
    }

    @Test
    public void executeCmd_mavenTest() throws Exception {
        String[] commands = {"mvn.cmd", "package", "-f", pomPath};
        JavaExecuteShell.executeCmd(commands);
    }
}