package com.zjjhyzd.springboot.commons.utils;

import java.util.UUID;

/**
 * 唯一标识符发生器(20位)
 *
 * @author Fan Peng
 */
public class IdUtil {

    /* No '1', '0', 'I', 'O' */
    public static final char[] x32s = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ".toCharArray();

    /* 36位随机种子（10位数字+26位字母） */
    public static final char[] x36s = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    /* 10位随机种子 */
    public static final char[] x10s = "0123456789".toCharArray();

    /**
     * 根据前缀生成随机数
     * @param prefix 前缀
     */
    public static String newId(String prefix) {
        return prefix + newId();
    }

    /**
     * 根据前缀生成指定长度的随机数
     * @param prefix 前缀
     * @param length 长度，不包含前缀长度
     */
    public static String newId(String prefix, int length) {
        return prefix + newId(length);
    }


    public static void main(String[] args) {
        System.out.println(newLongId(13));

    }


    /**
     * 生成指定长度（1<=length<=8）随机字符(‘第1位’必须为‘字母’)
     */
    public static String newId(int length) {

        char[] chs = new char[length];

        long v = System.currentTimeMillis() >> 1;
        for (int i = length - 1; i >= 0; i--) {
            chs[i] = x36s[(int) (v % 36)];
            v = v / 36;
        }
        chs[0] = x36s[(int) (v % 26) + 10]; // 确保第一个随机字符是"字母"

        return new String(chs);
    }

    /**
     * 生成包含0-9数字、A-Z字母的20位随机数
     * ‘前8位’随机字符以系统时间为随机池, ‘第1位’必须为‘字母’
     * ‘后12位’随机字符以UUID为随机池
     */
    public static String newId() {

        int len = 20;

        char[] chs = new char[len];

        // 生成‘前8位’随机字符
        long v = System.currentTimeMillis() >> 1;
        for (int i = 7; i > 0; i--) {
            chs[i] = x36s[(int) (v % 36)];
            v = v / 36;
        } /* for */
        chs[0] = x36s[(int) (v % 26) + 10]; // 确保第一个随机字符是"字母"

        // 生成‘后12位’随机字符
        UUID u = UUID.randomUUID();
        v = u.getLeastSignificantBits() ^ u.getMostSignificantBits();
        if (v < 0) {
            v = -v;
        }

        for (int i = 8; i < len; i++) {
            chs[i] = x36s[(int) (v % 36)];
            v = v / 36;
        }

        return new String(chs);
    }

    /**
     * 生成仅包含0-9数字的10位随机数
     */
    public static long newLongId(Integer shortLength) {

        /* 随机数长度 */

        /* 结果随机数{保存在字符串中} */
        char[] chs = new char[shortLength];

        // 生成前3位随机字符(以系统时间为随机池, 以10位数字为随机种子)
        long v = (System.currentTimeMillis() - 936748800000L) >> 1; // 1999-9-9
        // 要横跨102年
        for (int i = 2; i >= 0; i--) {
            chs[i] = x10s[(int) (v % 10)];
            v = v / 10;
        }

        // 生成后7位随机字符(以UUID为随机池, 以10位数字为随机种子)
        UUID u = UUID.randomUUID();
        v = u.getLeastSignificantBits() ^ u.getMostSignificantBits();
        if (v < 0) {
            v = -v;
        }
        for (int i = 3; i < shortLength; i++) {
            chs[i] = x10s[(int) (v % 10)];
            v = v / 10;
        }

        return Long.parseLong(new String(chs));
    }

}