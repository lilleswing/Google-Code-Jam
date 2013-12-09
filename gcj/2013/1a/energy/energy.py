__author__ = 'karl_leswing'


def solve(e, r, n, values):
    nextlarger = [-1] * len(values)
    for i in xrange(len(nextlarger) - 1, -1, -1):
        curval = values[i]
        index = i
        while index < len(values) and values[index] <= curval:
            index += 1
        if index < len(values):
            nextlarger[i] = index
    print nextlarger
    currente = e
    total = 0
    for i in xrange(0, len(values)):
        nextlargerindex = nextlarger[i]
        if nextlargerindex == -1:
            spend = currente
        else:
            spend = currente - max((0, e-r*(nextlargerindex-i)))
            if spend < 0:
                spend = 0
        print total
        total += spend * values[i]
        currente -= spend
        currente += r
    return total


if __name__ == '__main__':
    datafile = "B-large-practice"
    data = open("%s.in" % datafile).readlines()
    fout = open("%s.out" % datafile, 'w')
    rounds, data = int(data[0]), data[1:]
    for round in xrange(1, rounds + 1):
        e, r, n = map(lambda x: int(x), data[0].split(' '))
        values = map(lambda x: int(x), data[1].split(' '))
        data = data[2:]
        if(round != 90):
            continue
        print e, r, n
        print values
        energy = solve(e, r, n, values)
        print "Case #%d: %d" % (round, energy)
        fout.write("Case #%d: %d\n" % (round, energy))
    fout.close()

