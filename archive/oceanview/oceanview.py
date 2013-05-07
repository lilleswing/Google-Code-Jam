import string


def subsequence(houses):
    dp = [float('inf')] * (len(houses)+ 1)
    dp[0] = 0
    length = 0
    for house in houses:
        for i in xrange(1, len(dp)):
            if dp[i-1] < house:
                dp[i] = min(dp[i], house)
                if i > length:
                    length = i
    return len(houses) - length

if __name__ == '__main__':
    fpath = "C-large-practice"
    fout = open("%s.out" % fpath, "w")
    data = map(string.strip, open("%s.in" % fpath).readlines())
    rounds, data = int(data[0]), data[1:]
    for i in xrange(1, rounds + 1):
        houses = [float(x) for x in data[1].split(' ')]
        data = data[2:]
        size = subsequence(houses)
        print "Case #%d: %s" % (i, size)
        fout.write("Case #%s: %s\n" % (i, size))
    fout.close()
