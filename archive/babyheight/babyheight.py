import string
import re


def s2in(s):
    m = re.match("(?P<feet>\d+)'(?P<inches>\d+)\"", s)
    feet = int(m.group('feet'))
    inch = int(m.group('inches'))
    return 12 * feet + inch


def parse(person):
    person = person.split(' ')
    return person[0], s2in(person[1]) + s2in(person[2])


def in2s(inch):
    feet = int(inch) / 12
    inch = int(inch) % 12
    return '%s\'%s"' % (feet, inch)


def get_range(gender, height):
    if gender == 'G':
        height -= 5
    else:
        height += 5
    base = float(height)/2
    low = base - 4
    high = base + 4
    if int(low) < low:
        low = int(low) + 1
    if int(high) < high:
        high = int(high)
    return """%s to %s""" % (in2s(low), in2s(high))



if __name__ == '__main__':
    fpath = "test"
    fout = open("%s.out" % fpath, "w")
    data = map(string.strip, open("%s.in" % fpath).readlines())
    rounds, data = int(data[0]), data[1:]
    for i in xrange(1, rounds + 1):
        gender, height = parse(data[0])
        data = data[1:]
        range = get_range(gender, height)
        print "Case #%d: %s" % (i, range)
        fout.write("Case #%s: %s\n" % (i, range))
    fout.close()