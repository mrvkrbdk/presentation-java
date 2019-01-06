#!/bin/sh

git add .
git commit -m 'revised'
git push origin master
git push heroku master
heroku logs --tail
