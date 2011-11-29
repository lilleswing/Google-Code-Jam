def arrstr2arrnum(arr):
    for i in xrange(0, len(arr)):
        arr[i] = int(arr[i])
    return arr
words = open("words.txt", 'r').readlines()
for i in xrange(0,len(words)):
    words[i] = words[i].strip()
    
cases = int(words[0])
index = 1;
for i in xrange(0, cases):
    arr1 = arrstr2arrnum(words[index+1].split(" "));
    arr2 = arrstr2arrnum(words[index+2].split(" "));
    arr1.sort();
    arr2.sort();
    arr2.reverse();
    sum = 0
    for j in xrange(0, len(arr1)):
        #print("%s,%s" % (arr1[j], arr2[j]))
        sum += arr1[j]*arr2[j]
    index += 3
    print("Case #%s: %s" % (i+1, sum))