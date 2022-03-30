# student_registration

### What user stories were completed this iteration?
In this Iteration, we first decided to set up our project structure in eclipse. We made two text files, courses.txt and student.txt, and two Java files, Course.java and Student.java. student.txt listed information about a student on one line for each student. This information included the student’s first and last name, the student’s id number, the classes that the student has registered for, and the classes that the student is waitlisted for. course.txt listed information about a course on one line for each course. This information included the course’s id, the course’s name, the start time, the end time, what days the class are on, the professor that teaches the course, if there is a final exam, and the building and room number of that class. Course.java and Student.java work together to add the courses to the student’s course list. It does this so when a student decides to sign up for a new class, the computer will take in all the information for each course and link it to that student. We also have an ErrorCode.java for our Enums because some of our functions return success or error. Finally, we have a Registration.java that takes in the text files and parses them to understand what classes the student is linked to. It also remembers all of the information each course holds and if a student has registered for a class. In this Java file, we made methods to populate Student and Course objects from the text files and it also implements the login method. This method will allow a student to log into the app as that user. When this runs, it will take in all the information from Student.java and Course.java and allow the terminal to print the numbered options the student is able to take when they log in. The user is then able to select an option by entering one of the numbers into the console. The user’s choice will be read and user will be able to input any other information if needed. This is also what allows a student to register for a new class. Doing so will enable the code to print the respective information about each course and specifically all the course the student has registered for, so the student can see that they have successfully registered for this class. In the future we will be not be printing out the classes when the student registers and rather, making it another numbered option that they can select to see the registered classes. Finally, the user is able to select the number option to quit, and the code will stop running.

### What user stories do you intend to complete next iteration?
In the next Iteration, we want to be able to save the changes a student has made onto their account after the user quits. We also want to print out all the available classes the student can choose from to take. Like we previously said, we want to take out the part that print out all the classes the student has registered for after the user registers for a class, and instead, we want to add a separate numbered option the user can elect that will display the classes the user has registered for. In this iteration, we will also implement the waitlist option when the class is full, complete the processChoice() method in Registration.java and write tests for processChoice().

###	Is there anything that you implemented but doesn't currently work
As of right now, our entire code works. However, not all options are selectable because the code has not been implemented, yet. The user is allowed to log-in (by typing an ID), register for an available class (option 1), and quit (option 5). <br/>
<br/>
Since not all the options in the processChoice() method in Registration.java are selectable (e.g. printing all courses, adding to waitlist etc), we did not write tests for the processChoice() method because it is not complete right now. We intend to have the tests for that method by the next iteration.

### What commands are needed to compile and run your code from the command line (or better yet, provide a script that people can use to run your program!)
  You need to be in bash and run the following command: ./run_program.sh
  For ID, type 123456 (no spaces) and click ENTER.
  To register for a course, type 1 then press ENTER. Then type CSE131 (or CSE132) to register for that class and click ENTER. 
  To quit, type 5 and press ENTER.
  
  
### Note:
We merged the make_main_registration_class to main even though the functionality inside Registration.java is not complete. This was done to make it easy to clone and run the program and for grading purposes and peer review. We plan to complete Registration.java before the upcoming iteration.

  
