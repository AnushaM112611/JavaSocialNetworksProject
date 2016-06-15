# JavaSocialNetworksProject

# This project is to analyze social networks on Web forums. We compare people who post lots of questions vs lots of answers. 
# As a starting point, I  would like a list ,that prints out the top 10 users by number of questions asked, and the top 10 by 
# number of answers posted.

# Description of users.xml and posts.xml

posts.xml : 
- Id
- PostTypeId( - 1: Question   - 2: Answer )
(if the value of PostTypeId is 1 it means question and if it is 2 it means the post is an answer/reply to a question)
- ParentID (only present if PostTypeId is 2)
- AcceptedAnswerId (only present if PostTypeId is 1)
- CreationDate
- Score
- ViewCount
- Body
- OwnerUserId
- LastEditorUserId
- LastEditorDisplayName="Jeff Atwood"
- LastEditDate="2009-03-05T22:28:34.823"
- LastActivityDate="2009-03-11T12:51:01.480"
- CommunityOwnedDate="2009-03-11T12:51:01.480"
- ClosedDate="2009-03-11T12:51:01.480"
- Title=
- Tags=
- AnswerCount
- CommentCount
- FavoriteCount

users.xml:
- Id
- Reputation
- CreationDate
- DisplayName
- EmailHash
- LastAccessDate
- WebsiteUrl
- Location
- Age
- AboutMe
- Views
- UpVotes
- DownVotes


# Basically this project does two things:
# Read the data stored in xml format (users.xml and posts.xml files ) using SAX / DOM parser and also write an own custom 
# parser( 2 versions of same program). Extract only necessary fields required for the program while reading xml files to 
# save memory usage and execution time. Based on the extracted data used an efficient algorithm using efficient data structures 
# that prints out the top 10 users by number of questions asked, and the top 10 by number of answers posted.

