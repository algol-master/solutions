// 문제 : baekjoon 18258 - N번째 큰 수 (실버 2)
// 풀이 날짜 : 24/11/05
// 사용 알고리즘 : priority queue
// 시간 및 메모리 제한 : 1초, 12 MB
// 시도 횟수 : 6
// 성공 여부 : 실패
// 채점 소요 시간 : - ms
// 소요한 메모리 크기 : - KB
// 풀이 소요 시간 : 1시간 15분 13초
// 풀이 참고 사이트 : -

// 입력값 초기화
const readline = require('readline')
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
})
var N = -1
var inputs = []

// 구현부

class PriorityQueue {
    constructor() {
        this.heap = [null]
        this.size = 0
    }

    enqueue(value) {
        this.heap.push(value)
        this.size++
        this.heapifyUp()
    }

    dequeue() {
        var returnVal = this.heap[1]
        this.heap[1] = this.heap[this.size]
        this.heap.pop()
        this.size--
        this.heapifyDown()
        return returnVal
    }

    heapifyUp() {
        var index = this.size
        while (index > 1) {
            const parent = Math.floor(index / 2)
            if (this.heap[parent] > this.heap[index]) {
                this.swap(index, parent)
                index = parent
            } else {
                break
            }
        }
    }

    heapifyDown() {
        var index = 1
        while (true) {
            var leftChild = index * 2
            var rightChild = index * 2 + 1
            var smallest = index

            if (
                leftChild <= this.size &&
                this.heap[leftChild] < this.heap[smallest]
            ) {
                smallest = leftChild
            }

            if (
                rightChild <= this.size &&
                this.heap[rightChild] < this.heap[smallest]
            ) {
                smallest = rightChild
            }

            if (smallest === index) break

            this.swap(index, smallest)
            index = smallest
        }
    }

    swap(idx1, idx2) {
        let tmp = this.heap[idx1]
        this.heap[idx1] = this.heap[idx2]
        this.heap[idx2] = tmp
    }
}

var heap = new PriorityQueue()

rl.on('line', function (line) {
    if (N == -1) N = parseInt(line)
    else {
        inputs = line.split(' ').map((v) => parseInt(v))

        for (let i = 0; i < N; i++) {
            if (heap.size < N) {
                heap.enqueue(inputs[i][j])
            } else {
                if (inputs[i][j] > heap.heap[1]) {
                    heap.dequeue()
                    heap.enqueue(inputs[i][j])
                }
            }
        }
    }
}).on('close', function () {
    var arr = []
    for (let i = 0; i < N; i++) {
        arr.push(heap.dequeue())
    }
    arr.sort((a, b) => b - a)

    console.log(arr[N - 1])
})
