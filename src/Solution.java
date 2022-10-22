import java.util.*;
import java.util.stream.Collectors;

public class Solution {
//    C 1330000
//    W 1314000
//    C 1314000
//    C 0371280
//    C 0371290
//    W 03712*
//    C 0371280
//
//    ======= 按照电话出现顺序排序
//    1330000 0 1 接通｜拒接
//    1314000 1 0
//    0371280 1 1
//    0371280 0 1
    public static String[] call(String[] incall){
        Set<String> whitelist = new HashSet<>();
        Map<String, int[]> res = new LinkedHashMap<>(); // 需要有序
        for (String str : incall) {
            if (str.startsWith("W")) { // 更新白名单情况
                whitelist.add(str.substring(2));
            } else { // 打电话情况
                String raw = str.substring(2);
                if (isCallable(raw, whitelist)) { // 可以打通的情况
                    // 在map里有或没有
                    if (res.containsKey(raw)) {
                        int[] innerArray = res.get(raw);
                        innerArray[1] += 1;
                        res.put(raw, innerArray);
                    } else {
                        res.put(raw,new int[]{0,1});
                    }
                } else {  // 不能打通的情况
                    // 在map里有或没有
                    if (res.containsKey(raw)) {
                        int[] innerArray = res.get(raw);
                        innerArray[0] += 1;
                        res.put(raw, innerArray);
                    } else {
                        res.put(raw,new int[]{1,0});
                    }
                }
            }
        }
        List<String> list = new ArrayList<>();
        for(String key: res.keySet()){
            int[] val = res.get(key);
            list.add(key + " "+ val[0]+ " "+ val[1]);
        }

        return list.toArray(new String[0]); // toArray() 里面要填返回类型
    }

    private static boolean isCallable(String raw, Set<String> whitelist) {
        if (whitelist.contains(raw)) {
            return true;
        } else if (raw.startsWith("0371")) {
            return whitelist.stream().filter(str -> str.endsWith("*")).anyMatch(str -> raw.startsWith(str.substring(0,str.length()-1)));
        }
        return false;

    }

    public static void main(String[] args) {
        int[] nums = {-1,1,-3,5};
        int[] nums1 = {-22, 1, -3, -40, 47, 4};
        String[] source = {"C 1330000", "W 1314000", "C 1314000", "C 0371280", "C 0371290", "W 03712*", "C 0371280"};
        String[] res = call(source);
        for (String each: res) {
            System.out.println(each);
        }
    }
}
