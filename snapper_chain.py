def Denary2Binary(n):
    #'''convert denary integer n to binary string bStr'''
    bStr = ''
    if n < 0: raise ValueError, "must be a positive integer"
    if n == 0: return '0'
    while n > 0:
        bStr = str(n % 2) + bStr
        n = n >> 1
    return bStr

input = open("in.txt",'r').readlines()
output = open('out.txt','w')
cases = int(input[0].strip())
index = 1
for case in xrange(1,cases+1):
    varr = input[index].strip().split(" ")
    index += 1
    N = int(varr[0])
    K = int(varr[1])
    #print K
    string = Denary2Binary(K)
    string = list(string)
    string.reverse()
    on = "ON"
    #print ("%s %s" % (K,string))
    if(N > len(string)):
        on = "OFF"
    else:
        for i in xrange(0,N):
            if(string[i] != '1'):
                on = "OFF"
    
    output.write("Case #%s: %s\n" % (case,on))
    
    