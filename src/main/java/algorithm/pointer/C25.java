package algorithm.pointer;

public class C25 {
    /**
     * 丑数
     * 技巧：三指针
     * @param n
     * @return
     */
    public static int nthUglyNumber(int n) {
        if(n==1) return 1;

        int[] dp = new int[n];
        dp[0] = 1;
        int a=0, b=0, c=0;

        for(int i=1; i<n; i++) {
            int uglyA = dp[a]*2, uglyB = dp[b]*3, uglyC = dp[c]*5;
            dp[i] = Math.min(Math.min(uglyA , uglyB), uglyC);
            if(uglyA == dp[i]) a++;
            if(uglyB == dp[i]) b++;
            if(uglyC == dp[i]) c++;
        }

        return dp[n-1];
    }

    public static void main(String[] args) {
        System.out.println(C25.nthUglyNumber(10));
    }
}
