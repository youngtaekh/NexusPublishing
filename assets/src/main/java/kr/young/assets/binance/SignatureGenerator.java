package kr.young.assets.binance;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import java.nio.charset.StandardCharsets;

import static kr.young.assets.Constants.BINANCE_SECRET_KEY;

public class SignatureGenerator {
    private static final String HMAC_SHA256 = "HmacSHA256";

    private SignatureGenerator() {
    }

    public static final String getSignature(String data) {
        byte[] hmacSha256;
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(BINANCE_SECRET_KEY.getBytes(), HMAC_SHA256);
            Mac mac = Mac.getInstance(HMAC_SHA256);
            mac.init(secretKeySpec);
            hmacSha256 = mac.doFinal(data.getBytes());
        } catch (Exception e) {
            throw new RuntimeException("Failed to calculate hmac-sha256", e);
        }
        return encodeHexString(hmacSha256);
    }

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

    private static final char[] DIGITS_LOWER;
    private static final char[] DIGITS_UPPER;

    public static String encodeHexString(byte[] data) {
        return new String(encodeHex(data));
    }

    public static char[] encodeHex(byte[] data) {
        return encodeHex(data, true);
    }

    public static char[] encodeHex(byte[] data, boolean toLowerCase) {
        return encodeHex(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
    }

    protected static char[] encodeHex(byte[] data, char[] toDigits) {
        int l = data.length;
        char[] out = new char[l << 1];
        encodeHex(data, 0, data.length, toDigits, out, 0);
        return out;
    }

    private static void encodeHex(byte[] data, int dataOffset, int dataLen, char[] toDigits, char[] out, int outOffset) {
        int i = dataOffset;

        for (int var7 = outOffset; i < dataOffset + dataLen; ++i) {
            out[var7++] = toDigits[(240 & data[i]) >>> 4];
            out[var7++] = toDigits[15 & data[i]];
        }
    }

    static {
//        DEFAULT_CHARSET = StandardCharsets.UTF_8;
        DIGITS_LOWER = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        DIGITS_UPPER = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    }
}
