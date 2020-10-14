package io.github.sruby.os;

import org.junit.Test;

import java.io.IOException;

/**
 * @date 2020/10/13 15:36
 */
public class JavaExecuteShellTest {

    @Test
    public void execute() throws IOException, InterruptedException {
        JavaExecuteShell.execute();
    }

    @Test
    public void executeCmd() throws Exception {
        JavaExecuteShell.executeCmd();
    }

    @Test
    public void executeCmdNoThread() throws Exception {
        JavaExecuteShell.executeCmdNoThread();
    }
}