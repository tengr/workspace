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
import sys
#Ruichen Teng / tengr / 678693
#Tiehua Zhang / tiehuaz / 645227
#Chao Lin / clin2 / 658584
#Xueqiang Ma/ xueqiangm/ 668190
#Zheng Zheng/ zzheng2/ 651384
import os
from pip._vendor.requests.api import head

class LinkedListNode:
    def _init_(self, node_value):
        self.val = node_value
        self.next = None
def _insert_node_into_singlylinkedlist(head, val):
    if head == None:
        head =LinkedListNode(val)
    else:
        end = head
        while end.next != None:
            end = end.next
        node = LinkedListNode(val)
        end.next = node
    return head

def find(list, sublist):
    return find_ind(list,sublist, 0)
    

def find_ind(list, sublist, ind):
    if list == None:
        if sublist == None:
            return ind
        else:
            return -1
    else:
        if sublist == None:
            return ind
        else:
            if list.val == sublist.val:
                return find_ind(list.next, sublist.next, ind)
            else:
                return find_ind(list.next, sublist, ind + 1)