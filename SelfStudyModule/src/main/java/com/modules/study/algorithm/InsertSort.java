package com.modules.study.algorithm;

/**
 * 就像打扑克一样，一开始左手上就一张牌，然后不停的抽牌与手上的牌依次做对比，如果小于当前牌就交换
 *
 * 所以内循环起始index为 1到（length-1）
 */
public class InsertSort {

    public static int[] data = new int[]{9, 9, 4, 7, 5, 3, 2, 1, 2, 8, 1};

    public static void main(String[] args) {

        int length = data.length;
        for (int i = 1; i < length ; i++) {
            for (int j = i ; j > 0 ; j--) {
                if (data[j -1] > data[j]) {
                    int temp = data[j];
                    data[j] = data[j-1];
                    data[j -1] = temp;
                }
            }
        }

        for (int i = 0; i < length ; i++) {
            System.out.print(data[i]);
        }

    }
}
