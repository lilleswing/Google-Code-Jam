def add2set(path, set):
        path = path.split('/')[1:]
        s = path[0]
        set.add(s);
        for j in xrange(1,len(path)):
            s = "%s/%s" % (s,path[j])
            set.add(s)
    
input = open('in.txt', 'r').readlines()
T = int(input[0])
index = 1
for case in xrange(0, T):
    have = set()
    need = set()
    varr = input[index].split(' ');
    index += 1
    N = int(varr[0])
    M = int(varr[1])
    for i in xrange(0, N):
        path = input[index].strip()
        index += 1
        add2set(path,have)
    for i in xrange(0, M):
        path = input[index].strip()
        index += 1
        add2set(path,need)
    sum = 0
    #print need
    #print have
    for path in need:
        #print path
        if(path not in have):
            sum +=1 
    print("Case #%s: %s" % (case+1, sum))