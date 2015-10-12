from textblob import TextBlob
from textblob.sentiments import NaiveBayesAnalyzer
from textblob.sentiments import PatternAnalyzer
from textblob.np_extractors import ConllExtractor
#import couchdb
import json
import csv
dic = {}
count = 0
# couch = couchdb.Server()
# db_name = 'backup'
# if db_name not in couch:
#     couch.create(db_name)
# db_backup = couch[db_name]

with open("/Users/ruichen/Desktop/data/Sydney4.csv", "r") as f:
    reader = csv.reader(f)
    try:
        for i, line in enumerate(reader):
            json_input = line[4]
            #print json_input
            #print line
            tweet = json.loads(json_input)
            tweet_text = tweet['text']
            print tweet_text
#             create_time = tweet['created_at']
#             print create_time
#             time_seq = create_time.split()
#             print len(time_seq)
#             tweet['day'] = time_seq[0]
#             tweet['month'] = time_seq[1]
#             tweet['date'] = int(time_seq[2])
#             t = time_seq[3].split(":")
#             
#             tweet['hour'] = int(t[0])
#             tweet['minute'] = int(t[1])
#             tweet['second'] = int(t[2])
#             tweet['plus0000'] = time_seq[4]
#             tweet['year'] = int(time_seq[5])
            print tweet
            #break
            #print tweet
            #print type(tweet)
            #tb = TextBlob(tweet_text)
            #print type(tb)
            pt_tb = TextBlob(tweet_text, analyzer=PatternAnalyzer())
            nb_tb = TextBlob(tweet_text, analyzer=NaiveBayesAnalyzer())
            extractor = ConllExtractor()
            np_tb = TextBlob("Python is a high-level programming language, high-level programming language.", np_extractor=extractor)
            noun_dic = {}
            for ele in np_tb:
                if ele not in noun_dic:
                    noun_dic[ele] = 1
                else:
                    noun_dic[ele] += 1
             
            #print str(np_tb.noun_phrases)
            print str(pt_tb.sentiment) 
#             #print tweet['text']
#             print str(nb_tb.sentiment.classification)
#             print str(nb_tb.sentiment.p_pos)
#             print str(nb_tb.sentiment.p_neg)
#             print str(tb.sentiment.polarity)
#             print str(tb.sentiment.subjectivity)
            #print tweet['tb']
            count += 1
            break
    except Exception, e:
        print e
#print dic["text"]
#         
#     line  = f.readline()
#     line = f.readline()
#     l = line.find("\"")
#     print l
#     h = line.rfind("\"")
# 
#     print h
#     j = line[l + 1: h]
#     print j
#     
#     dic = json.loads(j)
#     print dic