def solution(s):
    l = len(s)
    answer = ''
    if l % 2 == 0:
        answer = s[l//2 - 1]+s[l//2]
    elif l % 2 == 1:
        answer = s[l//2]
    return answer

print(solution("abcde"))