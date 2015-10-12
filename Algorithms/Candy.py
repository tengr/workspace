#Leecode
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
# There are N children standing in a line. Each child is assigned a rating value.
# 
# You are giving candies to these children subjected to the following requirements:
# 
# Each child must have at least one candy.
# Children with a higher rating get more candies than their neighbors.
# What is the minimum candies you must give?
from itertools import count
def upres(ratings, start_count):
        i = 1
        res = 0
        count = start_count
        prestate = 0
        while i < len(ratings):
            if ratings[i] > ratings[i-1]:
                prestate = 1
                count += 1
            elif ratings[i] == ratings[i-1]: 
                if prestate >= 0:
                    if i == len(ratings) - 1 or ratings[i] <= ratings[i+1]:
                        res += 1
            elif prestate > 0:
                res += (1 + count) * count / 2
                count = start_count
                prestate = -1
            i += 1
        if prestate > 0:
            res += (1 + count) * count / 2
        return res

def candy(ratings):
#     if len(ratings) == 1:
#         return 1
#     if len(ratings) == 2:
#         if (ratings[0] == ratings[1]):
#             return 2
#         else:
#             return 3
#     
#     j = 1
#     res = 0
#     while j < len(ratings):
#         if(ratings[j] != ratings[j-1]):
#             break
#         else:
#             res += 1
#         j += 1
#     if j == len(ratings):
#         return res
#     i = j
#     prestate = ratings[1] - ratings[0]
#     if prestate == 0:
#         res = 1
#         count = 1
#     else:
#         res = 0
#         count = 2
# 
#     i = 2
#     while i < len(ratings):
#         if prestate > 0:
#             if ratings[i] == ratings[i-1]:
#                 res += count
#             else:
#                 if ratings[i] > ratings[i-1]:
#                     count += 1
#                 else:
#                     res += (1 + count) * count / 2
#                     count = 1
#                 prestate = ratings[i] - ratings[i-1]
#             i += 1
#         elif prestate < 0:
#             if ratings[i] == ratings[i-1]:
#                 res += count
#             else:
#                 if ratings[i] > ratings[i-1]:
#                     count += 1
#                 else:
#                     res += (1 + count) * count / 2
#                     count = 1
#                 prestate = ratings[i] - ratings[i-1]
#             i += 1
#             
#     if ratings[-1] == ratings[-2]:
#         res += count
#         print "eaql"
#         print count
#     else:
#         res +=  (1 + count) * count / 2
#     return res
    #peaks = []
    #troughs = []
#     posinfo = [0] * len(ratings)
#     prestate = ratings[1] - ratings[0]
#     for i in range(1,len(ratings)-1):
#         if  ratings[i] > ratings[i-1]:
#             posinfo[i] = 1
#             if prestate < 0:
#                 posinfo[i] = 2
#             prestate = ratings[i] - ratings[i-1]
#         elif ratings[i] < ratings[i-1]:
#             posinfo[i] = -1
#             if ratings[i+1] > ratings[i]:
#                 posinfo[i] = -2
#             prestate = ratings[i] - ratings[i-1]
# 
#     while i < len(ratings):
# #         if prestate > 0:
# #             if ratings[i] == ratings[i-1]:
# #                 res += count
# #             else:
# #                 if ratings[i] > ratings[i-1]:
# #                     count += 1
# #                 else:
# #                     res += (1 + count) * count / 2
# #                     count = 1
# #                 prestate = ratings[i] - ratings[i-1]

    #return upres(ratings, 1) + upres(ratings[::-1] , 1)
    #return upres(ratings[::-1], 1) 
    
