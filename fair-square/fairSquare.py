__author__ = 'karl_leswing'
import string

size = 10 ** 14
sqf = list()
top = int(size ** 0.5) + 1


def check_forward(i):
    a = i * i
    sq = str(a)
    if sq == sq[::-1]:
        sqf.append(a)


def gen_pal(i):
    p1 = int("%s%s" % (i, i[::-1]))
    p2 = int("%s%s" % (i, i[::-1][1:]))
    check_forward(p1)
    check_forward(p2)
    if p2 < top:
        for n in xrange(0, 10):
            gen_pal(i + str(n))


def sieve():
    global sqf
    for i in xrange(1, 10):
        gen_pal(str(i))
    sqf = sorted(sqf)


def solve(a, b):
    return len(filter(lambda x: a <= x <= b, sqf))


if __name__ == '__main__':
    data = map(string.strip, open('C-large-1.in').readlines())
    fout = open('large1.out', 'w')
    sieve()
    runs = int(data[0])
    data = data[1:]
    for run in xrange(1, runs + 1):
        A, B, = data[0].split(' ')
        A, B = int(A), int(B)
        data = data[1:]
        retval = solve(A, B)
        retstring = "Case #%d: %d" % (run, retval)
        print retstring
        fout.write("%s\n" % retstring)
    fout.close()
    print sqf
    fout = open('fqsq.txt', 'w')
    for fq in sqf:
        fout.write("%s\n" % fq)
    fout.close()
