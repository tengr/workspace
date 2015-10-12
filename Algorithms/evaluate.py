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
import os
#Ruichen Teng / tengr / 678693
#Tiehua Zhang / tiehuaz / 645227
#Chao Lin / clin2 / 658584
#Xueqiang Ma/ xueqiangm/ 668190
#Zheng Zheng/ zzheng2/ 651384
import re
from itertools import count
from twisted.scripts.tkunzip import countPys
predict_dir = "/Users/ruichen/Documents/testNLP/asm-re/workdir/test/predictions/"
gold_dir = "/Users/ruichen/Documents/testNLP/asm-re/workdir/test/"

predict_f_names = [f for f in os.listdir(predict_dir) if os.path.isfile(predict_dir + f) and os.path.getsize(predict_dir + f) > 0 ]
gold_f_names = [f for f in os.listdir(gold_dir) if os.path.isfile(gold_dir + f) and os.path.getsize(gold_dir + f) > 0 ]

pname = [ele for ele in predict_f_names if 'a2' in ele][0]
gname = [ele for ele in gold_f_names if 'a2' in ele][0]
# print pname
# print gname
plines = open(predict_dir + pname, 'r').readlines()
glines = open(gold_dir + gname, 'r').readlines()
# ent_dic = {}
# a1f = open(gold_dir + gname.replace('a2','a1'), 'r')
# for ll in a1f:
     
print predict_dir + pname
print gold_dir + gname
print len(plines)
print len(glines)
trigger_dic = {}
gevent_dic = {}
pevent_dic = {}
 
for line in glines:
    if line[0] == 'T':
        trigger_dic[line.split()[0]] = line.split()[2] + ' ' + line.split()[3]
    else:
        tr = re.findall(":(T\w+)", line)[0]
        th = re.findall(":(T\w+)", line)[1]
        gevent_dic[trigger_dic[tr] + ' ' + th] = 1


for line in plines:
    if line[0] == 'T':
        trigger_dic[line.split()[0]] = line.split()[2] + ' ' + line.split()[3]
    else:
        tr = re.findall(":(T\w+)", line)[0]
        th = re.findall(":(T\w+)", line)[1]
        pevent_dic[trigger_dic[tr] + ' ' + th] = 1
count = 0
for k,v in pevent_dic.iteritems():
    if k in gevent_dic:
        count += 1
print count
print len(pevent_dic)
print len(gevent_dic)
#count = 0

