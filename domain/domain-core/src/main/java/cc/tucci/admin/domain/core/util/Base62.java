package cc.tucci.admin.domain.core.util;

/**
 * @author tucci
 */
public class Base62 {

    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static String decimalToBase64(long decimal) {
        StringBuilder result = new StringBuilder();
        while (decimal > 0) {
            int remainder = (int) (decimal % ALPHABET.length());
            result.append(ALPHABET.charAt(remainder));
            decimal /= ALPHABET.length();
        }

        // 确保负数正确处理，并翻转字符串以获得正常顺序
        if (decimal < 0) {
            // 对于负数，这里假设我们使用了某种符号位表示，实际应用中可能不需要或有不同方式处理
            // 在此简单处理下，只对正数做转换，负数直接返回错误提示或空字符串
            throw new IllegalArgumentException("Negative numbers are not supported in this implementation.");
        } else {
            return result.reverse().toString();
        }
    }

    // 实现64进制转10进制
    public static long base64ToDecimal(String base64Str) {
        long decimal = 0;
        int power = 0;
        for (int i = base64Str.length() - 1; i >= 0; i--) {
            char digitChar = base64Str.charAt(i);
            int digitValue = ALPHABET.indexOf(digitChar);
            decimal += digitValue * Math.pow(ALPHABET.length(), power++);
        }
        return decimal;
    }

}
