import string


def ex_value(B, probs):
    base = B - len(probs)
    best = float('inf')
    p = 1
    for i in xrange(0, len(probs)+1):
        if i != 0:
            p = p * probs[i-1]
        #print probs[:i], i, p
        right = 2 * (len(probs) - i) + base + 1
        wrong = right + B + 1
        ex = p * right + (1 - p) * wrong
        if ex < best:
            #print best, i, probs, p
            best = ex
    if B + 2 < best:
        best = B + 2
    return best


if __name__ == '__main__':
    fpath = "A-large-practice"
    fout = open("%s.out" % fpath, "w")
    data = map(string.strip, open("%s.in" % fpath).readlines())
    rounds, data = int(data[0]), data[1:]
    for i in xrange(1, rounds + 1):
        A, B = [int(x) for x in data[0].split(' ')]
        probs = [float(x) for x in data[1].split(' ')]
        data = data[2:]
        ex = ex_value(B, probs)
        print "Case #%d: %f" % (i, ex)
        fout.write("Case #%d: %f\n" % (i, ex))
    fout.close()