import gcj_util
#need to make more efficient


#flip to customer's malted choice, if customer doesn't have a malted choice return impossible
def fixMilkshakes(customer,milkshakes):
    #print customer.count(1)
    #print customer
    if(customer.count(1) > 0):
        spot = customer.index(1)
        milkshakes[spot] = 1
        #print "Fixed"
        return milkshakes
    else:
        return "IMPOSSIBLE"
    
def satisfied(customer, milkshakes):
    #print("%s:%s" % (customer,milkshakes))
    for i in xrange(0, len(milkshakes)):
        if(customer[i] == milkshakes[i]):
            return True
    return False

def solve(customers, milkshakes):
    index = 0
    while(index < len(customers)):
        customer = customers[index]
        if(not satisfied(customer,milkshakes)):
            #print("Not Satisfied %s:%s" % (customer, milkshakes))
            index = 0
            milkshakes = fixMilkshakes(customer,milkshakes)
            if(milkshakes == "IMPOSSIBLE"):
                return "IMPOSSIBLE"
        else:
            index += 1
                
    return milkshakes

[cases, input] = gcj_util.ReadInput("in.txt")
index = 0
for case in xrange(1, cases + 1):
    N = int(input[index])
    index += 1
    M = int(input[index])
    index += 1
    customers = []
    for i in xrange(0, M):
        customer = [-1] * N
        
        L = map(int, input[index].split(" "))
        #print L
        for j in xrange(0, L[0]):
            customer[L[j * 2 + 1]-1] = L[j * 2 + 2]
        index += 1
        customers.append(customer)
    #print customers
    batch = solve(customers,[0]*N)
    batch = str(batch).replace('[','').replace(']', '').replace(',','')    
    print "Case #%s: %s" % (case,batch)
