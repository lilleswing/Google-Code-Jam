class Case():
    def __init__(self, _engines, _queries):
        self.engines = _engines
        self.queries = _queries


def parse_input(filename):
    data = [ x.strip() for x in open(filename, 'r').readlines() ]
    num_cases = int(data.pop(0))
    cases = list()
    for case in xrange(0, num_cases):
        engines = list()
        queries = list()

        num_engines = int(data.pop(0))
        for i in xrange(0, num_engines):
            engine = data.pop(0)
            engines.append(engine)

        num_queries = int(data.pop(0))
        for i in xrange(0, num_queries):
            query = data.pop(0)
            queries.append(query)
        cases.append(Case(engines, queries))
    return cases


def num_switches(case):
    switches = 0
    engines = case.engines
    queries = case.queries

    round_engines = set()
    for query in queries:
        round_engines.add(query)
        if(len(round_engines) == len(engines)):
            switches += 1
            round_engines = set()
            round_engines.add(query)
    return switches


if __name__ == "__main__":
    import sys
    cases = parse_input(sys.argv[1])
    casenum = 1
    for case in cases:
        switches = num_switches(case)
        print("Case %d: %d" % (casenum, switches))
        casenum += 1

