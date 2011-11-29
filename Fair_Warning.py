def gcd(a,b):
    while(b != 0):
        t = b
        b = a % b
        a = t
    return a

def Gcd(a, b):
  if b == 0:
    return a
  return Gcd(b, a % b)

def Solve(L):
  y = L[0]
  L1 = [abs(x - y) for x in L]
  g = reduce(Gcd, L1)
  if y % g == 0:
    return 0
  else:
    return g - (y % g)
        
input = open("in.txt",'r').readlines()
output = open('out.txt','w')
cases = int(input[0])
index = 1
for case in xrange(1,cases+1):
    varr = input[index].split(" ")[1:]
    index += 1
    varr = map(int,varr)
    ans = Solve(varr)
    
    print("Case #%s: %s" % (case,ans))