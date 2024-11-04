// 문제 : baekjoon 18258 - 큐 2 (실버 4)
// 풀이 날짜 : 24/11/05
// 사용 알고리즘 : queue
// 시간 및 메모리 제한 : 1초, 512 MB
// 시도 횟수 : 3
// 성공 여부 : 성공
// 채점 소요 시간 : 3512 ms
// 소요한 메모리 크기 : 496172 KB
// 풀이 소요 시간 : 38분 20초
// 풀이 참고 사이트 : -

// 입력값 초기화
const fs = require('fs')
// const lines = fs.readFileSync('input.txt').toString().trim().split('\n')
const lines = fs.readFileSync('/dev/stdin').toString().trim().split('\n')
const N = Number(lines[0])
const inputs = lines.slice(1).map((v) => v.split(' ').map((v) => v.trim()))

// 구현부
class Queue {
    constructor(capacity) {
        this.queue = new Array(capacity)
        this.front = 0
        this.rear = 0
        this.size = 0
        this.capacity = capacity
    }

    pickFront() {
        if (this.size === 0) return -1
        return this.queue[this.front]
    }

    pickBack() {
        if (this.size === 0) return -1
        return this.queue[(this.rear - 1 + this.capacity) % this.capacity]
    }

    push(value) {
        this.size++
        this.queue[this.rear] = value
        this.rear = (this.rear + 1) % this.capacity
    }

    shift() {
        if (this.size === 0) return -1
        this.size--
        var res = this.queue[this.front]
        this.front = (this.front + 1) % this.capacity
        return res
    }
}

var queue = new Queue(2000001)
var ans = ''

inputs.forEach((v, i) => {
    switch (v[0]) {
        case 'push':
            queue.push(v[1])
            break
        case 'pop':
            ans += queue.shift() + '\n'
            break
        case 'size':
            ans += queue.size + '\n'
            break
        case 'empty':
            ans += (queue.size === 0 ? 1 : 0) + '\n'
            break
        case 'front':
            ans += queue.pickFront() + '\n'
            break
        case 'back':
            ans += queue.pickBack() + '\n'
            break
    }
    if (ans.length >= 25000) {
        ans = ans.slice(0, ans.length - 1)
        console.log(ans)
        ans = ''
    }
})

console.log(ans)
