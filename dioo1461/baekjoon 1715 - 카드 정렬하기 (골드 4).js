// 문제 : baekjoon 1715 - 카드 정렬하기 (골드 4)
// 풀이 날짜 : 24/11/08
// 사용 알고리즘 : priority queue
// 시간 및 메모리 제한 : 1초, 128 MB
// 시도 횟수 : 1
// 성공 여부 : 성공
// 채점 소요 시간 : 268 ms
// 소요한 메모리 크기 : 17776 KB
// 풀이 소요 시간 : 약 10분
// 풀이 참고 사이트 : -

// 입력값 초기화
const fs = require("fs")
// const lines = fs.readFileSync("input.txt").toString().trim().split("\n")
const lines = fs.readFileSync("/dev/stdin").toString().trim().split("\n")
const N = Number(lines[0])
const inputs = lines.slice(1).map(Number)

// 구현부
class PriorityQueue {
    constructor () {
        this.heap = [null]
        this.size = 0
    }

    heapifyUp() {
        var index = this.size
        while (index > 1) {
            var parentIdx = Math.floor(index / 2)
            if (this.heap[index] < this.heap[parentIdx]) {
                this.swap(index, parentIdx)
                index = parentIdx
            } else {
                break
            }
        }

    }

    heapifyDown() {
        var index = 1
        while (true) {
            var leftChildIdx = index * 2
            var rightChildIdx = index * 2 + 1
            var smallestIdx = index
            if (leftChildIdx <= this.size && this.heap[leftChildIdx] < this.heap[smallestIdx]) {
                smallestIdx = leftChildIdx
            }

            if (rightChildIdx <= this.size && this.heap[rightChildIdx] < this.heap[smallestIdx]) {
                smallestIdx = rightChildIdx
            }

            if (smallestIdx === index) break
            
            this.swap(smallestIdx, index)
            index = smallestIdx
            
        }
    }

    enqueue(element) {
        this.heap.push(element)
        this.heapifyUp()
        this.size++
    }

    dequeue() {
        var res = this.heap[1]
        this.heap[1] = this.heap.pop()
        this.heapifyDown()
        this.size--
        return res
    }

    swap(a, b) {
        var tmp = this.heap[a]
        this.heap[a] = this.heap[b]
        this.heap[b] = tmp
    }

}

var priorityQueue = new PriorityQueue()
var sum = 0

for (let i=0; i<N; i++) {
    priorityQueue.enqueue(inputs[i])
}

while (priorityQueue.size > 1) {
    var cardsA = priorityQueue.dequeue()
    var cardsB = priorityQueue.dequeue()
    sum += cardsA + cardsB
    priorityQueue.enqueue(cardsA + cardsB)
}

console.log(sum)