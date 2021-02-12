import time
from json2xml import json2xml
from json2xml.utils import readfromjson
startlib = time.time()
for i in range(10):
    outputFile1 = open('libparser.xml', 'w')
    a = readfromjson('schedule.json')
    outputFile1.write(json2xml.Json2xml(a).to_xml())
print(time.time()-startlib)
startmy = time.time()
for i in range(10):
    inputFile = open('schedule.json', 'r')
    outputFile = open('schedule.xml', 'w')
    lines = list()
    counter = -1
    a = ['', '', '', '', '', '']
    nextLine = inputFile.readline()
    nextLine = inputFile.readline()
    while nextLine:
        lines.append(nextLine)
        nextLine = inputFile.readline()
    print(lines)
    outputFile.write('<?xml version="1.0" ?>\n<all>\n')
    for line in lines:
        if (line.find('{') > -1):
            line = line[:line.find('"')] + '<' + line[line.find('"') + 1:line.rfind('"')] + ' type="dict">' + '\n'
            outputFile.write(line)
            counter += 1
            a[counter] = line
        elif (line.find('" : "') > -1):
            line = line[:line.find('"')] + '<' + line[line.find('"') + 1:]
            line = line[:line.find('"')] + ' type=str>' + line[line.find('"') + 1:]
            line = line[:line.find('>') + 1] + line[line.find('"') + 1:]
            line = line[:line.find('"')] + '</' + line[line.find('<') + 1:line.rfind(' type=str>')] + '>\n'
            line = line.replace('str', '"str"')
            outputFile.write(line)
        elif (line == '}'):
            outputFile.write('</all>')
        elif (line.find('}') > -1):
            line = line[:line.find('}')] + '</' + a[counter][a[counter].find('<') + 1:a[counter].find(' type="dict">')] + '>\n'
            counter -= 1
            outputFile.write(line)
print(time.time() - startmy)