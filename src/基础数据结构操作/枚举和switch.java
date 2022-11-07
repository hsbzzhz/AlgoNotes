package 基础数据结构操作;

public class 枚举和switch {
    enum Weekday {
        MON(1, "星期一"), TUE(2, "星期二"), WED(3, "星期三"), THU(4, "星期四"), FRI(5, "星期五"), SAT(6, "星期六"), SUN(0, "星期日");

        public final int dayValue;  // 这里定义成员变量，每个枚举值后都跟着一个（），括号里填成员变量，用逗号分割
        private final String chinese;

        Weekday(int dayValue, String chinese) { // 还得用构造方法
            this.dayValue = dayValue;
            this.chinese = chinese;
        }

        // toString 方法可以写也可以不写
        @Override
        public String toString() {  // 这里重写了toString方法，所以，输出类的时候是这个
            return this.chinese;
        }
    }


    public static void main(String[] args) {
        Weekday day = Weekday.MON;
        System.out.println(day);  //枚举 toString 结果
        System.out.println(day.ordinal());  // 这个是枚举类型都顺序 Mon ： 0

        // switch：
        Weekday input = Weekday.SUN;
        switch(input) {
            case MON:
            case TUE:
            case WED:
            case THU:
            case FRI:
                System.out.println("Today is " + day + ". Work at office!");
                break; // swtich里记得每个case都要加 break
            case SAT:
            case SUN:
                System.out.println("Today is " + day + ". Work at home!");
                break;
            default:
                throw new RuntimeException("cannot process " + day);
        }
    }
}
