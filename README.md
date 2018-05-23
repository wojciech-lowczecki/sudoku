# Sudoku
### 1. Description
> This application resolves sudoku of the type 9x9 placed in a file given as a parameter and displays result on the screen.
  If the second parameter is present, it's treated as a target for result. The source has to be a text file and 
  contain only 81 characters `1` - `9` representing values and `.` (dots) as empty places. It can contain any 
  number of spaces, new lines and tabulators.
>  
> *Example 1:*
> ```
> . 2 . 5 . 1 . 9 . 
> 8 . . 2 . 3 . . 6
> . 3 . . 6 . . 7 .
> . . 1 . . . 6 . .
> 5 4 . . . . . 1 9
> . . 2 . . . 7 . .
> . 9 . . 3 . . 8 .
> 2 . . 8 . 4 . . 7
> . 1 . 9 . 7 . 6 .
> ```
> 
> *Example 2:*
> ```
> .2.5.1.9.8..2.3..6.3..6..7...1...6..54.....19..2...7...9..3..8.2..8.4..7.1.9.7.6.
> ```
>
> Usage: `<command-name> <source> [<target>]`, details are below. 
> 
### 2. Installation and usage
> ##### Prerequisites
> *Sudoku* requires JRE 1.8 or later installed on your system. Type `java -version` to check it.
>
> ##### Windows
> * Download and unpack project archive
> * Open your command-line interface
> * Go to unpacked application home directory `sudoku`
> * Build the program: `gradlew clean installDist` 
> * Usage: you can use attached example files   
>  
>  ```
>  > cd build\trial
>  > bin\sudoku examples\difficult.txt examples\resolved.txt 
>  > type examples\resolved.txt
>  > dir /B examples
>  ```  
>
> ##### Linux/Unix
> * Download and unpack project archive
> * Open your command-line interface
> * Go to unpacked application home directory `sudoku`
> * Build the program: `./gradlew clean installDist` 
> * Usage: you can use attached example files   
>  ```
>  $ cd build/trial  
>  $ sh bin/sudoku examples/difficult.txt examples/resolved.txt 
>  $ cat examples/resolved.txt
>  $ ls examples
>  ```  
