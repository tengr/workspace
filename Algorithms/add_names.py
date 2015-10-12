# Import the os module, for the os.walk function
import os
 
# Set the directory you want to start from
rootDir = '/Users/ruichen/Documents/workspace/Algorithms/'
added_string = '''#Ruichen Teng / tengr / 678693
#Tiehua Zhang / tiehuaz / 645227
#Chao Lin / clin2 / 658584
#Xueqiang Ma/ xueqiangm/ 668190
#Zheng Zheng/ zzheng2/ 651384
'''
for dirName, subdirList, fileList in os.walk(rootDir):
    #print('Found directory: %s' % dirName)
    for fname in fileList:
        print dirName + fname
        if os.path.isfile(dirName + fname) and os.path.getsize(dirName + fname) > 0:
            #print('\t%s' % fname)
            #lines = []
            lines =  open(dirName + fname, "r").readlines()
#                 print dirName + fname
#                 lines = f.readlines()
#                 print lines
            with open(dirName + fname, "w") as f:
                if lines[0][0] == '#':
                    f.write(lines[0])
                    f.write(added_string)
                    for line in lines[1:]:
                        f.write(line)
                else:
                    f.write(added_string)
                    for line in lines:
                        f.write(line)
        else:
            print "not file"


            