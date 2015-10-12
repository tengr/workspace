with open('/Users/ruichen/Documents/COMP90055/variome_annotation_corpus/data', 'r') as f:
    for line in f:
        print line + "\n"
        for i in range(len(line)):
            print i