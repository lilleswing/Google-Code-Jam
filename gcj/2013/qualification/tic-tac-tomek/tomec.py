__author__ = 'karl_leswing'
import string


def eval4(s):
    if len(s) == 2:
        if 'X' in s and 'T' in s:
            return 1
        if 'O' in s and 'T' in s:
            return 2
    if len(s) == 1:
        if 'X' in s:
            return 1
        if 'O' in s:
            return 2
    return 0


def solve(board):
    state = 0
    for row in board:
        s = set(row)
        retval = eval4(s)
        if retval != 0:
            return retval

    for i in xrange(0,4):
        s = set([board[0][i], board[1][i], board[2][i], board[3][i]])
        retval = eval4(s)
        if retval != 0:
            return retval
    s1 = set()
    s2 = set()
    for i in xrange(0,4):
        s1.add(board[i][i])
        s2.add(board[3-i][i])
    retval = eval4(s1)
    if retval != 0:
        return retval
    retval = eval4(s2)
    if retval != 0:
        return retval
    s3 = set()
    for row in board:
        for char in row:
            s3.add(char)
    if '.' not in s3:
        return 3
    return 0


if __name__ == '__main__':
    data = map(string.strip, open('A-large.in').readlines())
    out = open('out.out', 'w')
    runs = int(data[0])
    data = data[1:]
    for run in xrange(1,runs+1):
        base = "Case #%d: " % run
        board = data[:4]
        data = data[5:]
        retval = solve(board)
        if retval == 0:
            print "%s Game has not completed" % base
            out.write("%s Game has not completed\n" % base)
        if retval == 1:
            print "%s X won" % base
            out.write("%s X won\n" % base)
        if retval == 2:
            print "%s O won" % base
            out.write("%s O won\n" % base)
        if retval == 3:
            print "%s Draw" % base
            out.write("%s Draw\n" % base)
    out.close()


