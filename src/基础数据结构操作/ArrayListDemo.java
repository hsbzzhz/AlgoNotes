package 基础数据结构操作;

import java.util.*;
import java.util.stream.Collectors;

public class ArrayListDemo {

    public static void transfer(){
        // arraylist初始化，这里用一个String 数组来初始化， 上下等同
        List<String> list= new ArrayList<>(Arrays.asList(new String[]{"a0034","z8834","h440"}));
        List<String> list1= new ArrayList<>(Arrays.asList("a0034","z8834","h440"));

        // list 转为 string[]，直接转可能会报错，这样比较保险
        list.toArray(new String[0]);  // 简化这样写，但为啥不太清楚（这里要是包装类）

        // list 转 string，用循环
        String res = "";
        for(String str: list1) {
            res += str;
        }
        // list 转 string，使用String join
        String res1 = String.join("", list1);
        System.out.println(res);


        // list 去重方法
        // 方法一：用 hashset
        Set<String> hashSet = new LinkedHashSet<>(list1); // 删除了重复数据，保持了list中的顺序
        List<String> nonDuplicates = new ArrayList<>(hashSet); // 再把类型转换回来
        // 方法二： 用stream
        List<String> nonDuplicates1 = list.stream().distinct().collect(Collectors.toList());
    }

    public static void arraySort(){
        // sort 方法原地排序
        // ！！！数组排序中，不能对基本类型的数组进行自定义排序，如int，byte，char
        // 1. 一维数组排序
        Integer[] numArray1 = new Integer[]{3,4,5,3,6,8,12};
        Arrays.sort(numArray1);// 默认排序方法
        Arrays.sort(numArray1, new Comparator<Integer>() {  // 自定义排序方法，匿名函数实现内部类
            @Override
            public int compare(Integer e1, Integer e2) {
                // 升序，e1防在前面，这种写法适用于字符串排序
                return e1.compareTo(e2);
            }
        });
        // 2. 二维数组排序
        int[][] numArray2 = new int[3][4];
        Arrays.sort(numArray2, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // return o1[0] - o1[0]; // 按照二维数组的0位升序排列（有溢出风险）
                return Integer.compare(o1[0], o2[0]);  // 推荐这么写
            }
        });
    }


    public static void listSort(){
        // arraylist就用到 Collections ，而不是 array
        List<Integer> list = new ArrayList<>();
        Collections.sort(list); // 默认，升序吧？
        Collections.sort(list, new Comparator<Integer>() {  // 自定义排序方法，匿名类实现
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;  // 降序
            }
        });
    }

    public static void subArray(){
        // 1. 数组切片
        int[] test_int = {1,2,3,4,5};
        int[] arraySub = Arrays.copyOfRange(test_int,1,4);  // 左开右闭区间 2,3,4

        // 2. String切片
        String test = "12345";
        test = test.substring(1,4);

        // 3. list数组
        List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5));
        List<Integer> list1 = list.subList(1,4);
        System.out.println(list1);
    }

    public static void main(String[] args) {
    }
}
