package fun.peri.arithmetic;

import org.junit.Test;

public class Tests {

    @Test
    public void test1() {
        double x = Math.pow(82.7 / 25.0, 1.0 / 10.0);
        System.out.println("08年gdp25.0，18年82.7，基于此数据gdp十年平均增长率：");
        System.out.println(x - 1.0);
        System.out.println();
    }

    @Test
    public void test2() {
        double x = 82.7;
        double y = 6.5 / 100.0;
        System.out.println("18年82.7，国务院给出增速6.5%。假设去杠杆，防金融风险成立，基于此数据后五年历年gdp：");
        for (int i = 0; i < 5; i++) {
            x = x * (y + 1.0);
            System.out.println(2019 + i + ":" + x);
        }
        System.out.println();
    }

    @Test
    public void test3() {
        System.out.println("基于计算出的2023年gdp，08年到23年实际历年增长率：");
        double x = Math.pow(113.3 / 25.0, 1 / 15.0);
        System.out.println(x - 1.0);
        System.out.println();
    }

    @Test
    public void test4() {
        System.out.println("基于实际增长率，08年到23年实际历年gdp：");
        double x = 25.0;
        for (int i = 0; i < 15; i++) {
            x = x * 1.1059937176918395;
            System.out.println(2009 + i + ":" + x);
        }
        System.out.println();
    }

    @Test
    public void test5() {
        double x = 82.7;
        double y = x / 0.65;
        for (int i = 0; ; i++) {
            y = y * (1.0 + 3 / 100.0);
            x = x * 1.065;
            System.out.println(2019 + i + "usa gdp:" + y);
            System.out.println(2019 + i + "china gdp:" + x);
            if (y < x) {
                System.out.println("over");
                break;
            }
        }
        System.out.println();
    }

}
