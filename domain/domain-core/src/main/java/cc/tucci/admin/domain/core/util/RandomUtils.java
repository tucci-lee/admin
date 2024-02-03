package cc.tucci.admin.domain.core.util;

import java.util.Random;
import java.util.UUID;

/**
 * @author tucci
 */
public class RandomUtils {

    protected static final String CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    protected static final String NUMBERS = "0123456789";

    public static String randomString(int length) {
        return randomString(length, CHARS);
    }

    public static String randomNumber(int length) {
        return randomString(length, NUMBERS);
    }

    public static String randomString(int length, String chars) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
