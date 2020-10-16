def solution(array, commands):
    answer = []
    for cmd in commands:
        i = cmd[0]
        j = cmd[1]
        k = cmd[2]
        ans = sorted(array[i - 1:j])
        answer.append(ans[k - 1])
    return answer


print(solution([1, 5, 2, 6, 3, 7, 4], [[2, 5, 3], [4, 4, 1], [1, 7, 3]]))
