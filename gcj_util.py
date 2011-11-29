def ReadInput(filename):
    input = open(filename, 'r').readlines()
    input = map(str.strip, input)
    cases = int(input[0])
    input = input[1:]
    return [cases, input]