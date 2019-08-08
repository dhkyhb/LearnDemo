package dhkyhb.gson;

import sun.rmi.runtime.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Create by hl
 * Data by 2019-06-26
 * Description:
 */
public class utils {

    public static String jsonToStr(InputStream inputStream) throws IOException {
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        // 数组长度
        byte[] buffer = new byte[1024];
        // 初始长度
        int len = 0;
        // 循环
        while ((len = inputStream.read(buffer)) != -1) {
            arrayOutputStream.write(buffer, 0, len);
//            return arrayOutputStream.toString();
        }
//        System.out.println(arrayOutputStream.toString());
        return arrayOutputStream.toString();
    }

}
