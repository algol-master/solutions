// 문제 : BOJ 4485 - 녹색 옷 입은 애가 젤다지? (골드4)
// 풀이 날짜 : 25/01/19
// 사용 알고리즘 : dijkstra
// 시간 및 메모리 제한 : 1초, 256 MB
// 시도 횟수 : 8
// 성공 여부 : 성공
// 채점 소요 시간 : 340 ms
// 소요한 메모리 크기 : 26016 KB
// 풀이 소요 시간 : 2시간 33분 15초
// 풀이 참고 사이트 : -

// 입력값 초기화
const fs = require('fs')
// const lines = fs.readFileSync('input.txt').toString().trim().split('\n')
const lines = fs.readFileSync(0, 'utf-8').toString().trim().split('\n')
const inputs = lines

// 구현부
class PQ {
    constructor(compare) {
        this.heap = [null]
        this.size = 0
        this.compare = compare
    }

    heapifyUp() {
        var index = this.size
        while (index > 1) {
            var parentIdx = Math.floor(index / 2)
            if (this.compare(this.heap[index], this.heap[parentIdx]) < 0) {
                this.swap(index, parentIdx)
                index = parentIdx
            } else {
                break
            }
        }
    }

    heapifyDown() {
        let index = 1
        while (true) {
            let leftChildIdx = index * 2
            let rightChildIdx = leftChildIdx + 1
            let smallestIdx = index
            if (
                leftChildIdx <= this.size &&
                this.compare(this.heap[leftChildIdx], this.heap[smallestIdx]) <
                    0
            ) {
                smallestIdx = leftChildIdx
            }
            if (
                rightChildIdx <= this.size &&
                this.compare(this.heap[rightChildIdx], this.heap[smallestIdx]) <
                    0
            ) {
                smallestIdx = rightChildIdx
            }

            if (index === smallestIdx) break

            this.swap(smallestIdx, index)
            index = smallestIdx
        }
    }

    enqueue(data) {
        this.heap.push(data)
        this.size++
        this.heapifyUp()
    }

    dequeue() {
        const res = this.heap[1]
        this.heap[1] = this.heap.pop()
        this.size--
        this.heapifyDown()
        return res
    }

    swap(a, b) {
        ;[this.heap[a], this.heap[b]] = [this.heap[b], this.heap[a]]
    }
}

let buffer = ''
let bufferSize = 0

let line = 0
let tc = 1

const vertical = [1, -1, 0, 0]
const horizontal = [0, 0, 1, -1]

while (line < inputs.length) {
    const N = Number(inputs[line])
    if (N === 0) break
    line++
    let cave = new Array(N).fill(0)
    for (let i = 0; i < N; i++) {
        cave[i] = inputs[line].split(' ').map(Number)
        line++
    }

    const distance = new Array(N)
        .fill(0)
        .map((v) => new Array(N).fill(Infinity))

    const pq = new PQ((a, b) => a[2] - b[2])
    distance[0][0] = cave[0][0]
    pq.enqueue([0, 0, cave[0][0]])

    while (pq.size > 0) {
        const [x, y, dist] = pq.dequeue()

        if (dist > distance[x][y]) {
            continue
        }

        for (let i = 0; i < 4; i++) {
            let newX = x + vertical[i]
            let newY = y + horizontal[i]
            if (newX < 0 || newX >= N || newY < 0 || newY >= N) continue

            const newDist = dist + cave[newX][newY]
            if (newDist >= distance[newX][newY]) continue
            distance[newX][newY] = newDist
            pq.enqueue([newX, newY, newDist])
        }
    }

    if (bufferSize > 10000) {
        process.stdout.write(buffer)
        bufferSize = 0
        buffer = ''
    }
    buffer += `Problem ${tc}: ${distance[N - 1][N - 1]}\n`
    bufferSize++
    // console.log(`Problem ${tc}: ${distance[N - 1][N - 1]}`)
    tc++
}

process.stdout.write(buffer)
