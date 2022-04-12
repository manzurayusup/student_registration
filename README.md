# student_registration

### What user stories were completed this iteration?
In iteration 2, we complemented five new use cases. The app now saves the student's registration info and updates the courses' seats. Moreover, the app automatically adds the course to the student's waitlisted courses if the course is full. Another small change is that the user (student) is now able to see the list of all of their registered/waitlisted classes.

### What user stories do you intend to complete next iteration?
In the next iteration, we are planning to split up the Registration class because it is getting too big. We're trying to extract objects out of it which will make our program easier to test and easier to understand. (You can take a look at branch "${BRANCH_NAME}" if you want to see our progress so far.) We think this will take up some time. But if we do have some time leftover before the third iteration, we will first fix our current bugs and then implement logout and dropping a class.

###	Is there anything that you implemented but doesn't currently work
Currently, all the menu options should be working. We do not have test cases for all the methods that depend on user input, but we think they will be testable after we break up the Registration class. <br/>
<br/>
Other bugs we need to fix:
* The app crashes if a student with no registered courses logs in.
* The app crashes if the user types a string that doesn't match any of the menu options. 
* The code doesn't save the student's waitlisted courses.
* Tests cases manipulate the text files when run, so running all the test cases twice will likely result in errors. Two tests -- testSaveRegistrationCourseInfo() and testPrintAllCourses() -- are failing and we're trying to fix that.

### What commands are needed to compile and run your code from the command line (or better yet, provide a script that people can use to run your program!)
You need to be in the root directory and using bash. Run the following command: ./run_program.sh <br/>
<br/>
You can login as:
Jack Sparrow (ID: 123456)
Dorothy Gale (ID: 234567)
<br/>
List of courses with seats left: <br/>
* CSE204 
* CSE217 
* CSE240 
* CSE247 
* CSE330 
* CSE332 
<br/>
List of courses with 0 seats: <br/>
* CSE131
* CSE132
* CSE347

  
  

