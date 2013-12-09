class Case():
    def __init__(self):
        self.i = 0

def parse_input(filename):
    data = [ x.strip() for x in open(filename, 'r').readlines() ]


if __name__ == "__main__":
    import sys
    cases = parse_input(sys.arv[1])
    case_num = 1
    for case in cases:
        result = solve_case(case)
        print("Case %d: %s" % result)
        case_num += 1
