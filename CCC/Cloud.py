import re
import sys
import time
import datetime
import math
from collections import Counter
from mpi4py import MPI


def search(query, text_list):
    
    result = []
    text = ''
    for element in text_list:
        text = text + element
    text = text.lower()               
    
    
    pattern = re.findall(query,text)
    counts = Counter(pattern)
    result.append(counts) 

    
    term_1 = re.compile(r"@[\w]+").findall(text)
    counts_1 = Counter(term_1)
    result.append(counts_1) 
    
     
    term_2 = re.compile(r"#[\w]+").findall(text)
    counts_2 = Counter(term_2)
    result.append(counts_2)        
    
    return result



comm = MPI.
num_procs = comm.Get_size()
rank = comm.Get_rank()


if rank == 0:
    start = datetime.datetime.now()
    souce_file = open("/home/zzheng2/mydata/mydata/myMiniTwitter.csv",'r',encoding='UTF-8')
        
    query = "tomorrow"
         
    if len(sys.argv) == 2:
        query = sys.argv[1]
                     
    query = re.sub('[^\w]+',' ',query).lower()
    data = souce_file.readlines()
    interval = int(math.ceil(data.__len__() / float(num_procs)))     
             
    for i in range (num_procs):
        if i!=0:
            comm.send(data[interval * i:interval * (i + 1)], dest=i)
    one_result = search(query,data[0:interval])
          
    for i in range(num_procs):
        total_result = []
        my_result = []
        if i==0:
            total_result = one_result
        else:
            my_result = comm.recv(source=i)

        
        my_result.append((one_result[1]+my_result[1]).most_common(10))
        my_result.append((one_result[2]+my_result[2]).most_common(10))    

    end = datetime.datetime.now()
    dif = end - start
        
        
    print("Start time: " + str(start)  + '\n')
    print("End time: " + str(end)  + '\n')
    print("total time of programme: " + str(dif)  + '\n')
    print("query: " + query  + '\n')
    print("number of query: " + str(total_result[0]) + '\n'  )
    print("top10_users: " +  str(total_result[1].most_common(10)) + '\n' )
    print("top10_topics: " + str(total_result[2].most_common(10)) + '\n' )
    print("total_Processes: " + str(num_procs)  + '\n')
       
if rank == 1:
    start = datetime.datetime.now()
    query = "tomorrow"
    if len(sys.argv) == 2:
        pattern = sys.argv[1]
    query = re.sub('[^\w]+',' ',query).lower()
    data = comm.recv(source=0)
    one_result = search(query,data)
    comm.send(one_result, dest=0)
    end = datetime.datetime.now()
    dif = end - start
    print ("Process No." + str(rank) + " use time: " + str(dif) + '\n')
   
if rank == 2:
    start = datetime.datetime.now()
    query = "tomorrow"
    if len(sys.argv) == 2:
        pattern = sys.argv[1]
    query = re.sub('[^\w]+',' ',query).lower()
    data = comm.recv(source=0)
    one_result = search(query,data)
    comm.send(one_result, dest=0)
    end = datetime.datetime.now()
    dif = end - start
    print ("Process No." + str(rank) + " use time: " + str(dif) + '\n')
   
if rank == 3:
    start = datetime.datetime.now()
    query = "tomorrow"
    if len(sys.argv) == 2:
        pattern = sys.argv[1]
    query = re.sub('[^\w]+',' ',query).lower()
    data = comm.recv(source=0)
    one_result = search(query,data)
    comm.send(one_result, dest=0)
    end = datetime.datetime.now()
    dif = end - start
    print ("Process No." + str(rank) + " use time: " + str(dif) + '\n')
   
if rank == 4:
    start = datetime.datetime.now()
    query = "tomorrow"
    if len(sys.argv) == 2:
        pattern = sys.argv[1]
    query = re.sub('[^\w]+',' ',query).lower()
    data = comm.recv(source=0)
    one_result = search(query,data)
    comm.send(one_result, dest=0)
    end = datetime.datetime.now()
    dif = end - start
    print ("Process No." + str(rank) + " use time: " + str(dif) + '\n')
   
if rank == 5:
    start = datetime.datetime.now()
    query = "tomorrow"
    if len(sys.argv) == 2:
        pattern = sys.argv[1]
    query = re.sub('[^\w]+',' ',query).lower()
    data = comm.recv(source=0)
    one_result = search(query,data)
    comm.send(one_result, dest=0)
    end = datetime.datetime.now()
    dif = end - start
    print ("Process No." + str(rank) + " use time: " + str(dif) + '\n')
       
if rank == 6:
    start = datetime.datetime.now()
    query = "tomorrow"
    if len(sys.argv) == 2:
        pattern = sys.argv[1]
    query = re.sub('[^\w]+',' ',query).lower()
    data = comm.recv(source=0)
    one_result = search(query,data)
    comm.send(one_result, dest=0)
    end = datetime.datetime.now()
    dif = end - start
    print ("Process No." + str(rank) + " use time: " + str(dif) + '\n') 
       
if rank == 7:
    start = datetime.datetime.now()
    query = "tomorrow"
    if len(sys.argv) == 2:
        pattern = sys.argv[1]
    query = re.sub('[^\w]+',' ',query).lower()
    data = comm.recv(source=0)
    one_result = search(query,data)
    comm.send(one_result, dest=0)
    end = datetime.datetime.now()
    dif = end - start
    print ("Process No." + str(rank) + " use time: " + str(dif) + '\n') 



