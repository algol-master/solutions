// 문제 : baekjoon 1715 - 싸지방에 간 준하 (골드 3)
// 풀이 날짜 : 24/11/12
// 사용 알고리즘 : priority queue
// 시간 및 메모리 제한 : 1초, 128 MB
// 시도 횟수 : 2
// 성공 여부 : 성공
// 채점 소요 시간 : 588 ms
// 소요한 메모리 크기 : 53244 KB
// 풀이 소요 시간 : 약 2시간
// 풀이 참고 사이트 : -

// 입력값 초기화
const fs = require("fs")
// const lines = fs.readFileSync("input.txt").toString().trim().split("\n")
const lines = fs.readFileSync("/dev/stdin").toString().trim().split("\n")
const N = Number(lines[0])
const inputs = lines.slice(1).map(v => v.split(' ').map(Number))

// 구현부
class PriorityQueue {
    constructor (compare) {
        this.heap = [null]
        this.size = 0
        this.compare = compare
    }

    pick() {
        return this.heap[1]
    }

    heapifyUp() {
        var index = this.size
        while (index > 1) {
            var parentIdx = Math.floor(index / 2)
            if (this.compare(this.heap[index], this.heap[parentIdx])) {
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
            if (leftChildIdx <= this.size && this.compare(this.heap[leftChildIdx], this.heap[smallestIdx])) {
                smallestIdx = leftChildIdx
            }

            if (rightChildIdx <= this.size && this.compare(this.heap[rightChildIdx], this.heap[smallestIdx])) {
                smallestIdx = rightChildIdx
            }

            if (smallestIdx === index) break
            
            this.swap(smallestIdx, index)
            index = smallestIdx
        }
    }

    enqueue(element) {
        this.heap.push(element)
        this.size++
        this.heapifyUp()
    }

    dequeue() {
        var res = this.heap[1]
        this.heap[1] = this.heap.pop()
        this.size--
        this.heapifyDown()
        return res
    }

    swap(a, b) {
        var tmp = this.heap[a]
        this.heap[a] = this.heap[b]
        this.heap[b] = tmp
    }
}

var availableSeats = new PriorityQueue((a, b) => a < b)
var pq = new PriorityQueue((a, b) => a[0] < b[0])
var count = 0
var uses = new Array(N+1).fill(0)

inputs.sort((a, b) => {
    return a[0] - b[0]
})

for (let i=1; i<=N; i++) {
    availableSeats.enqueue(i)
}

for (let i=0; i<N; i++) {
    const currentTime = inputs[i][0]
    while (pq.size > 0 && pq.pick()[0] <= currentTime) {
        var res = pq.dequeue()
        availableSeats.enqueue(res[1])
    }
    const minSeat = availableSeats.dequeue()
    pq.enqueue([inputs[i][1], minSeat])
    count = Math.max(count, pq.size)
    uses[minSeat]++
}

console.log(count)

var seatAns = ''
for (let i=1; i<=count; i++) {
    seatAns += uses[i] + ' '
}

console.log(seatAns)

