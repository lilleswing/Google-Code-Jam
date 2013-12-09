import gcj_util
[cases, input] = gcj_util.ReadInput('in.txt')
index = 0
for case in xrange(1,cases+1):
    S = int(input[index])
    index += 1
    engines = set()
    for i in xrange(0,S):
        engines.add(input[index])
        index += 1
    Q = int(input[index])
    index += 1
    queries = list()
    for i in xrange(0, Q):
        queries.append(input[index])
        index += 1
    #print queries
    #print engines
    switches = 0
    round_engines = set()
    for query in queries:
        round_engines.add(query)
        if(len(round_engines) == len(engines)):
            switches += 1
            round_engines = set()
            round_engines.add(query)
    print("Case #%s: %s" % (case, switches))
        