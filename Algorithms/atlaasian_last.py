#Ruichen Teng / tengr / 678693
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
#Tiehua Zhang / tiehuaz / 645227
#Chao Lin / clin2 / 658584
#Xueqiang Ma/ xueqiangm/ 668190
#Zheng Zheng/ zzheng2/ 651384
    # -*- coding: utf-8 -*-
#Ruichen Teng / tengr / 678693
#Tiehua Zhang / tiehuaz / 645227
#Chao Lin / clin2 / 658584
#Xueqiang Ma/ xueqiangm/ 668190
#Zheng Zheng/ zzheng2/ 651384

def check_segment(input, start, end):
    dic = {}
    if input[start] != '|':
        dic[-1] = ''
        return dic
    cleanup = ''
    i = start + 1
    while i <= end:
        if input[i] == '~':
            if i + 1 < end :
                if input[i+1] == '~':
                    cleanup += chr(ord(' ') - 1)
                    i += 2
                elif input[i+1] == '|':
                    cleanup += chr(ord(' ') - 2)
                    i += 2
                elif input[i+1] == 'n' and cleanup[-1] == '|':
                    dic[i-1] = cleanup[:-1]
                    return dic
                else:
                    dic[-1] = ''
                    return dic
            else:
                dic[-1] = ''
                return dic
        elif ord(input[i]) < ord(' ') or ord(input[i]) > ord('~'):
            dic[-1] = ''
            return dic
        else:
            cleanup += input[i]
            i += 1
    if cleanup[-1] == '|':
        dic[end] = cleanup[:-1]
    else:
        dic[-1] = ''
    return dic

def validate(input):
    try:
        input.decode('ascii')
    except UnicodeDecodeError:
        return '0:0:0:format_error'
    else:
        fl_start = input.find('|')
        fl_res = check_segment(input, fl_start, len(input))
        if -1 in fl_res:
            return '0:0:0:format_error'
        else:
            fl_end = fl_res.keys()[0]
            first_line = fl_res.values()[0]
            fnms = first_line.split('|')
            ca = {}
            for ele in fnms:
                if len(ele.strip().lower()) == 0:
                    return '0:0:0:format_error'
                elif ele.strip().lower() in ca:
                    return '0:0:0:format_error'
                else:
                    ca[ele.strip().lower()] = 1
            nmfnms = len(fnms)
            last_name = fnms[-1].strip().replace(chr(ord(' ') - 1),'~').replace(chr(ord(' ') - 2),'|')
            records = 0
            nmfds = 0
            nonempty = 0
            names = {}
            pos = fl_end + 3
            stop = input.rfind('|')
            while pos <= stop:
                res = check_segment(input, pos, stop)
                if -1 in res:
                    return '0:0:0:format_error'
                pos = res.keys()[0] + 3
                line = res.values()[0]
                print line
                fields = line.split('|')
                name = fields[0].strip().lower()
                if len(name) == 0 or name in names:
                    return '0:0:0:format_error'
                else:
                    names[name] = 1
                for f in fields:
                    if len(f.strip()) > 0:
                        nonempty += 1
                records += 1
                nmfds = max(nmfds, len(fields))
            nmfds = max(nmfds, nmfnms)
            empty = records * nmfds - nonempty
            extra = nmfds - nmfnms
            if extra > 0:
                last_name += ('_'+str(extra))
            return str(records) + ":"+str(nmfds) + ":" + str(empty) + ":" + last_name
input = "|name|address|~n|Annie|a@b.com|sdsf.com|~n|brian|sdsdd|~n"
input = "~n~n~n|name|address|b|a|adress|~n|Patrick|patrick@test.com|pat@test.com|~n|Annie|~|Annie@test.com|~n"
#input = "|name|address|b|a|adress|~n|Patrick|patrick@test.com|pat@test.com|~n|Flügel|~|Annie@test.com|~n"


print validate(input)