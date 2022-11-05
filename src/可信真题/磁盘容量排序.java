package 可信真题;

import java.util.*;

public class 磁盘容量排序 {

    public static void usingList(String[] input) {
        List<String[]> list = new ArrayList<>();
        for (String str: input) {
            String transfed = String.valueOf(transfer2M(str));
            list.add(new String[]{str, transfed});
        }

        Collections.sort(list, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                return Integer.parseInt(o1[1]) - Integer.parseInt(o2[1]);
            }
        });

        for(int i =0; i < list.size(); i++) {
            System.out.println(list.get(i)[0]);
        }
    }
    public static int transfer2M(String str) {
        int sum = 0, index = 0;

        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if(!Character.isDigit(ch)) {
                if (ch == 'G') {
                    sum = sum + Integer.parseInt(str.substring(index, i)) * 1024;
                } else if (ch == 'T'){
                    sum = sum + Integer.parseInt(str.substring(index, i)) * 1024 *1024;
                } else if (ch == 'M'){
                    sum = sum + Integer.parseInt(str.substring(index, i));
                }
                index = i + 1;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        String[] str = {"2G4M", "3M2G", "1T", "1024G"};
        usingList(str);
    }
}
