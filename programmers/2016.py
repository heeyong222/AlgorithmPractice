def solution(a, b):
    ans = 0
    date = [31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]  # 각 월별 날짜수
    day = ["SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"]
    for i in range(0, a - 1):
        ans += date[i]
    answer = day[(ans + b + 4) % 7]
    return answer


print(solution(5, 24))
