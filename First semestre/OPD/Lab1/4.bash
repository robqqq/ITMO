#!/bin/bash
cd lab0/bulbasaur3
wc palpitoad delcatty poliwhirl 2> /dev/null > /tmp/hello
cd ..
ls -l * */*  2> /dev/null|grep -v total|grep -v ^d|sort -k2|tail -2l
cat -n z* */z* 2> /dev/null|sort -k2
ls -lur f* */f* 2> /tmp/hello|grep -v total|grep -v /|tail -4l
ls -l *pa* */*pa* 2> /dev/null|sort -k5|head -n2
ls -lt *1 */*1 2>&1|grep -v total|grep -v 1:|tail -2l