__author__ = 'leswing'


class DisjointSet():
    def __init__(self, alist):
        self.rep = {x: x for x in alist}

    def find(self, p):
        if self.rep[p] != p:
            self.rep[p] = self.find(self.rep[p])
        return self.rep[p]

    def union(self, p, q):
        self.rep[self.find(p)] = self.find(q)

    def __len__(self):
        return len(set([self.find(x) for x in self.rep.values()]))
