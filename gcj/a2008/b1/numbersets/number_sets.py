class Case():
    def __init__(self, _lower, _upper, _min_prime):
        self.lower = _lower
        self.upper = _upper
        self.min_prime = _min_prime
        self.sieve = list()

    def sieve(self):
        """ Creates a list from lower-upper inclusive
        and finds the factors """
        self.sieve = [ set() ] * (self.upper - self.lower)



def parse_input(filename):
    data = [x.strip() for x in open(filename, 'r').readlines()]
    num_cases = int(data.pop(0))
    cases = list()
    for case in xrange(num_cases):
        vals = data.pop(0).strip().split(' ')
        lower = int(vals[0])
        upper = int(vals[1])
        min_prime = int(vals[2])
        cases.append(Case(lower, upper, min_prime))
    return cases


def solve_case(case):
    return 1


if __name__ == "__main__":
    import sys
    cases = parse_input(sys.argv[1])
    case_num = 1
    for case in cases:
        result = solve_case(case)
        print("Case %d: %s" % (case_num, result))
        case_num += 1
