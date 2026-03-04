# Database Design – Study Partner Backend

## User

* id
* name
* email
* password
* university
* studyField
* bio

## Swipe

* id
* userId
* targetUserId
* liked

## Match

* id
* user1Id
* user2Id
* createdAt

## Chat

* id
* matchId

## Message

* id
* chatId
* senderId
* text
* sentAt
