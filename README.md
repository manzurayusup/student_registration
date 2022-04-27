# student_registration

### What user stories were completed this iteration?
We completed the bulk of the functionality of our app in the first two iterations and iteration 3 mainly focused on cleaning the code by refactoring the classes. We split the Registration class into AppFileProcessor, AppFileWriter and Commandline classes/objects. We were able to complete three new use cases. The app now saves all course and student info including their waitlisted courses. We added another option to the menu - "Print waitlisted courses" - so now our menu has a total of 6 options. Refactoring took a lot of time, so we weren't able to implement lots of new features, but we did improve usage messages and error handling.

###	Is there anything that you implemented but doesn't currently work
In iteration 2, we listed three things that weren't working. We fixed 2 of those:. <br/>

* The app doesn't crash if the user types an invalid input. 
* The app saves the student's waitlisted courses.
* But the app still crashes if a student with no registered/waitlisted courses logs in.

There are also other things that we think we could've done better:
We only wrote tests for the important methods (not helper methods). We also aren't testing the Commandline class because it runs a while loop that depends on user input and we wanted to prioritize refactoring. 

### What commands are needed to compile and run your code from the command line?
You need to be in the root directory and using bash. Run the following command: ./run_program.sh <br/>
<br/>
You can login as:
Jack Sparrow (ID: 123456), enrolled in CSE247, CSE204, CSE332 and waitlisted in CSE132. <br/>
Dorothy Gale (ID: 234567), enrolled in CSE132 and waitlisted in CSE347. <br/>
<br/>
List of courses with seats left: <br/>
* CSE131 
* CSE204 
* CSE217 
* CSE240 
* CSE247 
* CSE330 
* CSE332 
<br/>
List of courses with 0 seats: <br/>
* CSE132 
* CSE347 
* GER101 
* GER201 
* GER313 
<br/>

  
  

