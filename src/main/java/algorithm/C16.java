package algorithm;

import java.util.Scanner;

/**
 * 假设1元、2元、5元、10元、20元、50元、100元的纸币分别有c0, c1, c2, c3, c4, c5, c6张。
 * 现在要用这些钱来支付K元，至少要用多少张纸币？
 * 贪心策略: 用贪心算法的思想，很显然，每一步尽可能用面值大的纸币即可.
 *
 */
public class C16 {
    public static int solve(int money, int[][] moneyCount)
    {
        int num = 0;
        //首先选择最大面值的纸币
        for (int i = moneyCount.length - 1; i >= 0; i--)
        {
            //需要的当前面值与面值数量取最小
            int c = Math.min(money / moneyCount[i][0], moneyCount[i][1]);
            money = money - c * moneyCount[i][0];
            num += c;
            System.out.println(c);
        }
        if (money > 0)
            num = -1;
        return num;
    }
    public static void main(String[] args) {
        //存放纸币与数量: first:纸币，second:数量
        int[][] moneyCount = { { 1, 3 }, { 2, 1 }, { 5, 4 }, { 10, 3 }, { 20, 0 }
                ,{50, 1}, { 100, 10 } };
        Scanner scanner = new Scanner(System.in);
        int money;
        System.out.println("请输入要支付的钱");
        money = scanner.nextInt();
        int res = solve(money, moneyCount);
        System.out.println("需要张数：");
        if (res != -1)
            System.out.println(res);
        else
            System.out.println("No");
    }
}
