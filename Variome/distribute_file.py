import os
import re
import shutil
#Convert Variome Annotations to ASM-RE annotations
#Relation cast as a binding event
#"Fake Trigger" set as the first argument of a biological event

dirn = "workdir"

data_dir = "/Users/ruichen/Documents/COMP90055/variome_annotation_corpus/data/"
train_dir = "/Users/ruichen/Documents/RE/asm-re/" + dirn + "/training/"
test_dir = "/Users/ruichen/Documents/RE/asm-re/" + dirn + "/test/"
tune_dir = "/Users/ruichen/Documents/RE/asm-re/" + dirn + "/tuning/"
f_names = [f for f in os.listdir(data_dir) if os.path.isfile(data_dir + f) and os.path.getsize(data_dir + f) > 0 ]
prefix_set = []
for f_name in f_names:
    prefix = f_name.strip()[:-3]
    if prefix not in prefix_set:
        if prefix + "txt" in f_names and prefix + "ann" in f_names:
            prefix_set.append(prefix)

def generate_docs(some_set, some_dir):
    for prefix in some_set:       
        shutil.copy2(data_dir + prefix + "txt", some_dir + prefix + "txt")
        has_trigger_set = []
        rel_trigger_set = []
        theme_set = []
        lines = []
        with open(data_dir + prefix + "ann", "r") as ann_f:
            lines = ann_f.readlines()
        
#         for line in lines:
#             if line[0] == 'R':
#                 #print line
#                 arg1 = re.findall(r":(T\w+)",line)[0].strip()
#                 theme = re.findall(r":(T\w+)",line)[1].strip()
        a1_f = open(some_dir + prefix + "a1", "w")
        a2_f = open(some_dir + prefix + "a2", "w")

        for pline in lines:
            line = pline
            if line[0] == 'R':
                line = 'E' + line[1:]
#                 ar1 = re.findall(r"(Arg\d+)",line)[0].strip()
#                 ar2 = re.findall(r"(Arg\d+)",line)[1].strip()
                if "has" in line:
                    line = line.replace("has Arg1", "Binding:T1 Theme")
                    line = line.replace("Arg2", "Theme2")
                elif "relatedTo" in line:
                    line = line.replace("relatedTo Arg1", "Binding:T1 Theme")
                    line = line.replace("Arg1", "Theme2")
                    line = line.replace("Arg2", "Theme2")
                line = line.replace("Theme1", "Theme2")
                #nextarg = re.findall(r"(Arg\d+)",line)[0].strip()
                #line = line.replace(nextarg, "Theme2:")
                #line = line.replace("Arg2:","Theme2:")
                m = re.findall(":T\d+(_\w+)", line)
                for mm in m:
                    line = line.replace(mm, str(len(mm)))
                n = re.findall("E\d+(_\S+)", line)
                for nn in n:
                    line = line.replace(nn, str(len(nn)))
                a2_f.write(line)
            else:
                line_split = line.split()
                entity = line_split[0].strip()
                l = entity + "\tProtein " + line_split[2] + " " + line_split[3] + "\t" + line_split[4] + "\n"
                m = re.findall("T\d+(_\w+)", l)
                for mm in m:
                    l = l.replace(mm, str(len(mm)))
                a1_f.write(l)
        a1_f.close()
        a2_f.close()
train_set = prefix_set[2:3]
tune_set= prefix_set[2:3]
test_set = prefix_set[2:3]
print len(prefix_set)

generate_docs(train_set, train_dir)
generate_docs(tune_set, tune_dir)
generate_docs(test_set, test_dir)
            