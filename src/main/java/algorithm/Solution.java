package algorithm;


class Solution {
    public String addBinary(String a, String b) {
        int i = 0;
        int alen = a.length();
        int blen = b.length();
        int result = 0;
        StringBuffer sbf = new StringBuffer();
        while(i< alen || i < blen) {
            int m = (i < alen) ? Integer.valueOf(Character.toString(a.charAt(alen - i -1))) : 0;
            int n = (i < blen) ? Integer.valueOf(Character.toString(b.charAt(blen - i -1))) : 0;
            if (m + n + result > 2) {
                result = 1;
                sbf.append(1);
            }else if (m + n + result == 2) {
                result = 1;
                sbf.append(0);
            }else if (m + n + result == 1) {
                result = 0;
                sbf.append(1);
            }else{
                result = 0;
                sbf.append(0);
            }
            i++;
        }
        if(result > 0) {
            sbf.append(result);
        }
        return sbf.reverse().toString();
    }

    public boolean isPalindrome(int x) {
        if(x<0) return false;
        if(x<10) return true;

        int m = x, n=0, x1=0;
        while(m > 0) {
            n = m%10;
            m = m/10;
            x1 = x1*10 + n;
        }

        return (x1 == x) ? true : false;
    }
}