#     prestate = ratings[1] - ratings[0]
#     if prestate > 0:
#         incre_count = 1 #1 increasing from bottom
#         #res = 1 #ratings[i] is the bottom, gets score 1
#         decre_count = 0
#         res = 0
#     elif prestate == 0:
#         incre_count = 0 #counting how many increasing from bottom
#         decre_count = 0 #counting how many decreasing from top
#         res = 1
#     else:
#         decre_count = 1
#         incre_count = 0
#         res = 0
#     
#     i = 0
#     while i < len(ratings) - 1:
#         if ratings[i + 1] > ratings[i]: #increasing
#             if prestate == 0: #__/ previous two numbers equal
#                 res += 1 #ratings[i] gets score 1
#                 incre_count += 1 #add 1 count increasing from bottom
#             elif prestate > 0: #/// #previous increasing
#                 incre_count += 1
#             else:#previously decreasing ratings[i] is the bottom
#                 res += (decre_count + 1) * decre_count / 2
#                 decre_count = 0
#                 incre_count = 1
#         elif ratings[i + 1] == ratings[i]: #equal
#             if prestate == 0: #___
#                 res += 1
#             elif prestate > 0: #/--#previously increasing
#                 res += incre_count
#             else: #previously decreasing
#                 res += 1
#         else: #decreasing
#             if prestate == 0: #previously equal
#                 decre_count += 1
#             elif prestate > 0: #previously increasing
#                 res += count
#                 count = 1
#             else: #previously decreasing
#                 count += 1
#                 res += count
#             
#             
#         prestate = ratings[i + 1] - ratings[i]
#         i += 1
#     
#     if ratings[-1] > ratings[-2]:
#         res += count
#     elif ratings[-1] == ratings[-2]:
#         res += 1
#     else:
#         res += 1
#                 
#     return res
    #     
        if len(ratings) == 1:
            return 1
        if len(ratings) == 2:
            if ratings[0] == ratings[1]:
                return 2
            else:
                return 3
    
        prestate = 0
        i = 0
        res = 0
        incre_count = 0
        decre_count = 0
        compareflag = 0
        commited = 1
        #score = [0] * len[ratings]
        #appending score for ratings[i] to res
        #incre_count and decre_count means 'we have up to @counts' from the top and the next one is going to be that
        while i < len(ratings) - 1:
            if ratings[i + 1] == ratings[i] and prestate == 0: #E E
                res += 1
            elif ratings[i + 1] == ratings[i] and prestate < 0: #D E
                if commited == 0:
                    res += (decre_count + 1) * decre_count / 2
                    #if compareflag == 0: #no need to compare
                    #    res += (1 + decre_count) * decre_count / 2
                    if compareflag == 1:
                        res += max(decre_count + 1, incre_count + 1)
                        compareflag = 0
                    else:
                        res += (decre_count + 1)
                    incre_count = 1
                    commited = 1
                elif commited == 1:
                    res += 1 #ratings[i] gets score of 1
                    incre_count += 1
            elif ratings[i + 1] == ratings[i] and prestate > 0: #I E
                res += (incre_count + 1) #ratings[i[ is incre_count from the bottom, gets score incre_count + 1
            elif ratings[i + 1] < ratings[i] and prestate < 0: #D D
                commited = 0
                decre_count += 1
            elif ratings[i + 1] < ratings[i] and prestate == 0:#E D
                commited = 0
                decre_count += 1 #leave scoring till later
                #compareflag = 0 #don't compare with left increasing
            elif ratings[i + 1] < ratings[i] and prestate > 0: #I D
                commited = 0
                compareflag = 1
                decre_count = 1
            elif ratings[i + 1] > ratings[i] and prestate > 0: #I I
                res += (incre_count + 1)
                incre_count += 1
            elif ratings[i + 1] > ratings[i] and prestate == 0: # E I
                if commited == 0:
                    res += (decre_count + 1) * decre_count / 2
                    #if compareflag == 0: #no need to compare
                    #    res += (1 + decre_count) * decre_count / 2
                    if compareflag == 1:
                        res += max(decre_count + 1, incre_count + 1)
                        compareflag = 0
                    else:
                        res += (decre_count + 1)
                    incre_count = 1
                    commited = 1
                elif commited == 1:
                    res += 1 #ratings[i] gets score of 1
                    incre_count += 1
            elif ratings[i + 1] > ratings[i] and prestate < 0: # D I 
                if commited == 0:
                    res += (decre_count + 1) * decre_count / 2
                    #if compareflag == 0: #no need to compare
                    #    res += (1 + decre_count) * decre_count / 2
                    if compareflag == 1:
                        res += max(decre_count + 1, incre_count + 1)
                        compareflag = 0
                    else:
                        res += (decre_count + 1)
                    incre_count = 1
                    commited = 1
                elif commited == 1:
                    res += 1 #ratings[i] gets score of 1
                    incre_count += 1
                
            prestate = ratings[i + 1] - ratings[i]
            #print str(ratings[i]) + "\t" + str(res)
            i += 1   
        #print incre_count
        #handling last element
        if prestate == 0:
            res += 1
        elif prestate > 0:
            res += (incre_count + 1)
        else:
            if commited == 0:
                    res += (decre_count + 1) * decre_count / 2
                    #if compareflag == 0: #no need to compare
                    #    res += (1 + decre_count) * decre_count / 2
                    if compareflag == 1:
                        res += max(decre_count + 1, incre_count + 1)
                        compareflag = 0
                    else:
                        res += (decre_count + 1)
                    incre_count = 1
                    commited = 1
            elif commited == 1:
                    res += 1 #ratings[i] gets score of 1
                    incre_count += 1
        return res
#print candy([4,1,1,1,1,2,3,4,1,1,2]) #2 1 1 1 3 2 1 1 1 2

# if candy([4,3,2,1]) != 10:
#     print "wrong"
# if candy([1,2,3,4]) != 10:
#     print "wrong"
# if candy([1,1,2,3,4]) != 11:
#     print "11234 wrong"
# if candy([4,3,2,1,1]) != 11:
#     print "43211 wrong"
print candy([5,1,1,1,10,2,1,1,1,2]) 

print candy([1,2,3,4,5,6,100])