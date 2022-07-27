import java.util.HashMap;
import java.util.Map;
/*
 * 例子1： XXVII（因为左边比右边大，所有是相加的逻辑） X+X+V+I+I = 10 + 10+ 5 + 1 + 1=27
 * 例子2： XIV （因为左边的比右边小，所以要减） X - I + V = 10 - 1 +5 =14
 */
public class 罗马数字转整数 {
    Map<Character, Integer> symbolValues = new HashMap<Character, Integer>(){{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
    }};

    public int romanToInt(String s){
        int ans = 0;
        int n = s.length();
        for(int i=0; i<n; i++){
            int value = symbolValues.get(s.charAt(i));
            if(i<n-1 && value < symbolValues.get(s.charAt(i+1))){
                ans -= value;
            } else {
                ans += value;
            }
        }
        return ans;
    }
}
