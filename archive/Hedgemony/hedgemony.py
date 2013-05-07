import string


def get_size(bushes):
    for i in xrange(1, len(bushes) -1):
        bushes[i] = min((bushes[i-1] + bushes[i+1]) / 2, bushes[i])
    return bushes[-2]

if __name__ == '__main__':
    fpath = "test"
    fout = open("%s.out" % fpath, "w")
    data = map(string.strip, open("%s.in" % fpath).readlines())
    rounds, data = int(data[0]), data[1:]
    for i in xrange(1, rounds + 1):
        bushes = [float(x) for x in data[1].split(' ')]
        data = data[2:]
        size = get_size(bushes)
        print "Case #%d: %s" % (i, size)
        fout.write("Case #%s: %s\n" % (i, size))
    fout.close()
