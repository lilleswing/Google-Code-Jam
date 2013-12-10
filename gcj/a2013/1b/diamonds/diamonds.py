__author__ = 'karl_leswing'
import math

def choose(n, r):
    c = 1L
    denom = 1
    for (num, denom) in zip(xrange(n, n - r, -1), xrange(1, r + 1, 1)):
        c = (c * num) // denom
    return c


def prob(n, x, y):
    k = 1
    while (k+2) * (k + 3) //2 <= n:
        k += 2
    level = abs(x) + y + 1

    if level <= k:
        return 1.0
    if level > k + 2:
        return 0.0

    tiles_left = n - k * (k + 1) // 2
    h = k + 1
    if y == h:
        return 0.0
    if y < tiles_left-h:
        return 1.0

    good = 0
    for i in xrange(y+1, tiles_left + 1):
        good += choose(tiles_left, i)

    return good * 0.5 ** tiles_left


if __name__ == '__main__':
    fpath = "B-large-practice"
    fout = open("%s.out" % fpath, "w")
    data = open("%s.in" % fpath).readlines()
    rounds, data = int(data[0]), data[1:]
    for i in xrange(1, rounds + 1):
        n, x, y = map(lambda x: int(x), data[0].split(' '))
        data = data[1:]
        moves = prob(n, x, y)
        print "Case #%d: %f" % (i, moves)
        fout.write("Case #{0}: {1}\n".format(i, float(moves)))
    fout.close()