# My Personal Project

## Phase 0

&nbsp;&nbsp;&nbsp;&nbsp; My application is going to be a workout planner,
that allows you to go on to each day of the week and add/remove 
specific workouts. In those workouts you can specify the muscle group
it hits. You can also add a set, a set will contain the number of reps to do in the set
along with the percent of your one rep max weight to lift
There will also be an area to add additional notes. The target audience for this app
will be athletes and bodybuilders. This project is important to me 
because working out is a huge part of my lifestyle and having an app
like this will easily allow me to track my workouts and design workout plans.


## User Story

- As a user, I want to be able to add an exercise to a day
- AS a user, I want to be able to select the day of the week
- As a user, I want to be able to view my exercises for each day
- As a user, I want to be able to add a set to my workout, a set will contain the number of rep and the percent 
max weight to lift
- As a user, I want to be able to remove an exercise from my plan
- As a user, I want to be able to see all the sets for each exercise
- As a user, I want to be able to save my workout plan to file
- As a user, when I start the application, I want to be given the option to load my workout plan from file.

## Sample eventLog
Wed Aug 09 13:07:52 PDT 2023 <br>
Exercise Bench Press added to Wednesday <br>
Wed Aug 09 13:08:00 PDT 2023 <br>
Added set to Bench Press exercise. <br>
Wed Aug 09 13:08:27 PDT 2023 <br>
Exercise Bar Squat added to Tuesday <br>
Wed Aug 09 13:08:34 PDT 2023 <br>
Exercise Bench Press removed from Wednesday <br>

## Phase 4 Task 3

If I had more time to work on my project, the first refactoring I would undertake is implementing the Singleton pattern in the `WorkoutPlanGUI` and `ExerciseFrameGUI` classes. Currently, these two frames are instantiated multiple times, and after running the program for a while, many instances are disposed of or set to invisible. By using the Singleton pattern, I can ensure that the same instance is called each time, significantly improving the code's efficiency and maintainability.

The second refactoring I would do is to have the `Day` and `Exercise` classes implement the `Iterator` interface. `Day` would iterate over `Exercise` objects, and `Exercise` would iterate over `Set` objects. This change would reduce the coupling in my code and eliminate the need for lengthy `for-each` loops to access the `Exercises` and `Sets` lists.
