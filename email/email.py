__author__ = 'karl_leswing'
import string
import cPickle as pickle


def enstarifyword(word, stars, n):
    return "".join(["*" if (z in stars) else word[z] for z in xrange(n)])


dictionary = set()

def precompute():
    words = set(map(string.strip, open("dictionary.txt").readlines()))
    dictionary = set()
    for word in words:
        dictionary.add(word)
        n = len(word)
        for i in xrange(0, n):
            dictionary.add(enstarifyword(word, [i], n))
        for i in xrange(0, n):
            for j in xrange(i + 5, n):
                dictionary.add(enstarifyword(word, [i, j], n))
    pickle.dump(dictionary, open('stars.p', 'wb'))


def load_dict():
    global dictionary
    dictionary = pickle.load(open('stars.p', 'rb'))


def getswaps(line):
    n = len(line)
    dp = [[float('inf') for x in xrange(0, n + 1)] for y in xrange(0, 5)]
    for i in xrange(0, 5):
        dp[i][0] = 0
    for i in xrange(1, n + 1):
        for wordlen in xrange(1, min(i, 10) + 1):
            garble = line[i - wordlen:i]
            possstars = [[]] + [[x] for x in xrange(0, wordlen)] + [[x, y] for x in xrange(0, wordlen) for y in
                                                                    xrange(x + 5, wordlen)]
            possstars = [x for x in possstars if enstarifyword(garble, x, wordlen) in dictionary]
            for j1 in xrange(0, 5):
                for j2 in xrange(0, min(5, j1 + wordlen + 1)):
                    if dp[j1][i-wordlen] != float('inf'):
                        valids = [p for p in possstars if p == [] or (min(p) + j1 >= 4 and max(p) + j2 <= wordlen - 1)]
                        if len(valids) > 0:
                            dp[j2][i] = min(dp[j2][i], dp[j1][i - wordlen] + min([len(z) for z in valids]))

    return min(map(lambda x: dp[x][-1], xrange(0, 5)))


if __name__ == '__main__':
    #precompute()
    load_dict()
    fpath = "test"
    fout = open("%s.out" % fpath, "w")
    data = map(string.strip, open("%s.in" % fpath).readlines())
    rounds, data = int(data[0]), data[1:]
    for i in xrange(1, rounds + 1):
        s, data = data[0], data[1:]
        swaps = getswaps(s)
        print "Case #%d: %s" % (i, swaps)
        fout.write("Case #%s: %s\n" % (i, swaps))
    fout.close()