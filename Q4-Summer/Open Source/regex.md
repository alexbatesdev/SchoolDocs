# REGEx stuff!

Case Sensitive


"." - any character

[] - Matches any characters in brackets

[^] - Matches any characters NOT in the brackets

[a-z] - Matches any character inbetween both letters

[0-9] - Works with numbers!

\d - also any digit

TOKEN{3} - matches the previous token 3 times

TOKEN? - optional match, either grabs all or none

TOKEN* - zero or more of the previous token

TOKEN+ - one or more of the previous token

TOKEN\w - Get whole word

TOKEN\w+ - Match with words bigger than previous token

TOKEN\w* - Will get anything with the previus token

[a-z]+ - grabs lowercase letters when there's more than one

\s - grabs white space

TOKEN{2,3} - grabs 2 or 3 of previous token

TOKEN{2,} - grabs 2 or more up to infinity

^TOKEN - only grab if the token is at the beginning of the line

TOKEN$ - grabs it at the end of the line

.+ - grabs everything but the endline

.* - anything or nothing

TO(.*)KEN - optional middle section can be any length or not exist and it will be grabbed