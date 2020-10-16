package io.github.sruby.os;

import java.io.IOException;

public class CMDUtils {
    public static void execCMD(){
        Runtime runtime=Runtime.getRuntime();
        try {
            runtime.exec("cmd /k cd C:\\Users\\1\\Documents\\javaStudy && mvn package");
//            runtime.exec("cmd mvn package");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        execCMD();
    }
}