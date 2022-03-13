package io.github.sruby.jvm;

import java.lang.reflect.Field;
import java.util.Arrays;

public class ClearSensitiveInformation {
    void clearChar() {
        char[] password = getPassword();
        verifyPassword(password);
        // 清除password
        Arrays.fill(password, (char) 0x00);
    }

    private char[] getPassword() {
        return new char[10];
    }

    boolean verifyPassword(char[] pwd) {
        return true;
    }

    void clearString() {
        String user = "zhangsan";
        String password = "1234234";
        verifyLoginInfo(user, password);
        // 清除password
        try {
            Field valueFieldOfString = String.class.getDeclaredField("value");
            valueFieldOfString.setAccessible(true);
            char[] value = (char[]) valueFieldOfString.get(password);
            Arrays.fill(value, (char) 0x00);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void verifyLoginInfo(String user, String password) {

    }

}
