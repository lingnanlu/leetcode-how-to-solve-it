#!/bin/zsh


rm -rf out/

mvn clean compile

cp README.md out/

git add --all && git commit -m "x" && git push