# from pattern.web import Twitter
# import time
# # twitter = Twitter(language='en', place='Sydney') 
# # for tweet in twitter.search('"more important than"', cached=False):
# #     
# #     trends =  Twitter().trends(cached=False)
# 
# print help(Twitter)
# 
# # s = Twitter().stream('#fail')
# # for i in range(10):
# #     time.sleep(1) 
# #     s.update(bytes=1024)
# #     print s[-1].text if s else 'sdsfd'
# # # l =  Twitter().trends(cached=False)
# # # for ele in l:
# # #     print ele

import tweepy
from twitter_authentication import CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, ACCESS_TOKEN_SECRET

auth = tweepy.OAuthHandler(CONSUMER_KEY, CONSUMER_SECRET)
auth.set_access_token(ACCESS_TOKEN, ACCESS_TOKEN_SECRET)

api = tweepy.API(auth)

# public_tweets = api.home_timeline(count=100)
public_tweets = api.user_timeline(user_id=5301)
for tweet in public_tweets:
    print(tweet.text)