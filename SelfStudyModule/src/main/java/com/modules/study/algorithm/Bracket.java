package com.modules.study.algorithm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Bracket {

    public static String s = "{}";

    public static void main(String[] args) {
        boolean result = isValid(s);
        System.out.println(result);
    }

    public static Map<Character, Character> brackets = new HashMap<>();

    static {
        brackets.put('(', ')');
        brackets.put('{', '}');
        brackets.put('[', ']');
    }

    public static boolean isValid(String s) {
        if (s == null || s.length() == 0 || s.length() % 2 != 0) {
            return false;
        } else {
            char[] letters = s.toCharArray();
            if (!brackets.containsKey(letters[0])) {
                return false;
            } else {
                LinkedList<Character> list = new LinkedList<>();
                for (int i = 0 ; i < letters.length ; i++) {
                    char bracket = letters[i];
                    if (brackets.containsKey(bracket)) {
                        list.addLast(bracket);
                    } else if (brackets.get(list.removeLast()) != bracket) {
                        return false;
                    }
                }
                return list.size() == 0;
            }
        }
    }
}
