input = open("in.txt", 'r').readlines()
cases = int(input[0])
index = 1;
for case in xrange(0,cases):
    N = int(input[index]);
    index += 1
    wires = []
    for i in xrange(0, N):
        points = input[index].strip().split(" ");
        points[0] = int(points[0])
        points[1] = int(points[1])
        wires.append(points)
        index += 1
    sum = 0
    for i in xrange(0, len(wires)):
        for j in xrange(i+1, len(wires)):
            a = wires[i][0]-wires[j][0];
            b = wires[i][1]-wires[j][1];
            if(a * b < 0):
                sum += 1
    print("Case #%s: %s" % (case+1, sum))
        