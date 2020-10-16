def solution(n, lost, reserve):
    can_wear = [1] * n  # 전체를 1로 먼저 설정
    for rsv in reserve:  # 도움줄수 있는 사람을 2로 설정
        can_wear[rsv - 1] = 2
    for l in lost:  # 잃어버린 학생의 체육복수 -1
        can_wear[l - 1] -= 1

    for i, value in enumerate(can_wear):
        if i > 0 and value == 0 and can_wear[i - 1] == 2:  # 각 index에서 체육복이 없으면 앞사람확인
            can_wear[i] += 1
            can_wear[i - 1] -= 1
        elif i < n - 1 and value == 0 and can_wear[i + 1] == 2:  # 각 index에서 체육복이 없으면 뒷사람 확인
            can_wear[i] += 1
            can_wear[i + 1] -= 1

    cnt = 0
    for stu in range(0, n):
        if can_wear[stu] >= 1:
            cnt += 1

    return cnt


print(solution(5, [2, 4], [1, 3, 5]))
