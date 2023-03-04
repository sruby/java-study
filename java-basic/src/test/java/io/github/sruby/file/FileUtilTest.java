package io.github.sruby.file;

import cn.hutool.core.io.FileUtil;
import cn.hutool.crypto.SecureUtil;
import jodd.util.Base64;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

/**
 * read file
 *
 * @author Sruby
 * @date 14/11/2022 10:12
 */
public class FileUtilTest {
    @Test
    public void readFileAndDecrypt(){
        byte[] bytes = FileUtil.readBytes("/Users/macuser/Documents/security");

//        ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
//        Inflater decompresser = new Inflater(true);
//        InflaterOutputStream inflaterOutputStream = new InflaterOutputStream(stream2, decompresser);
//        inflaterOutputStream.write(output);
//        inflaterOutputStream.close();
//        byte[] output2 = stream2.toByteArray();

        byte[] decode = Base64.decode(bytes);
        byte[] decrypt = SecureUtil.aes("6666661234567890".getBytes(StandardCharsets.UTF_8)).decrypt(decode);
        FileUtil.writeBytes(decrypt,"/Users/macuser/Documents/security.docx");
    }
}
