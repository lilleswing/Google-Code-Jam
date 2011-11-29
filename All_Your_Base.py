def findNextOpen(l):
    return l.index(1)

input = open("in.txt",'r').readlines()
cases = int(input[0])
index = 1
for case in xrange(1,cases+1):
    messageSet = set(input[index].strip())
    messageList = list(input[index].strip())
    mapping = dict()
    index += 1
    base = len(messageSet)
    if(base==1):
        base = 2
    used = [1]*base
    
    mapping[messageList[0]] = 1
    used[1] = 0
    
    for i in xrange(1,len(messageList)):
        char = messageList[i]
        #print(char)
        #print(used)
        #print(mapping)
        if(char not in mapping):
            open = findNextOpen(used)
            used[open] = 0
            mapping[char] = open
    
    
    messageList.reverse()
    sum = 0
    for i in xrange(0,len(messageList)):
        sum += mapping[messageList[i]] * (base**i)        
    
    #print ("%s,%s" % (message,len(message)))
    print("Case #%s: %s" % (case,sum))
    
