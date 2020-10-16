def solution(arr):
    answer = []
    index = 0
    answer.append(arr[0])
    for i in arr[1:]:
        if i != answer[index]:
            answer.append(i)
            index += 1

    return answer


print(solution([1, 1, 3, 3, 0, 1, 1]))
