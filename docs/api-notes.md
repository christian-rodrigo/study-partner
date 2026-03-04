# API Notes – Study Partner App

## Authentication

POST /api/auth/register 
POST /api/auth/login

## Users

GET /api/users/{id} 
PUT /api/users/{id}

## Swipe / Matching

POST /api/swipe/{targetUserId} 
GET /api/matches

## Chat

GET /api/messages/{matchId} 
POST /api/messages
