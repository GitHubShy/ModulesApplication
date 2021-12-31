package com.modules.study.algorithm;

/**
 * 给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中最后一个单词的长度。
 * <p>
 * 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。
 */
public class LastWord {


    public static void main(String[] args) {

        int length = lengthOfLastWord("Hello World");
        System.out.println(length);
    }

    public static int lengthOfLastWord(String s) {
        char val = " ".charAt(0);
        char[] letters = s.toCharArray();
        int length = 0;
        for (int i = letters.length - 1; i >= 0; i--) {
            if (letters[i] == val) {
                if (length != 0) {
                    return length;
                }
                continue;
            } else {
                length++;
            }
        }
        return length;

    }

}
