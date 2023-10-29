package com.jd.phc;

import java.io.ByteArrayOutputStream;

@SuppressWarnings("all")
public class d {

    private static char[] f12667a = {'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};

    private static byte[] f12668b = new byte[128];

    public d() {
        try {
            c();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static byte[] a(String str) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bytes = str.getBytes();
        int length = bytes.length;
        byte[] bArr = new byte[length];
        for (int i2 = 0; i2 <= bytes.length - 1; i2++) {
            bArr[i2] = f12668b[bytes[i2]];
        }
        int i3 = 0;
        while (true) {
            int i4 = length - 1;
            if (i3 > i4) {
                return byteArrayOutputStream.toByteArray();
            }
            byte[] bArr2 = new byte[3];
            int i5 = 0;
            for (int i6 = 0; i6 <= 2; i6++) {
                int i7 = i3 + i6;
                int i8 = i7 + 1;
                if (i8 <= i4 && bArr[i8] >= 0) {
                    bArr2[i6] = (byte) ((((bArr[i7] & 255) << ((i6 * 2) + 2)) & 255) | ((byte) ((bArr[i8] & 255) >>> (((2 - (i6 + 1)) * 2) + 2))));
                    i5++;
                }
            }
            for (int i9 = 0; i9 <= i5 - 1; i9++) {
                byteArrayOutputStream.write(bArr2[i9]);
            }
            i3 += 4;
        }
    }

    public static String b(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 <= bArr.length - 1; i2 += 3) {
            byte[] bArr2 = new byte[4];
            byte b2 = 0;
            for (int i3 = 0; i3 <= 2; i3++) {
                int i4 = i2 + i3;
                if (i4 <= bArr.length - 1) {
                    bArr2[i3] = (byte) (b2 | ((bArr[i4] & 255) >>> ((i3 * 2) + 2)));
                    b2 = (byte) ((((bArr[i4] & 255) << (((2 - i3) * 2) + 2)) & 255) >>> 2);
                } else {
                    bArr2[i3] = b2;
                    b2 = 64;
                }
            }
            bArr2[3] = b2;
            for (int i5 = 0; i5 <= 3; i5++) {
                if (bArr2[i5] <= 63) {
                    sb.append(f12667a[bArr2[i5]]);
                } else {
                    sb.append('=');
                }
            }
        }
        return sb.toString();
    }

    public static void c() {
        int i2 = 0;
        int i3 = 0;
        while (true) {
            byte[] bArr = f12668b;
            if (i3 > bArr.length - 1) {
                break;
            }
            bArr[i3] = -1;
            i3++;
        }
        while (true) {
            char[] cArr = f12667a;
            if (i2 > cArr.length - 1) {
                return;
            }
            f12668b[cArr[i2]] = (byte) i2;
            i2++;
        }
    }
}