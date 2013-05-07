def calcLookup(riders,k):
    lookup = []
    for i in xrange(0,len(riders)):
        money = 0
        index = i
        originalIndex = i
        loop = False
        numRiders = 0
        while(numRiders + riders[index] <= k):
            if(loop and index == originalIndex):
                break
            numRiders += riders[index]
            money += riders[index]
            index += 1
            if(index == len(riders)):
                index = 0
            loop = True
        lookup.append([money,index])
    return lookup


input = open("in.txt",'r').readlines()
output = open('out.txt','w')

cases = int(input[0].strip())
index = 1

for case in xrange(1,cases+1):
    varr = input[index].strip().split(" ")
    index += 1
    varr = map(int,varr)
    R = varr[0]
    k = varr[1]
    N = varr[2]
    riders = input[index].strip().split(" ")
    index += 1
    riders = map(int,riders)
    lookup = calcLookup(riders,k)
    money = 0
    lineSpot = 0
    for i in xrange(0,R):
        move = lookup[lineSpot]
        money += move[0]
        lineSpot = move[1]
#        print move[0]
    output.write("Case #%s: %s\n" % (case, money))
    