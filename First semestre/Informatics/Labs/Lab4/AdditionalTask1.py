import re
file = open('Hamlet.txt', encoding="utf-8")
text = file.read()
pattern = re.compile(r"(?<=[.!?]\s)[qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA][qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA][qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*(?:(?:[\[,:;\'\"()\s\]])+(?:[qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA][qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*|[qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA][qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA][qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA][qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*(?:[qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA]*)*)){5}[\'\"\])]*[.?!]|(?<=[.!?]\s)(?:[qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA][qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*|[qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA][qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA][qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA][qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*(?:[qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA]*)*)(?:[\[,:;\'\"()\s\]])+[qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA][qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA][qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*(?:(?:[\[,:;\'\"()\s\]])+(?:[qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA][qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*|[qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA][qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA][qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA][qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*(?:[qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA]*)*)){4}[\'\"\])]*[.?!]|(?<=[.!?]\s)(?:(?:[qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA][qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*|[qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA][qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA][qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA][qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*(?:[qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA]*)*)(?:[\[,:;\'\"()\s\]])+){2}[qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA][qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA][qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*(?:(?:[\[,:;\'\"()\s\]])+(?:[qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA][qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*|[qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA][qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA][qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA][qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*(?:[qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA]*)*)){3}[\'\"\])]*[.?!]|(?<=[.!?]\s)(?:(?:[qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA][qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*|[qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA][qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA][qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA][qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*(?:[qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA]*)*)(?:[\[,:;\'\"()\s\]])+){3}[qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA][qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA][qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*(?:(?:[\[,:;\'\"()\s\]])+(?:[qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA][qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*|[qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA][qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA][qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA][qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*(?:[qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA]*)*)){2}[\'\"\])]*[.?!]|(?<=[.!?]\s)(?:(?:[qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA][qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*|[qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA][qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA][qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA][qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*(?:[qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA]*)*)(?:[\[,:;\'\"()\s\]])+){4}[qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA][qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA][qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*(?:[\[,:;\'\"()\s\]])+(?:[qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA][qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*|[qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA][qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA][qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA][qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*(?:[qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA]*)*)[\'\"\])]*[.?!]|(?<=[.!?]\s)(?:(?:[qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA][qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*|[qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA][qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA][qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA][qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*(?:[qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA]*)*)(?:[\[,:;\'\"()\s\]])+){5}[qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA][qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[eEyYuUiIoOaA][qQwWrRtTpPsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM]*[\'\"\])]*[.?!]")
for sentence in pattern.findall(text):
    print(sentence + '\n')
file.close()