// 문제 : baekjoon 1946 - 신입 사원 (실버 1)
// 풀이 날짜 : 24/12/27
// 사용 알고리즘 : greedy, sort
// 시간 및 메모리 제한 : 2초, 256 MB
// 시도 횟수 : 3
// 성공 여부 : 성공
// 채점 소요 시간 : 3984 ms
// 소요한 메모리 크기 : 398332 KB
// 풀이 소요 시간 : 1시간 43분 34초
// 풀이 참고 사이트 : -

// 입력값 초기화
const fs = require('fs')
// const lines = fs.readFileSync('input.txt').toString().trim().split('\n')
const lines = fs.readFileSync('/dev/stdin').toString().trim().split('\n')
const T = Number(lines[0])

// 구현부
var lineIdx = 1
var tc = 0

while (tc < T) {
    const cnt = Number(lines[lineIdx])
    lineIdx++
    const applicants = lines.slice(lineIdx, lineIdx + cnt).map(v => v.split(' ').map(Number))
    lineIdx += cnt
    
    applicants.sort((a,b) => a[0]-b[0])
    
    var validCnt = cnt
    var right = applicants[0][1]

    for (let i=1; i<cnt; i++) {
        if (right < applicants[i][1]) {
            validCnt--
        } else {
            right = applicants[i][1]
        }
    }
    console.log(validCnt)
    tc++
}