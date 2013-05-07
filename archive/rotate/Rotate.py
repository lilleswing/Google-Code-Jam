def shift(row):
    n = len(row)
    while(row.count('.') >= 1):
        row.remove('.')
    row.reverse()
    while(len(row) != n):
        row.append('.')
    row.reverse()
    return row

def checkHelper(board,k, i,j,dirx,diry):
    if(dirx==0 and diry==0):
        return "Neither"
    l = list()
    for num in xrange(0,k):
        x = i + dirx*num
        y = j + diry*num
        if(x >= 0 and x < len(board) and y >= 0 and y < len(board[0])):
            l.append(board[x][y])
        else:
            return "Neither"
    if(l.count('B')==k):
        return"blue"
    if(l.count('R')==k):
        return "red"
    

def checkwin(boardk, k):
    width = len(board)
    height = len(board[0])
    blue = False
    red = False
    for i in xrange(0,width):
        for j in xrange(0,height):
            for dirx in xrange(-1,2,1):
                for diry in xrange(0,2,1):
                    sol = checkHelper(board,k,i,j,dirx,diry)
                    if(sol == "blue"):
                        blue = True;
                    if(sol =="red"):
                        red = True;
    if(blue and red):
            return "Both"
    if(blue):
            return "Blue"
    if(red):
            return "Red"
    return "Neither"
                        
    
    
input = open("in.txt", 'r').readlines()

cases = int(input[0])
index = 1
for case in xrange(1, cases+1):
    #print("case %s" % case)
    varr = input[index].strip().split(" ")
    index += 1
    H = int(varr[0]);
    K = int(varr[1])
    board = [];
    for i in xrange(0,H):
        row = list(input[index].strip())
        index += 1
        row = shift(row)
        board.append(row)
        #print(row)
    sol = checkwin(board,K)
    print("Case #%s: %s" % (case, sol))
        
        