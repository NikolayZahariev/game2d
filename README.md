# Scrub Lords

2D Platformer

# Instructions

## Rebasing

git fetch upstream

git rebase upstream/master

git push origin

## Commit/Push

git add .

git commit

git push origin BRANCH

## Squashing

git rebase master -i

#### Example:
pick "commit 1, bla bla bla" <br>
pick "commit 2, bla bla bla" <br>
pick "commit 3, bla bla bla" <br>

#### To:

pick "commit 1, bla bla bla" <br>
squash "commit 2, bla bla bla" <br>
squash "commit 3, bla bla bla" <br>

#### commits 2 and 3 will be squashed into 1

git push origin BRANCH --force

<b>WARNING:</b> --force will make you lose the old progress

## Amending

git add .

git commit --amend

git push origin --force

<b>WARNING:</b> --force will make you lose the old progress