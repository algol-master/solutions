// 문제 : baekjoon 3190 - 뱀 (골드 4)
// 풀이 날짜 : 24/11/05
// 사용 알고리즘 : queue
// 시간 및 메모리 제한 : 1초, 128 MB
// 시도 횟수 : 2
// 성공 여부 : 성공
// 채점 소요 시간 : 180 ms
// 소요한 메모리 크기 : 13120 KB
// 풀이 소요 시간 : 약 2시간
// 풀이 참고 사이트 : -

// 입력값 초기화
const fs = require("fs")
// const lines = fs.readFileSync("input.txt").toString().trim().split("\n")
const lines = fs.readFileSync("/dev/stdin").toString().trim().split("\n")
const N = Number(lines[0])
const K = Number(lines[1])
const apples = lines.slice(2, 2 + K).map((v) => v.split(" ").map(Number))
const L = Number(lines[2 + K])
const controls = lines.slice(3 + K).map((v) => v.trim().split(" "))

// 구현부
class Queue {
  constructor(capacity) {
    this.queue = new Array(capacity)
    this.front = 0
    this.rear = 0
    this.capacity = capacity
    this.size = 0
  }

  push(value) {
    if (this.size == this.capacity) return null
    this.queue[this.rear] = value
    this.rear = (this.rear + 1) % this.capacity
    this.size++
  }

  shift() {
    if (this.size == 0) {
      return null
    }
    const res = this.queue[this.front]
    this.front = (this.front + 1) % this.capacity
    this.size--
    return res
  }
}

var queue = new Queue(1000)

var validMap = new Array(N + 1).fill(0).map((v) => new Array(N + 1).fill(true))
var appleMap = new Array(N + 1).fill(0).map((v) => new Array(N + 1).fill(false))

apples.forEach((v) => {
  appleMap[v[0]][v[1]] = true
})

const getNextHeadPos = (headX, headY) => {
  switch (direction) {
    case "up":
      ;[headX, headY] = [headX - 1, headY]
      break

    case "down":
      ;[headX, headY] = [headX + 1, headY]
      break

    case "left":
      ;[headX, headY] = [headX, headY - 1]
      break

    case "right":
      ;[headX, headY] = [headX, headY + 1]
      break
  }
  return [headX, headY]
}

const checkGameOver = (headX, headY) => {
  if (headX > N || headY > N || headX < 1 || headY < 1) {
    // console.log("game over")
    return true
  }
  if (validMap[headX][headY] == false) {
    // console.log("game over")
    return true
  }
  return false
}

const moveHeadToPos = (headX, headY) => {
  // console.log(`head: [${headX}][${headY}]`)
  queue.push([headX, headY])
  validMap[headX][headY] = false
}

const eatAppleIfExists = (headX, headY) => {
  if (appleMap[headX][headY]) {
    // console.log(`eat apple - [${headX}][${headY}]`)
    appleMap[headX][headY] = false
    return true
  }
  return false
}

const switchDirection = (op) => {
  switch (direction) {
    case "up":
      direction = op == "L" ? "left" : "right"
      break
    case "down":
      direction = op == "L" ? "right" : "left"
      break
    case "left":
      direction = op == "L" ? "down" : "up"
      break
    case "right":
      direction = op == "L" ? "up" : "down"
      break
  }
  // console.log(`switched direction to ${direction}`)
}

queue.push([1, 1])
var [headX, headY] = [1, 1]
var direction = "right"
var time = 0
let idx = -1
controls.push([Infinity, ""])

while (true) {
  idx++
  var control = controls[idx]
  while (time < Number(control[0])) {
    time++
    ;[headX, headY] = getNextHeadPos(headX, headY)

    if (checkGameOver(headX, headY)) {
      console.log(time)
      return
    }

    moveHeadToPos(headX, headY)

    if (!eatAppleIfExists(headX, headY)) {
      const [tailX, tailY] = queue.shift()
      validMap[tailX][tailY] = true
    }
  }

  if (idx == controls.length) {
    // console.log(time)
    return
  }
  switchDirection(control[1])
}
