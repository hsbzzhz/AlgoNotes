package 可信真题;

import java.util.*;

public class 身高体重排序 {
    // 数组做法
    private static int[] hei = {90, 110, 90, 100, 90};
    private static int[] wei = {45, 50,  35, 70, 45};

    public static void usingArray() {

        int n = hei.length;
        // 需要建立一个二维数组 {{1, 90, 45}, {2, 110, 90, 35}, {} ...}
        int[][] source = new int[n][3];
        for (int i = 0; i < n; i++) {
            source[i][0] = i+1;  // index
            source[i][1] = hei[i];  // height
            source[i][2] = wei[i];  // weight
        }

        // 把数组，排序，二维数组排序；数组原地排序
        Arrays.sort(source, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] == o2[1]) { // 身高相同的情况
                    return o1[2] - o2[2];  //小的在前面
                } else {
                    return o1[1] - o2[1];
                }
            }
        });
        for(int[] each: source) {
            System.out.print(each[0]+ " ");
        }
    }

    public static void usingMap() {
        /*
         * 这里不好用LinkedHashmap，因为重写 comparator 也是对key进行排序
         *  我们需要对value进行排序，就要先把它转成 arraylist，再重写comparator
         */
        Map<Integer, int[]> map = new HashMap<>();
        for(int i =0; i < hei.length; i++) {
            map.put(i+1, new int[]{hei[i], wei[i]}); // {index: {90, 45}}
        }
        // 1. 将map 转换成list
        List<Map.Entry<Integer, int[]>> list = new ArrayList<>(map.entrySet());
        // 借助 collections 来排序
        Collections.sort(list, new Comparator<Map.Entry<Integer, int[]>>() {   // comparator里是arraylist的类型
            @Override
            public int compare(Map.Entry<Integer, int[]> o1, Map.Entry<Integer, int[]> o2) {
                // 身高差
                int sub = o1.getValue()[0] - o2.getValue()[0];
                if( sub==0 ) {
                    return o1.getValue()[1] - o2.getValue()[1];
                }
                return sub;
            }
        });

        for(Map.Entry<Integer, int[]> each: list)  {
            System.out.print(each.getKey());
        }
    }
    public static void main(String[] args) {
        usingMap();
    }
}
