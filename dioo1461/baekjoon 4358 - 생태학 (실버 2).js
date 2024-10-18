// 문제 : baekjoon 4358 - 생태학 (실버 2)
// 풀이 날짜 : 24/10/15
// 사용 알고리즘 : hashmap
// 시간 및 메모리 제한 : 1초, 256 MB
// 시도 횟수 : 1
// 성공 여부 : 성공
// 채점 소요 시간 : 516 ms
// 소요한 메모리 크기 : 109112 KB
// 풀이 소요 시간 : 13분 19초
// 풀이 참고 사이트 : -

// 입력값 초기화
const fs = require("fs")
// const lines = fs.readFileSync("input.txt").toString().trim().split("\n")
const lines = fs.readFileSync("/dev/stdin").toString().trim().split("\n")
const inputs = lines.map((v, i) => v.trim())

// 구현부
var map = new Map()
inputs.map((v, i) => {
    if (!map.get(v)) {
        map.set(v, 1)
    } else {
        map.set(v, map.get(v) + 1)
    }
})

var res = []

map.forEach((v, k) => {
    res.push([k, v])
})

res.sort()

var ans = ""

res.forEach((v, i) => {
    var ratio = ((v[1] / inputs.length) * 100).toFixed(4)
    ans += `${v[0]} ${ratio}\n`
})

console.log(ans)
