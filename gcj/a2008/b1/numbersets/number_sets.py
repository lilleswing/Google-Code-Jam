import util.maths as maths
from util.disjoint_set import UnionFind

primes = maths.sieve(1000001)


class Case():
    def __init__(self, _lower, _upper, _min_prime):
        self.lower = _lower
        self.upper = _upper
        self.min_prime = _min_prime
        self.interval = range(self.lower, self.upper+1)

    def get_interior_values(self, prime):
        values = list()
        start = case.lower/prime
        val = start * prime
        while val <= case.upper:
            if self.inside(val):
                values.append(val)
            val += prime
        return values

    def inside(self, i):
        return self.lower <= i <= self.upper


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
    global primes
    upper_bound = (case.upper - case.lower)
    case_primes = filter(lambda x: case.min_prime <= x <= upper_bound, primes)
    disjoint_set = UnionFind()
    disjoint_set.insert_objects(case.interval)

    for prime in case_primes:
        interior_values = case.get_interior_values(prime)
        if len(interior_values) <= 1:
            continue
        if len(interior_values) == 0:
            break
        for value in interior_values[1:]:
            disjoint_set.union(interior_values[0], value)

    return len(disjoint_set)

if __name__ == "__main__":
    import sys

    cases = parse_input(sys.argv[1])
    outfile_name = sys.argv[1].split('.')[0] + ".out"
    fout = open(outfile_name, 'w')
    case_num = 1
    for case in cases:
        result = solve_case(case)
        message = "Case #%d: %s" % (case_num, result)
        print(message)
        fout.write("%s\n" % message)
        case_num += 1
    fout.close()
