def solution(board, moves):
    cnt = 0
    answer = 0
    take = [0]
    for i in moves:
        for j in board:
            if j[i - 1] != 0:
                take.append(j[i - 1])
                j[i - 1] = 0
                if take[-1] == take[-2]:
                    take.pop()
                    take.pop()
                    answer += 2

                break;

    return answer


print(solution([[0, 0, 0, 0, 0], [0, 0, 1, 0, 3], [0, 2, 5, 0, 1], [4, 2, 4, 4, 2], [3, 5, 1, 3, 1]],
               [1, 5, 3, 5, 1, 2, 1, 4]))
