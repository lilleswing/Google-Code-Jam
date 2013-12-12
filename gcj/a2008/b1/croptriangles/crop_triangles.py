class Case():
    def __init__(self):
        self.i = 0


def parse_input(filename):
    data = [x.strip() for x in open(filename, 'r').readlines()]


def solve_case(case):
    return 1


if __name__ == "__main__":
    import sys
    cases = parse_input(sys.argv[1])
    outfile_name = sys.argv[1].split('.')[0] + ".out"
    fout = open(outfile_name, 'w')
    case_num = 1
    for case in cases:
        result = solve_case(case)
        message = "Case %d: %s" % (case_num, result)
        fout.write("%s\n" % message)
        print message
        case_num += 1
