import util.maths as maths


class Node:
    def __init__(self, _value, _before=None, _after=None):
        self.value = _value
        self.before = _before
        self.after = _after


class Case():
    def __init__(self, _lower, _upper, _min_prime):
        self.lower = _lower
        self.upper = _upper
        self.min_prime = _min_prime
        self.sieve = Node(None)

    def sieve_factors(self):
        """ Creates a Linked list from lower-upper inclusive
        and finds the factors """
        self.sieve = [set()] * (self.upper - self.lower)
        _range = self.upper - self.lower
        for i in xrange(_range + 1):  # Inclusive of upper
            factors = maths.factor(self.lower + i)
            if i == 0:
                self.sieve = Node(factors)
                node = self.sieve
            else:
                new_node = Node(factors)
                new_node.before = node
                node.after = new_node
                node = new_node

    def jiggle(self):
        node = self.sieve
        while node.after is not None:
            self.jiggle_one(node)
            node = node.after

    def jiggle_one(self, node):
        forward = True
        checks = 0
        while checks < 2:
            is_merged = self.merge(node, forward)
            if is_merged:
                checks = 0
            else:
                checks += 1
                forward = not forward

    def merge(self, node1, forward):
        if forward:
            node2 = node1.after
        else:
            node2 = node1.before

        if node2 is None:
            return False

        set1 = node1.value
        set2 = node2.value
        intersection = set1.intersection(set2)
        if len(intersection) == 0:
            return False
        biggest = max(set1.intersection(set2))
        if biggest < self.min_prime:
            return False
        set3 = set1.union(set2)
        node1.value = set3
        if forward:
            node1.after = node2.after
        else:
            node1.before = node2.before
        return True

    def get_sieve_size(self):
        count = 1
        node = self.sieve
        while node.after is not None:
            count += 1
            node = node.after
        return count


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
    case.sieve_factors()
    case.jiggle()
    return case.get_sieve_size()


if __name__ == "__main__":
    import sys

    cases = parse_input(sys.argv[1])
    case_num = 1
    for case in cases:
        result = solve_case(case)
        print("Case %d: %s" % (case_num, result))
        case_num += 1
