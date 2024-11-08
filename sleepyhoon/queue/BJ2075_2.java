package sleepyhoon.queue;

import java.util.Scanner;

public class BJ2075_2 {

    // 최대 힙을 구현하자
    static class PQ {
        int[] heap;
        int size;
        int capacity;

        public PQ(int capacity) {
            this.capacity = capacity;
            this.size = 0;
            this.heap = new int[this.capacity];
        }

        public int getParent(int index) {
            return index / 2;
        }

        public int getLeftChild(int index) {
            return 2 * index;
        }

        public int getRightChild(int index) {
            return 2 * index + 1;
        }

        public void enqueue(int value) {
            this.heap[++size] = value;
            heapUp(size);
        }

        public int dequeue() {
            int result = this.heap[1];
            heap[1] = heap[size--];
            heapDown(1);

            return result;
        }

        private void swap(int index1, int index2) {
            int tmp = this.heap[index1];
            this.heap[index1] = this.heap[index2];
            this.heap[index2] = tmp;
        }

        private void heapUp(int index) {
            while(index > 1 && this.heap[getParent(index)] < this.heap[index]) {
                swap(index,getParent(index));
                index = getParent(index);
            }
        }

        private void heapDown(int index) {
            int smallest = index;
            int left = getLeftChild(index);
            int right = getRightChild(index);

            if (left <= size && heap[left] > heap[smallest]) {
                smallest = left;
            }

            if (left <= size && heap[right] > heap[smallest]) {
                smallest = right;
            }

            if (smallest != index) {
                swap(index, smallest);
                heapDown(smallest);
            }
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        PQ pq = new PQ(n*n*2);
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                pq.enqueue(sc.nextInt());
            }
        }
        for(int i=0;i<n-1;i++) {
            pq.dequeue();
        }
        System.out.println(pq.dequeue());
    }
}
