# API Notes – Study Partner App

## Authentication

* POST /api/auth/register – create a new user account
* POST /api/auth/login – authenticate user and return token

## Users

* GET /api/users/{id} – get user profile
* PUT /api/users/{id} – update user profile

## Swipe / Matching

* POST /api/swipe/{targetUserId} – like or dislike another user
* GET /api/matches – list all matches for the current user

## Chat

* GET /api/messages/{matchId} – retrieve messages for a match
* POST /api/messages – send a message
