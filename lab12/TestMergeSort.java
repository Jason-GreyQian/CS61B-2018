import org.junit.Test;
import edu.princeton.cs.algs4.Queue;

import static org.junit.jupiter.api.Assertions.*; // 导入断言方法


public class TestMergeSort {
    @Test
    public void testSmallNumbers() {
        Queue<Integer> q = new Queue<>();
        q.enqueue(1);
        q.enqueue(5);
        q.enqueue(3);
        q.enqueue(2);
        q.enqueue(4);
        q = MergeSort.mergeSort(q);
        Queue<Integer> q2 = new Queue<>();
        for (int i = 1; i < 6; i++) {
            q2.enqueue(i);
        }
        assertEquals(q2.toString(), q.toString());
    }

    @Test
    public void testBigNumbers() {
        Queue<Integer> q1 = new Queue<>();
        Queue<Integer> q2 = new Queue<>();
        int N = 10000;
        for (int i = 1; i <= N; i++) {
            q1.enqueue(i);
        }

        for (int i = N; i >= 1; i--) {
            q2.enqueue(i);
        }
        q2 = MergeSort.mergeSort(q2);
        assertEquals(q1.toString(), q2.toString());

    }

    @Test
    public void testInString() {
        Queue<String> students = new Queue<String>();
        students.enqueue("Alice");
        students.enqueue("Vanessa");
        students.enqueue("Ethan");

        students = MergeSort.mergeSort(students);
        assertEquals("Alice", students.dequeue());
        assertEquals("Ethan", students.dequeue());
        assertEquals("Vanessa", students.dequeue());
        assertTrue(students.isEmpty());
    }
}
