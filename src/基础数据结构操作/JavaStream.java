package 基础数据结构操作;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaStream {
    public static void LambdaDemo(){
        // 用lambda来排序 字符串数组
        ArrayList<String> list = new ArrayList<>();
        list.add("hello");
        list.add("bit");
        list.add("jj");
        list.add("laoliu");

        // 按照字符串长度来排序，从短到长
        list.sort((String o1, String o2) -> {
            return o1.length() - o2.length();
        });
        // 最后输出打印
        list.forEach(s -> System.out.println(s));
        // method reference 方式，更加简洁
        // list.forEach(System.out::println);
    }
    public static void StreamDemo(){
        List<String> strings = Arrays.asList("abc","def","efg"); // 创建list的方法，asList
        //返回符合条件的stream
        Stream<String> stringStream = strings.stream().filter(s -> "abc".equals(s));
        //计算流符合条件的流的数量
        long count = stringStream.count();
        //forEach遍历->打印元素
        strings.forEach(System.out::println);

        //limit 获取到1个元素的stream
        Stream<String> limit = strings.stream().limit(1); // "abc" 从前开始保留
        //toArray 比如我们想看这个limitStream里面是什么，比如转换成String[],比如循环
        String[] array = limit.toArray(String[]::new);  // 把stream转换回 array

        //map 对每个元素进行操作返回新流
        Stream<String> map = strings.stream().map(s -> s + "22");
        map.forEach(System.out::println);

        //sorted 排序并打印
        strings.stream().sorted().forEach(System.out::println);

        //Collectors collect 把abc放入容器中
        // 如果等于"abc" 就放入容器中
        List<String> collect = strings.stream().filter(string -> "abc".equals(string)).collect(Collectors.toList());
        //把list转为string，各元素用，号隔开
        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(","));

        //对数组的统计，比如用
        List<Integer> number = Arrays.asList(1, 2, 5, 4);

        IntSummaryStatistics statistics = number.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("列表中最大的数 : "+statistics.getMax());
        System.out.println("列表中最小的数 : "+statistics.getMin());
        System.out.println("平均数 : "+statistics.getAverage());
        System.out.println("所有数之和 : "+statistics.getSum());

        //concat 合并流
        List<String> strings2 = Arrays.asList("xyz", "jqx");
        Stream.concat(strings2.stream(),strings.stream()).count();

        //一个流只能使用一次，但是可以这样, 连续使用
        map.limit(2).forEach(System.out::println);

        // 形如{{"abc","acd", "ccd"}, {"acc"}, {"qqd", "errt"}}
        // 这种嵌套数组，如果想把它整合为一个数组，可以用flatMap
        // list.stream().flatMap(Collection::stream)

        // 多个条件排序，这个从底至上的条件排
        // List<Department> res = map.values().stream().flatMap(Collection::stream)
        //     .sorted(Comparator.comparing(Department::getDepartmentName))
        //     .sorted(Comparator.comparing(Department::getCity))
        //     .collect(Collectors.toList());
       
    }
    public static void main(String[] args) {
        StreamDemo();
    }
}
