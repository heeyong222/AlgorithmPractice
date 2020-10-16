from itertools import cycle  # 세명 전부 주기가 있기 때문에 cycle 사용


def solution(answers):
    supo1 = [1, 2, 3, 4, 5]
    supo2 = [2, 1, 2, 3, 2, 4, 2, 5]
    supo3 = [3, 3, 1, 1, 2, 2, 4, 4, 5, 5]  # 주기 입력
    score = [0, 0, 0]

    for i, j, k, ans in zip(cycle(supo1), cycle(supo2), cycle(supo3), answers):  # zip으로 묶어서 한번에 입력
        if i == ans:
            score[0] += 1  # 1번이 맞추면 +1
        if j == ans:
            score[1] += 1  # 2번이 맞추면 +1
        if k == ans:
            score[2] += 1  # 2번이 맞추면 +1
    answer = []
    for i, sc in enumerate(score):  # index와 함께 받기 위해 enumerate함수 사용
        if sc == max(score):  # score중 max 수치라면
            answer.append(i + 1)  # index + 1 이 사람 번호이기때문에 answer에 추가
    return answer


print(solution([1, 2, 3, 4, 5]))
