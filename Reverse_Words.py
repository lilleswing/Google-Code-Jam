def arr2str(arr):
	s = arr[0]
	i = 1
	while(i < len(arr)):
		s = "%s %s" % (s,arr[i]);
		i += 1
	return s
words = open("words.txt", 'r').readlines()
i = 1
while(i < len(words)):
	words[i] = words[i].strip()
	words[i] = words[i].split(' ');
	words[i].reverse()
	print "Case #%s: %s" % (i, arr2str(words[i]))
	i+=1
	