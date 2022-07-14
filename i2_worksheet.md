Paying off technical debt
========================================
We were using a newer version of HSQL which caused problems with the real database because of some implementations that changed. We decided to updated it to the [older version][1] of HSQL which works well and has features that the lastest one lacked. This debt was deliberate and reckless because we wanted to make it work but didn't have time to research how the newer version worked.

One of our technical debts includs [improper][2] branching from our chosen design strategy that resulted in the code not merging and compiling properly when pulled from origin. We needed to deliver the working project so we had to merge directly into main instead of develop. This was [fixed][3] later by handling the merge issues and properly branching and [merging][4] into develop.   

[1]: <https://code.cs.umanitoba.ca/winter-2022-a01/group-16/killer-nano-robots/-/commit/1ee49aecb6e99abd33effb1dbd2879bf066c7bd7>
[2]: <https://code.cs.umanitoba.ca/winter-2022-a01/group-16/killer-nano-robots/-/commit/4608c8cedc49fbf64d080b84249b380e8dae05d8>
[3]: <https://code.cs.umanitoba.ca/winter-2022-a01/group-16/killer-nano-robots/-/commit/d247fec8b685a7c89b64cdc61e5e8a01340bdbad>
[4]: <https://code.cs.umanitoba.ca/winter-2022-a01/group-16/killer-nano-robots/-/commit/553b0a798d95271b33d214bc0f0223803f02009c>

SOLID
==================================
Find a SOLID violation in the project of group with group number n-1 in the same section of the course as you (group 1 does group 16). Open an issue in their project with the violation, clearly explaining the SOLID violation - specifying the type, provide a link to that issue. Be sure your links in the issues are to specific commits (not to main, or develop as those will be changed).

Provide a link to the issue you created here.

Group 15 has a class called Section, this class has 2 constructors. The [second constructor][5] violates the Single Resposibility Principle (S). The constructor is handling input and assigning variables. It should be in a different function that can handle the input. The link to the [issue][6] and [commit][7]

[5]: <https://code.cs.umanitoba.ca/winter-2022-a01/group-15/better-than-aurora-registration-system-a01/-/blob/main/app/src/main/java/com/group_15/bta/objects/Section.java#L48>
[6]: <https://code.cs.umanitoba.ca/winter-2022-a01/group-15/better-than-aurora-registration-system-a01/-/issues/39>
[7]: <https://code.cs.umanitoba.ca/winter-2022-a01/group-15/better-than-aurora-registration-system-a01/-/commit/10e31c7a25b1ea4a26e5e8ae2f66a66e8c995872>

Retrospective
============================================
Describe how the retrospective has changed the way you are doing your project. Is there evidence of the change in estimating/committing/peer review/timelines/testing? Provide those links and evidence here - or explain why there is not evidence..

Iteration 1 retrospective helped us analyse our mistakes, we had trouble using [branching][8] and [merging][9] strategies. We also took longer to figure out our goal during meeting outside of class. In iteration 2 we spent less time in meetings and achieved more goals than iteration 1.

[8]: <https://code.cs.umanitoba.ca/winter-2022-a01/group-16/killer-nano-robots/-/commit/d247fec8b685a7c89b64cdc61e5e8a01340bdbad>
[9]: <https://code.cs.umanitoba.ca/winter-2022-a01/group-16/killer-nano-robots/-/commit/553b0a798d95271b33d214bc0f0223803f02009c>


Design patterns
==========================
Show links to your project where you use a well-known design pattern. Which pattern is it? Provide links to the design pattern that you used.

Note: Though Dependency Injection is a programming pattern, we would like to see a programming pattern other than Dependency Injections.

We used the adapter design pattern in our [project][10]. This solved the incompatiblity issues with recycle viewer and the xml layout files so we could put tables with information instead of designing the whole screen. 

[10]: <https://code.cs.umanitoba.ca/winter-2022-a01/group-16/killer-nano-robots/-/blob/LarenUser/app/src/main/java/com/example/bicepgamers/GUI/SessionRecyclerAdapter.java>



Iteration 1 Feedback fixes
===============

The architecture does not reflect the state of the application. For example, showAllGame is not present in the current iteration. The architecture design was later [fixed][11] and updated according to the current design of the project 

Few issues were unassigned and some issues were not updated with actual time spent on task. We [fixed][12] those buy assigning them and also adding time estimates for each of the missing issues. Some these issues include:
1. [Issue #30](https://code.cs.umanitoba.ca/winter-2022-a01/group-16/killer-nano-robots/-/issues/30)
2. [Issue #25](https://code.cs.umanitoba.ca/winter-2022-a01/group-16/killer-nano-robots/-/issues/25)
3. [Issue #24](https://code.cs.umanitoba.ca/winter-2022-a01/group-16/killer-nano-robots/-/issues/24)
4. [Issue #26](https://code.cs.umanitoba.ca/winter-2022-a01/group-16/killer-nano-robots/-/issues/26)

[11]: <https://code.cs.umanitoba.ca/winter-2022-a01/group-16/killer-nano-robots/-/blob/develop/Architecture.png>
[12]: <https://code.cs.umanitoba.ca/winter-2022-a01/group-16/killer-nano-robots/-/issues?scope=all&state=all>

