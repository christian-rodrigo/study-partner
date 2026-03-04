# DTO Design – Study Partner Backend

## RegisterRequest

Used for user registration

Fields:

* email
* password
* name
* university
* studyField

## LoginRequest

Used for login

Fields:

* email
* password

## UserResponse

Returned when requesting a user profile

Fields:

* id
* name
* email
* university
* studyField
* bio

## SwipeRequest

Used when swiping another user

Fields:

* targetUserId
* liked

## MatchResponse

Returned when listing matches

Fields:

* matchId
* userId
* name

## MessageRequest

Used to send a message

Fields:

* text

## MessageResponse

Returned when retrieving chat messages

Fields:

* messageId
* senderId
* text
* sentAt
