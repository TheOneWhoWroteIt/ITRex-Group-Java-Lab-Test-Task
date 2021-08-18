#Task 1.

**Implement an algorithm that simplifies the English text.** 

**1.Remove "c" from the text**

If the text contains “ci” and “ce”, change it to “si” and “se”.
If “ck” then change it to “k”.
In the other case replace “c” with “k”.
All the changes should be made in a strong order left-to-right.

**For example:**

The word “success” first of all will be the word “sukcess”. Then “suksess”.

**2.Remove a double letter**

If the text contains “ee” then replace it by simple “i”.
If “oo” then change it by “u”.
In the other case any double letter should be changed by one letter.

**For example:**

“ooo” will be “uo”
“oou” will be “u”
“iee” will be “i”

**3.Remove the letter “e” at the end of each word**

Remove the letter “e” at the end of each word if the word length > 1.

**For example:**

“The” will be “Th”.

**4.Remove articles**

Remove the articles “a”, “an” or “the” from the text. They should be removed only if they were the words a, an, the in the original text.

**For example:**

Input text: “the table”. After the first three steps it will be changed to “th tabl”. After the fourth step: “tabl”

**Examples:**

**Input:**

cacao and coffee

success 

**Output:**

kakao and kofi

sukses


#Task 2.

**Implement an algorithm.**

Opening his eyes, the Prince of Persia found himself at the top of the underground Jaffar’s labyrinth. The labyrinth contains H levels, located strictly one above the other. Each level is a rectangular area, divided into m × n areas. In some areas there are pillars. In such areas the Prince can not go.

Prince can move from one area to another adjacent free area on the same level. He can also break through the floor to be at a lower level (at the lowest level he can not break through the floor). Any movement of the Prince takes 5 seconds.

There is a Princess waiting for the Prince at one of the areas on the lowest level. Your task is to help the Prince to find the Princess as soon as possible.

**Input format:**

The first line of INPUT.TXT file contains digits H, M, N - count of levels and rectangle size of each level. Then other lines contain H blocks, each of them describes one level from top to bottom. Each block contains M rows and N columns. Every cell contains: “.”, “o”, “1”, 2”.

- “.” means that it’s free space.
- “o” means columns there.
- “1” Prince position.
- “2” Princes position.

Symbol “1” is always in the first block. Symbol “2” is always in the last block. Nearby blocks are separated by one blank line.

 

**Output format:**

The minimum time the Prince has to find the Princess in seconds. As fairy tales always have happy ends it is guaranteed that the Prince can do it.

 

 

**Examples:**

 

**Input:**

3 3 3

 

1..

oo.

...

 

ooo

..o

.oo

 

ooo

o..

o.2

 

**Output:**

60 