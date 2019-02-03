package nioDemos.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 实现键盘输入数据的处理操作
 */
public class InputUtil {
    private static final BufferedReader KEY_BOARD_INPUT = new BufferedReader(new InputStreamReader(System.in));

    private InputUtil() {
    } //内部将直接提供静态方法

    /**
     * 通过键盘输入数据并返回字符串内容
     *
     * @param promt
     * @return
     */
    public static String getString(String promt) {
        String returnData = null;
        boolean flag = true;
        while (flag) {
            System.out.println(promt);
            try {
                returnData = KEY_BOARD_INPUT.readLine();
                if (returnData == null || "".equals(returnData)) {
                    System.out.println("输入的数据不许为空");
                } else {
                    //结束循环
                    flag = false;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        return returnData;
    }

}
