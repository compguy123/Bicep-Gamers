Iteration 1 Worksheet
=====================

 Adding a feature
-----------------
We wanted to access the database for all the games to calculate appropriate break times and suggest exercises for the users. We could not complete the feature, suggesting exercises was left out but the break time calulator was implemented. We added a feature that has buttons for [break][1] and [session][2] for creating a game. We then [tested][3] it and [implemented][4] it into the application.

[1]: <https://code.cs.umanitoba.ca/winter-2022-a01/group-16/killer-nano-robots/-/issues/2>
[2]: <https://code.cs.umanitoba.ca/winter-2022-a01/group-16/killer-nano-robots/-/issues/3>

[3]: <https://code.cs.umanitoba.ca/winter-2022-a01/group-16/killer-nano-robots/-/blob/develop/app/src/test/java/com/example/bicepgamers/UnitTest.java>

[4]: <https://code.cs.umanitoba.ca/winter-2022-a01/group-16/killer-nano-robots/-/tree/HaiBranch/app/src/main/java/com/example/bicepgamers/objects>
  

 Exceptional code
----------------
**We didn't have any exceptional code as of yet. In the later iterations we will add some wherever needed**

 Branching
----------
The branching strategy we are using is called a [Git Flow][5]. It is a strategy that uses one main/master branch to keep track of releases and develop branch help integrate features. It is great for smaller teams that don't need **expedite their work**. 

The [graph][6] represents the bigger picture and a [commit][7] to shows how we used the strategy and for what purpose.

[5]: <https://code.cs.umanitoba.ca/winter-2022-a01/group-16/killer-nano-robots#branching-strategy>

[6]: <https://code.cs.umanitoba.ca/winter-2022-a01/group-16/killer-nano-robots/-/network/main>

[7]: <https://code.cs.umanitoba.ca/winter-2022-a01/group-16/killer-nano-robots/-/commit/093a32a86cfd3781307e45a4cc74bb49538a0f23>

  

 SOLID
-----

In this group 1 we looked for SOLID violation and found a [commit][8] that in their patient class, they had several function for profileList. This is a violation of S, as in single responsibility principle, which can be avoided by maybe putting that function in profileListLogic class that holds those functions and call it from there in thier patient class. 

We made an [issue][9] for the violation

[8]: <https://code.cs.umanitoba.ca/winter-2022-a01/group-1/personal_healthcare/-/commit/9c0bd4b03a550e135776b18c057507d0684d22c2>
  
[9]: <https://code.cs.umanitoba.ca/winter-2022-a01/group-1/personal_healthcare/-/issues/22>

Agile Planning
--------------

  

Write a paragraph about any plans that were changed. Did you

'push' any features to iteration 2? Did you change the description

of any Features or User Stories? Have links to any changed or pushed Features

or User Stories.

We decided to push some features to iteration 2, these include [creating a game][10], [push notification][11], showing [exercises suggestions][12] and clear [rest time][13]


[10]: <https://code.cs.umanitoba.ca/winter-2022-a01/group-16/killer-nano-robots/-/issues/22>
[11]: <https://code.cs.umanitoba.ca/winter-2022-a01/group-16/killer-nano-robots/-/issues/7>
[12]: <https://code.cs.umanitoba.ca/winter-2022-a01/group-16/killer-nano-robots/-/issues/19>
[13]: <https://code.cs.umanitoba.ca/winter-2022-a01/group-16/killer-nano-robots/-/issues/20>
