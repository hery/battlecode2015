# battlecode2015

Get Started!
============

To set up, move this base directory into your `Battlecode2015` installation and rename it to `teams`.

We can use basic [semantic versioning](http://semver.org/) to manage our submissions, which I'm also tagging as I go (so far) and storing in the `/submissions` folder.

Yay!

![http://collectionofawesome.files.wordpress.com/2012/01/tumblr_lupel430rw1qmmrpjo1_500.gif](http://collectionofawesome.files.wordpress.com/2012/01/tumblr_lupel430rw1qmmrpjo1_500.gif)


Strategy Brainstorming
======================

* Implement some high-level actions to use with deep/reinforcement learning
* Build a deep/reinforcement learning network to figure out the best strategies using those actions

Notes
==========

* Actions can be stored using the [adapter pattern](http://stackoverflow.com/questions/4280727/java-creating-an-array-of-methods)
* It doesn't seem possible to use [memory outside a set of tournament matches](https://www.dropbox.com/s/p705l3mvbr85j0w/Screenshot%202015-01-15%2012.53.26.png?dl=0), which would make training our player in scrimmage matches impossible. Would it make sense to evolve two of our own learning players against each other? xD
