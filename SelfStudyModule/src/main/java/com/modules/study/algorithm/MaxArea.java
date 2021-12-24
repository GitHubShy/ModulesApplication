package com.modules.study.algorithm;

/**
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * <p>
 * 说明：你不能倾斜容器。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/container-with-most-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxArea {

    public static final int[] height = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};


    public static int maxArea(int[] height) {
        int result = 0;

//        for (int i = 0; i < height.length - 1; i++) {
//            for (int j = i + 1; j < height.length; j++) {
//                int rect_width = j - i;
//                int rect_height = height[i] < height[j] ? height[i]:height[j];
//                result = rect_height * rect_width > result ? rect_height * rect_width : result;
//            }
//        }
        int left_point = 0;
        int right_point = height[height.length - 1];
        while (left_point < right_point) {
            int new_result = (right_point - left_point) * Math.min(height[left_point], height[right_point]);
            result = new_result > result ? new_result : result;
            if (height[left_point] <= height[right_point]) {
                left_point++;
            } else {
                right_point--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int result = maxArea(height);
        System.out.println(result);
    }
}
