import math
hash = dict()
l = [0, 0]

for i in xrange(2,1001):
    print i
    k = int((3+math.sqrt(5))) % 1000
    l.append(k)
    if(k in hash):
        print "SOLVED!"
    hash[k] = i
    
    