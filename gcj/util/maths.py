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


def sieve(n):
    """Return all primes <= n."""
    np1 = n + 1
    s = range(np1)
    s[1] = 0
    sqrtn = int(round(n ** 0.5))
    for i in range(2, sqrtn + 1):
        if s[i]:
            s[i * i: np1: i] = [0] * len(xrange(i * i, np1, i))
    return filter(None, s)


def prime_factor(n):
    """Returns a set of prime factors for N using trial division"""
    k = 2
    factors = set()
    while n != 1:
        while n % k == 0:
            n /= k
            factors.add(k)
        k += 1
    return factors


if __name__ == "__main__":
    print sieve(1000000)
