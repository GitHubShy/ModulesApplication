package com.modules.study.algorithm;

/**
 * 顾名思义，从第一开始，与后面一个比较，如果大，就交换位置，
 *
 * 所以每次内循环开始位置都为0，结束位置为 end <（length -1）倒数第二个
 */
public class BubbleSort {

    public static int[] data = new int[]{9, 9, 4, 7, 5, 3, 2, 1, 2, 8, 1};

    public static void main(String[] args) {

        int length = data.length;
        for (int j = length - 1; j >= 1; j--) {
            for (int i = 0; i < j; i++) {
                if (data[i + 1] < data[i]) {
                    int temp = data[i];
                    data[i] = data[i + 1];
                    data[i + 1] = temp;
                }
            }
        }

//        for (int j = 1; j <= length - 1; j++) {
//            for (int i = 0; i < j; i++) {
//                if (data[i + 1] < data[i]) {
//                    int temp = data[i];
//                    data[i] = data[i + 1];
//                    data[i + 1] = temp;
//                }
//            }
//        }

        for (int i = 0; i < length; i++) {
            System.out.println(data[i]);
        }


    }


}
