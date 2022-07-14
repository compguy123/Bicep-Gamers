What technical debt has been cleaned up

========================================

We [fixed][1] the break time calculator that shows the break in the session page after a session has been logged. It was Deliberate and Prudent.

[1]: <https://code.cs.umanitoba.ca/winter-2022-a01/group-16/killer-nano-robots/-/commit/1c149cfbdf1a4a390a4d4fcccaf4ba69e70ee930>  


What technical debt did you leave?

==================================

We didn't really have any technical debt left over that we couldn't fix, some unseen bugs like the emulator crashing might be there but as of now we can't tell the source. So this technical debt could be identified as Deliberate and Prudent.

  

Discuss a Feature or User Story that was cut/re-prioritized

============================================

We re-priortized the [push notification][2] during the break time, it notifies the user when a break has ended

[2]: <https://code.cs.umanitoba.ca/winter-2022-a01/group-16/killer-nano-robots/-/issues/21> 


Acceptance test/end-to-end

==========================

We ran through all the feature in order from homepage to the game page, where we added a new game, then to the session page and break page. We [tested][3] a custom game and custom times so we could test the flakiness of time. 
  
[3]: <https://code.cs.umanitoba.ca/winter-2022-a01/group-16/killer-nano-robots/-/blob/larenUser/app/src/androidTest/java/AndroidUITest.java>

Acceptance test, untestable

===============
Learning and implementing the libraries to run a system test was difficult, we tested all of the features. We couldn't test longer session periods and their results so there might be bugs there.  We couldn't run it for say 2 hours to see the output.
  

Velocity/teamwork

================= 

In the first iteration, because we didn’t know much about the entire project, we estimated a longer time to complete the task, which resulted in our actual completion time being shorter than the estimated time. The problem improved on the second iteration, and our estimated and actual times were almost the same. Unfortunately, we overestimated on the third iteration the time it took to test the system and fix technical debt left over from the previous iterations, which led to another overestimation of how long it would take us.

![Velocity graph](/images/velocity_graph.png)