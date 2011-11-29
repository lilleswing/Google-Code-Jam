import re

words = open("words.txt", 'r').readlines()
arr = words[0].split(" ");

L = int(arr[0])
D = int(arr[1])
N = int(arr[2])

known_words = []
for i in xrange(1, 1+D):
    known_words.append(words[i].strip())


patterns = []
for i in xrange(1+D, len(words)):
    s = words[i].strip()
    s = s.replace('(','[')
    s = s.replace(')',']')
    patterns.append(s)
    #print(s)

case = 1
for pattern in patterns:
    sum = 0
    for attempt in known_words:
       #if(re.match(pattern, attempt)):
       #print("%s,%s" % (attempt, pattern));
       patt = re.compile(pattern)
       if(patt.match(attempt)):
           sum += 1
           
    print("Case #%s: %s" %(case, sum)) 
    case += 1
