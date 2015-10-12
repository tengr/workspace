#ord('~')
#Ruichen Teng / tengr / 678693
#Tiehua Zhang / tiehuaz / 645227
#Chao Lin / clin2 / 658584
#Xueqiang Ma/ xueqiangm/ 668190
#Zheng Zheng/ zzheng2/ 651384
#Ruichen Teng / tengr / 678693
#Tiehua Zhang / tiehuaz / 645227
#Chao Lin / clin2 / 658584
#Xueqiang Ma/ xueqiangm/ 668190
#Zheng Zheng/ zzheng2/ 651384
#Ruichen Teng / tengr / 678693
#Tiehua Zhang / tiehuaz / 645227
#Chao Lin / clin2 / 658584
#Xueqiang Ma/ xueqiangm/ 668190
#Zheng Zheng/ zzheng2/ 651384
#Ruichen Teng / tengr / 678693
#Tiehua Zhang / tiehuaz / 645227
#Chao Lin / clin2 / 658584
#Xueqiang Ma/ xueqiangm/ 668190
#Zheng Zheng/ zzheng2/ 651384
#Ruichen Teng / tengr / 678693
#Tiehua Zhang / tiehuaz / 645227
#Chao Lin / clin2 / 658584
#Xueqiang Ma/ xueqiangm/ 668190
#Zheng Zheng/ zzheng2/ 651384
def solution(input):
    if input[0] != '|':
        return "0:0:0:format_error"
    pos = input.find('|~n',1)
    if pos < 0:
        return "0:0:0:format_error"
    
    checkline = input[0:pos+1]
    checkline.replace('~~','')
    checkline.replace('~|','')
    for i in range(len(checkline)):
        if ord(checkline[i]) < ord(' ') or ord(checkline[i]) >= ord('~'):
            return "0:0:0:format_error" 
    line = input[1:pos]
    line.replace('~|', '~'+str(chr(ord(' ')-1)))        
    fnms = line.split('|')
    nmfnms = len(fnms)
    last_name = fnms[-1]

    records = 0
    nmfds = 0
    nonempty = 0
    names = []
    while True:
        start = pos + 4
        pos = input.find('|~n', start)
        if pos < 0:
            if start >= len(input):
                break
            else:
                pos = len(input)
        else:
            records += 1
            checkline = input[start-1:pos+1]
            if checkline[0] != '|':
                return '0:0:0:format_error'
                checkline.replace('~~','')
                checkline.replae('~|','')
            for i in range(len(checkline)):
                if ord(checkline[i]) < ord(' ') or ord(checkline[i]) >= ord('~'):
                    return "0:0:0:format_error" 
            #no invalid characters
            line = input[start:pos]
            line.replace('~|', '~'+str(chr(ord(' ')-1)))
            fields = line.split('|')
            if len(fields[0]) == 0 or fields[0] in names:
                return "0:0:0:format_error"
            else:
                #print(fields[0])
                names.append(fields[0])
            for f in fields:
                if len(f) > 0:
                    nonempty += 1
          
            nmfds = max(nmfds, len(fields))
    empty = records * nmfds - nonempty
    extra = nmfds - nmfnms
    if extra > 0:
        last_name += ('_'+str(extra))
    
    return str(records) + ":"+str(nmfds) + ":" + str(empty) + ":" + last_name

input = "|name|address|~n|Annie|a@b.com|sdsf.com|~n|brian|sdsdd|~n"
input = "|name|address|~n|Patrick|patrick@test.com|pat@test.com|~n|Annie|~|Annie@test.com|~n"

print solution(input)