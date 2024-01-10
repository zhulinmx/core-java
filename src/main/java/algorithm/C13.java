package algorithm;


/**
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，
 * 所有的偶数位于位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变
 * 思路：每次只和前面一个数交换位置。或者利用辅助数组
 */
public class C13 {

    public static int[] reOrderArray(int[] array) {

        if (array == null) return null;
        //自第二个数开始，逐个和前一个数进行比较
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j = i - 1;
            if (array[i] % 2 != 0) {
                while (j >= 0) {
                    if (array[j] % 2 != 0) {
                        break;
                    }
                    if (array[j] % 2 == 0) {
                        int t = array[j + 1];
                        array[j + 1] = array[j];
                        array[j] = t;
                        j--;
                    }
                }
            }
            array[j + 1] = temp;
        }
        return array;
    }

    public static int[] reOrderArray1(int[] array) {
        if (array.length == 0) return null;
        int[] arrTemp = new int[array.length];
        int j = 0;
        for (int i = 0; i < array.length; i++) {
            if(array[i] % 2 != 0) {
                arrTemp[j] = array[i];
                j++;
            }
        }
        for (int i = 0; i < array.length; i++) {
            if(array[i] % 2 == 0) {
                arrTemp[j] = array[i];
                j++;
            }
        }
        return arrTemp;
    }

    public static void main(String[] args) {
        int[] arr = {1, 4, 6, 7, 3, 9};
        for (int k : reOrderArray1(arr)) {
            System.out.println(k);
        }
    }
}
