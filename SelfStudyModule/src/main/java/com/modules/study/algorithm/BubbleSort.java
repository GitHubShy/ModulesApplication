package com.modules.study.algorithm;

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
