__author__ = 'karl_leswing'
import string


def solve(lawn):
    rowmax = [0] * len(lawn)
    colmax = [0] * len(lawn[0])
    for i in xrange(0, len(lawn)):
        rowmax[i] = max(lawn[i])
    for j in xrange(0, len(lawn[0])):
        best = 0
        for i in xrange(0, len(lawn)):
            if lawn[i][j] > best:
                best = lawn[i][j]
        colmax[j] = best
    for i in xrange(0, len(lawn)):
        for j in xrange(0, len(lawn[i])):
            if lawn[i][j] < rowmax[i] and lawn[i][j] < colmax[j]:
                return False
    return True


if __name__ == '__main__':
    data = map(string.strip, open('B-large.in').readlines())
    fout = open('out.out', 'w')
    runs = int(data[0])
    data = data[1:]
    for run in xrange(1, runs + 1):
        rows, cols = data[0].split(' ')
        rows, cols = int(rows), int(cols)
        data = data[1:]
        lawn = list()
        for i in xrange(0, rows):
            row, data = data[0], data[1:]
            row = map(lambda x: int(x), row.split(' '))
            lawn.append(row)
        result = solve(lawn)
        if result:
            print "Case #%d: YES" % run
            fout.write("Case #%d: YES\n" % run)
        else:
            print "Case #%d: NO" % run
            fout.write("Case #%d: NO\n" % run)


