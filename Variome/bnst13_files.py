import os
import shutil

#Convert Variome Annotations to ASM-RE annotations

pwd = "/Users/ruichen/Documents/COMP90055/"
train_dir = "BioNLP-ST_2011_genia_train_data_rev1/"
dev_dir = "BioNLP-ST_2011_genia_devel_data_rev1/"
test_dir = "BioNLP-ST_2011_genia_test_data/"
train_dest = "/Users/ruichen/Documents/NLP/asm-re/testdir/training/"
test_dest = "/Users/ruichen/Documents/NLP/asm-re/testdir/test/"
tune_dest = "/Users/ruichen/Documents/NLP/asm-re/testdir/tuning/"
def get_fnames(data_dir):
    f_names = [f for f in os.listdir(data_dir) if os.path.isfile(data_dir + f) and os.path.getsize(data_dir + f) > 0 and "a1" in f]
    return f_names

t = get_fnames(pwd + train_dir)
d = get_fnames(pwd + dev_dir)
te = get_fnames(pwd + test_dir)
#print t
offset = 2
for tt in t:
    #print tt.strip()[:-offset]
    shutil.copy2(pwd + train_dir + tt.strip()[:-offset] + "txt", train_dest + tt.strip()[:-offset] + "txt")
    #print tt.strip()
    shutil.copy2(pwd + train_dir + tt.strip()[:-offset] + "a1", train_dest + tt.strip()[:-offset] + "a1")
    shutil.copy2(pwd + train_dir + tt.strip()[:-offset] + "a2", train_dest + tt.strip()[:-offset] + "a2")

for tt in d:
    shutil.copy2(pwd + dev_dir + tt.strip()[:-offset] + "txt", tune_dest + tt.strip()[:-offset] + "txt")
    shutil.copy2(pwd + dev_dir + tt.strip()[:-offset] + "a1", tune_dest + tt.strip()[:-offset] + "a1")
    shutil.copy2(pwd + dev_dir + tt.strip()[:-offset] + "a2", tune_dest + tt.strip()[:-offset] + "a2")
    
for tt in te:
    shutil.copy2(pwd + test_dir + tt.strip()[:-offset] + "txt", test_dest + tt.strip()[:-offset] + "txt")
    shutil.copy2(pwd + test_dir + tt.strip()[:-offset] + "a1", test_dest + tt.strip()[:-offset] + "a1")

print "done"