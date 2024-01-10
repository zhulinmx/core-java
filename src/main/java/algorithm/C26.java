package algorithm;

import java.util.ArrayList;
import java.util.List;

public class C26 {

    public boolean isNumber(String s) {
        int i = 0;
        boolean f1 = false, f2 = false, f3 = false, f4 = false;
        if(s.endsWith("-") || s.endsWith("+") || s.endsWith("e") || s.endsWith("E")) return false;

        while(i< s.length()) {
            char c = s.charAt(i);

            if(c >= 'a' && c <= 'Z' && Character.toUpperCase(c) != 'E') return false;
            if(f1 && i>1 &&  Character.toUpperCase(s.charAt(i-1))!= 'E') return false;
            if(f3 && Character.toUpperCase(c) == 'E') return false;
            if(f2 && c == '.') return false;
            if(f3 && c == '.') return false;
            if(!f4 && c == '.') return false;

            switch (c) {
                case '+' :
                case '-' : f1 = true; break;
                case '.' : f2 = true; break;
                case 'E' :
                case 'e' : f3 = true; break;
                default: f4 = true;
            }
            i++;
        }
        return true;
    }

    public int lengthOfLongestSubstring(String s) {
        if(s.isEmpty()) return 0;
        if(s.length() == 1) return 1;

        int p1 = 1, length = 1, max_length = 1;
        int p2 = 0;
        while (p1 < s.length()) {
            String c = Character.toString(s.charAt(p1));
            String sub = s.substring(0, p1);

            if(sub.lastIndexOf(c) > -1) {
                //repeatable
                if(p2 < sub.lastIndexOf(c)) {
                    p2 = sub.lastIndexOf(c);
                }
                length = p1 - p2;
            } else
                length++;

            if(length > max_length) max_length = length;
            //enhancement
            if(max_length > (s.length() - p2)) return max_length;

            p1++;
        }
        return max_length;
    }

    public String convert(String s, int numRows) {
        int i = 1;
        boolean flag = true; //mark add or sub
        String[] new_cc = new String[numRows];

        if(numRows == 1) return s;

        for (char cc: s.toCharArray()) {
            new_cc[i-1] = ((new_cc[i-1] == null) ? "": new_cc[i-1] ) + cc;

            if(i==numRows) {
                flag = false;
            } else if(i==1){
                flag = true;
            }

            if(flag) i++; else i--;
        }

        StringBuffer bf = new StringBuffer();
        for (int k=0; k<numRows; k++) {
            if(new_cc[k] != null && !new_cc[k].isEmpty())
                bf.append(new_cc[k]);
        }

        return bf.toString();
    }

    public List<String> restoreIpAddresses(String s) {
        List list = new ArrayList<String>();
        if(s.length() < 4 || s.length() > 16)
            return list;

        int cp = 2;
        while(cp<s.length()-2) {

        }

        return list;

    }

    public static void main(String[] args) {

//        System.out.println(new C26().isNumber("."));
//        System.out.println(new C26().lengthOfLongestSubstring("ffffrtrws"));
        /**
         * 输入：s = "PAYPALISHIRING", numRows = 4
         * 输出："PINALSIGYAHRPI"
         */
        System.out.println(new C26().convert("AB", 4));

        System.out.println(new C26().restoreIpAddresses("0000"));
    }
}
