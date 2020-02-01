package me.superning.luntan.security;

/**
 *
 * @author superning
 */
public class AccessContext {
    private static final ThreadLocal<String> token =  new ThreadLocal<String>();

    public static String getToken() {
       return token.get();
    }
    public static void setToken(String tokenStr) {
        token.set(tokenStr);
    }
    public static void clearTokenKey() {
        token.remove();
    }
}
