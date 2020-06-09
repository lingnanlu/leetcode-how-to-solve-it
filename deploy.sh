#!/bin/zsh

mvn clean compile

rm -rf out/
cp README.md out/

git add --all && git commit -m "x" && git push