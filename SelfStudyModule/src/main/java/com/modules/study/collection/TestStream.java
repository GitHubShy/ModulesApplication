package com.modules.study.collection;



import android.annotation.SuppressLint;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class TestStream {

    @SuppressLint("NewApi")
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7);
         List<Integer> result = list.stream().filter(number -> number % 2 == 0).collect(Collectors.toList());
        System.out.print(result);

    }
}
