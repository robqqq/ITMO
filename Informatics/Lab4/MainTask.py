import re
for i in range(5):
    fileName = 'test' + str(i+1) +'.txt'
    file = open(fileName, encoding="utf-8")
    text = file.read()
    print(text + '\n')
    pattern = re.compile(r"\b(?:[0-1]\d|2[0-3])(?::[0-5]\d){1,2}(?=\W)")
    matches = pattern.findall(text)
    counter = 0
    for time in matches:
        print('Change ' + time + ' to ', end='')
        matches[counter] = input()
        counter += 1
    for time in reversed(matches):
        text = pattern.sub(matches[counter - 1], text, counter)
        counter -= 1
    print('\n' + text + '\n')