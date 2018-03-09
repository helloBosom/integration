package fun.peri.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chenq on 2017/9/25.
 */
public class StringUtil {

    public static String convert2LikeMatched(String templateName) {
        if (!StringUtils.isEmpty(templateName)) {
            return "%" + templateName + "%";
        }
        return null;
    }

    /**
     * 统计数字数
     *
     * @param str
     * @return
     */
    public static int countNumber(String str) {
        int count = 0;
        Pattern p = Pattern.compile("\\d");
        Matcher m = p.matcher(str);
        while (m.find()) {
            count++;
        }
        return count;
    }

    /**
     * 统计字母数
     *
     * @param str
     * @return
     */
    public static int countLetter(String str) {
        int count = 0;
        Pattern p = Pattern.compile("[a-zA-Z]");
        Matcher m = p.matcher(str);
        while (m.find()) {
            count++;
        }
        return count;
    }

    /**
     * 统计汉字数
     *
     * @param str
     * @return
     */
    public static int countChinese(String str) {
        int count = 0;
        Pattern p = Pattern.compile("[\\u4e00-\\u9fa5]");
        Matcher m = p.matcher(str);
        while (m.find()) {
            count++;
        }
        return count;
    }

    /**
     * 统计空格数
     *
     * @param str
     * @return
     */
    public static int countSpace(String str) {
        int count = 0;
        Pattern p = Pattern.compile("\\s");
        Matcher m = p.matcher(str);
        while (m.find()) {
            count++;
        }
        return count;
    }

    /**
     * 是否为数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        return str.matches("-?[0-9]+.*[0-9]*");
    }

    public static void main(String[] args) {
        System.out.printf(isNumeric("-1.01") + "");
    }

}
