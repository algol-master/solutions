import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;
import static java.lang.System.out;

public class BJ2075 {
    static long[] heap;
    static int size;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        size = 0;

        heap = new long[2 * n * n];
        long val;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                val = Long.parseLong(st.nextToken());
                enqueue(val);
            }
        }

        for (int i = 0; i < n - 1; i++) {
            dequeue();
        }

        val = dequeue();
        sb.append(val);
        out.println(val);
    }

    private static void enqueue(long value) {
        heap[++size] = value;
        heapUp(size);
    }

    private static long dequeue() {
        long result = heap[1];
        heap[1] = heap[size--];
        heapDown(1);

        return result;
    }

    private static void swap(int index1, int index2) {
        long tmp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = tmp;
    }

    private static int parent(int index) {
        return index / 2;
    }

    private static int left(int index) {
        return index * 2;
    }

    private static int right(int index) {
        return index * 2 + 1;
    }

    private static void heapUp(int index) {
        while (index > 1 && heap[parent(index)] < heap[index]) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    private static void heapDown(int index) {
        int smallest = index;
        int left = left(index);
        int right = right(index);

        if (left <= size && heap[left] > heap[smallest]) {
            smallest = left;
        }

        if (right <= size && heap[right] > heap[smallest]) {
            smallest = right;
        }

        if (smallest != index) {
            swap(index, smallest);
            heapDown(smallest);
        }
    }
}
