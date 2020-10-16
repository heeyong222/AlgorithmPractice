def solution(n):
    a = ""
    for i in range(0, n):
        if i == 0:
            a = a + "수"
        elif i % 2 == 1:
            a = a + "박"
        else:
            a = a + "수"
    answer = a
    return answer


print(3)
