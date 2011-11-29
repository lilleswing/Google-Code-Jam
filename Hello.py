def solve(credit,items):
    items = items.split(" ");
    credit = int(credit)
    for i in xrange(0,len(items)):
        items[i] = int(items[i])
    hashing = [0] * 1005;
    for i in xrange(0,len(items)):
        currValue = items[i]
        if(hashing[credit-currValue] != 0):
            return [hashing[credit-currValue], i+1]
        hashing[currValue] = i+1
    return ["fail", "fail"]
        
words = open("words.txt", 'r').readlines()
for i in xrange(0, len(words)):
    words[i] = words[i].strip()
cases = int(words[0]);
#print cases
i = 1
#print("starting assignment")
for j in xrange(0,cases):
        #print(words[i])
        #print(words[i+2])
        a = solve(words[i],words[i+2])
        i += 3
        print("Case #%s: %s %s"%(j+1,a[0],a[1]))
        