package algorithm;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * leetcode 295. 数据流的中位数
 *
 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
 * 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 *
 * 思路：创建优先级队列维护大顶堆和小顶堆两个堆，并且小顶堆的值都大于大顶堆的值，2个堆个数的差值小于等于1，
 * 所以当插入个数为奇数时：大顶堆个数就比小顶堆多1，中位数就是大顶堆堆头；
 * 当插入个数为偶数时，使大顶堆个数跟小顶堆个数一样，中位数就是 2个堆堆头平均数。也可使用集合类的排序方法
 *
 */
public class C24 {

    public static void main(String[] args) {
        MedianFinder mf = new MedianFinder();
        mf.addNum(1);
        System.out.println(mf.findMedian());
        mf.addNum(3);
        mf.addNum(4);
        System.out.println(mf.findMedian());
        mf.addNum(2);
        System.out.println(mf.findMedian());
    }

}

class MedianFinder {

    int count = 0;
    // 两个优先级队列
    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;

    public MedianFinder() {
        this.minHeap = new PriorityQueue<>(16);
        this.maxHeap = new PriorityQueue<>(16, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
    }

    public void addNum(int num) {
        count++;
        // 当数据的个数为奇数时，进入小根堆，再把小根堆的顶加入大根堆
        if ((count & 1) == 1) {
            minHeap.offer(num);
            maxHeap.offer(minHeap.poll());
        } else {
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());
        }
    }

    public double findMedian() {
        if (count == 0)
            return 0;
        // 当数据个数是奇数时，中位数就是大根堆的顶点
        if ((count & 1) == 1) {
            return (double) maxHeap.peek();
        } else {
            return (minHeap.peek() + maxHeap.peek()) / 2.0;
        }
    }
}
