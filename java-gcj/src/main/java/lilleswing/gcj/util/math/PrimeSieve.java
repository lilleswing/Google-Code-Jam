package lilleswing.gcj.util.math;

import com.google.common.collect.Lists;

import java.util.List;

public class PrimeSieve {
    public static List<Long> sieve(int N) {
        // initially assume all integers are prime
        boolean[] isPrime = new boolean[N + 1];
        for (int i = 2; i <= N; i++) {
            isPrime[i] = true;
        }

        // mark non-primes <= N using Sieve of Eratosthenes
        for (int i = 2; i * i <= N; i++) {

            // if i is prime, then mark multiples of i as nonprime
            // suffices to consider mutiples i, i+1, ..., N/i
            if (isPrime[i]) {
                for (int j = i; i * j <= N; j++) {
                    isPrime[i * j] = false;
                }
            }
        }

        // count primes
        final List<Long> primes = Lists.newArrayList();
        for (int i = 2; i <= N; i++) {
            if (isPrime[i])
                primes.add(Long.valueOf(i));
        }
        return primes;
    }
}
