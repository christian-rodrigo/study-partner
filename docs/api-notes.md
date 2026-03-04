# API Notes – Study Partner App

## Authentication
POST /api/auth/register
POST /api/auth/login

## Users
GET /api/users/{id}
PUT /api/users/{id}

## Matches
GET /api/matches
POST /api/swipe/{userId}

## Chat
GET /api/messages/{matchId}
POST /api/messages
