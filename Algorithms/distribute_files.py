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
import shutil

data_dir = "/Users/ruichen/Documents/COMP90055/variome_annotation_corpus/data/"
train_dir = "/Users/ruichen/Documents/testNLP/asm-re/workdir/training/"
test_dir = "/Users/ruichen/Documents/testNLP/asm-re/workdir/test/"
tune_dir = "/Users/ruichen/Documents/testNLP/asm-re/workdir/tuning/"
f_names = [f for f in os.listdir(data_dir) if os.path.isfile(data_dir + f) and os.path.getsize(data_dir + f) > 0 ]
prefix_set = []
for f_name in f_names:
    prefix = f_name.strip()[:-3]
    if prefix not in prefix_set:
        if prefix + "txt" in f_names and prefix + "ann" in f_names:
            prefix_set.append(prefix)

# print len(f_names)
#print len(prefix_set)
# for prefix in prefix_set:
#     if prefix + "txt" in f_names and prefix + "ann" not in f_names:
#         print prefix
#     elif prefix + "ann" in f_names and prefix + "txt" not in f_names:
#         print prefix
def generate_docs(some_set, some_dir):
    for prefix in some_set:
#         lines = []
#         with open(data_dir + prefix + "txt", "r") as read_f:
#             lines = read_f.readlines()[3:]
#             #lines = read_f.readlines()
#         with open(some_dir + prefix + "txt", "w") as write_f:
#             for l in lines:
#                 write_f.write(l)        
        shutil.copy2(data_dir + prefix + "txt", some_dir + prefix + "txt")
        has_trigger_set = []
        rel_trigger_set = []
        theme_set = []
        lines = []
        with open(data_dir + prefix + "ann", "r") as ann_f:
            lines = ann_f.readlines()
        
        for line in lines:
            if line[0] == 'R':
                #print line
                trigger = re.findall(r":(T\w+)",line)[0].strip()
                theme = re.findall(r":(T\w+)",line)[1].strip()
                theme_set.append(theme)
                if "has" in line:
                    has_trigger_set.append(trigger)
                else:
                    rel_trigger_set.append(trigger)
#                 if "R0_T9_T3_cancers" in line: 
#                     print line
#                     print rel_trigger_set
#                     print has_trigger_set
#                     print prefix
#                     break            
        a1_f = open(some_dir + prefix + "a1", "w")
        a2_f = open(some_dir + prefix + "a2", "w")

        for pline in lines:
            #line = re.sub(r'_\w+','', pline)
            line = pline
            if line[0] == 'R':
                line = 'E' + line[1:]
                line = line.replace("has Arg1", "Gene_expression")
                line = line.replace("relatedTo Arg1", "Protein_catabolism")
                line = line.replace("Arg2","Theme")
                m = re.findall(":T\d+(_\w+)", line)
#                 if len(m) > 0:
#                     line = line.replace(m.group(1), str(len(m.group(1))))
                for mm in m:
                    line = line.replace(mm, str(len(mm)))
#                 m = re.search("E\d+(_\w+)", line)
#                 if m is not None: 
#                     line = line.replace(m.group(1), str(len(m.group(1))))               
                #line = re.sub("T\d+(_\w+)","200",line)
                #line = re.sub("R\d+(_\w+)", "300", line)
                a2_f.write(line)
#                 if "T5_merge" in line:
#                     print m.group(1)
#                     print line
#                     break
            else:
                line_split = line.split()
                entity = line_split[0].strip()
                if entity in has_trigger_set:
                    l = entity + "\tGene_expression " + line_split[2] + " " + line_split[3] + "\t" + line_split[4] + "\n"
                    m = re.findall("T\d+(_\w+)", l)
                    for mm in m:
                        l = l.replace(mm, str(len(mm)))
                    a2_f.write(l)
                if entity in rel_trigger_set:
                    l = entity + "\tProtein_catabolism " + line_split[2] + " " + line_split[3] + "\t" + line_split[4] + "\n"
                    #entity = re.sub(r"_\w+", "01", entity)
                    m = re.findall("T\d+(_\w+)", l)
                    for mm in m:
                        l = l.replace(mm, str(len(mm)))
                    a2_f.write(l)
                if entity in theme_set:
                    #entity = re.sub(r"_\w+", "01", entity)
                    #a2_f.write(entity + "\tProtein " + line_split[2] + " " + line_split[3] + "\t" + line_split[4] + "\n")
                    l = entity + "\tProtein " + line_split[2] + " " + line_split[3] + "\t" + line_split[4] + "\n"
                    m = re.findall("T\d+(_\w+)", l)
                    for mm in m:
                        l = l.replace(mm, str(len(mm)))
                    a1_f.write(l)
                #a1_f.write(entity + "\tProtein " + line_split[2] + " " + line_split[3] + "\t" + line_split[4] + "\n")
        a1_f.close()
        a2_f.close()
        #print has_trigger_set
train_set = prefix_set[:80]
tune_set= prefix_set[:80]
test_set = prefix_set[80:]
print len(prefix_set)
#shutil.copy2('/dir/file.ext', '/new/dir/newname.ext')

generate_docs(train_set, train_dir)
generate_docs(tune_set, tune_dir)
generate_docs(test_set, test_dir)
#[os.remove(os.path.join(test_dir,f)) for f in os.listdir(test_dir) if f.endswith(".a2")]

          
# dir = "/Users/ruichen/Documents/testNLP/asm-re/testdir/tuning/"
# newlines = []
# with open(dir + "biomed.a1", 'r') as f:
#     lines = f.readlines()
#     for line in lines:
#         eles = line.split()
#         if len(eles) > 3:
#             newline = eles[0] + "\t" + eles[1] + " " + eles[2] + " " + eles[3] + "\t" + eles[4] + "\n"
#             print newline
#             newlines.append(newline)
# with open(dir + "biomed.a1", 'w') as f:
#     for newline in newlines:
#         print newline
#         f.write(newline)
            