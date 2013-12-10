import random
from fractions import gcd


def brent(N):
    """Fast finding of a single factor"""
    if N % 2 == 0:
        return 2
    y, c, m = random.randint(1, N - 1), random.randint(1, N - 1), random.randint(1, N - 1)
    g, r, q = 1, 1, 1
    while g == 1:
        x = y
        for i in xrange(r):
            y = ((y * y) % N + c) % N
        k = 0
        while k < r and g == 1:
            ys = y
            for i in xrange(min(m, r - k)):
                y = ((y * y) % N + c) % N
                q = q * (abs(x - y)) % N
            g = gcd(q, N)
            k = k + m
        r *= 2
        if g == N:
            while True:
                ys = ((ys * ys) % N + c) % N
                g = gcd(abs(x - ys), N)
                if g > 1:
                    break

        return g


def factor(n):
    k = 2
    factors = set()
    while n != 1:
        while n % k == 0:
            n /= k
            factors.add(k)
        k += 1
    return factors


if __name__ == "__main__":
    print(brent(100005))
