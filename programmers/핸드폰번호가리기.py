def solution(n):
    n = list(n)
    for i in range(0, len(n) - 4):
        n[i] = '*'
    answer = ''.join(n)
    return answer


print(solution("01033334444"))
