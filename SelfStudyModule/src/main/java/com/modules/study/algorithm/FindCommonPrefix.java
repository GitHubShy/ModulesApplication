package com.modules.study.algorithm;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 */
public class FindCommonPrefix {

    public static String[] strs = new String[]{"flower", "flow", "flight"};

    public static void main(String[] args) {
        String result = longestCommonPrefix(strs);
        System.out.println(result);
    }

    public static String longestCommonPrefix(String[] strs) {

        if (strs != null && strs.length > 1) {
            String longestStr = "";
            longestStr = strs[0];

            for (int j = longestStr.length(); j > 0; j--) {
                longestStr = longestStr.substring(0, j);
                boolean isCommonPrefix = true;
                for (int i = 1; i < strs.length; i++) {
                    if (!strs[i].startsWith(longestStr)) {
                        isCommonPrefix = false;
                        break;
                    }
                }
                if (isCommonPrefix) {
                    return longestStr;
                }
            }

        } else if (strs.length == 1) {
            return strs[0];
        }
        return "";

    }
}
