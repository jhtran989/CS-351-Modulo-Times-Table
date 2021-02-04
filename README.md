# README
## CS 351
### Project 1: Modulo Times Table
#### John Tran

Overall, everything should be working as attended.   

(Note: the comments themselves are kind of general, given how many separate pieces of code there were)   

IMPORTANT: The skeleton from last semester for CS251 was used as a reference for this Modulo Times Table Project   

Primarily, only `public` methods were commented with a Javadoc and other simple methods (like getters and setters) weren't given a comment due to time constraints (since they could be inferred pretty easily)   

Jump comment:   
The `Jump` Button is used to process all changes to the times table number and number of points on the times table circle at once (the changes won't appear as the user types in the `TextField` areas)

Animation Timer comment (`TableAnimationTimer`):   
The implementation of the animation timer in its own class might not have been the best approach since a lot of parameters had to be passed into the constructor to be used in the `run` method. A nested class would probably have made the timer more flexible to changes (using the parameters directly instead of having to pass references into the animation timer object).

Color increment comment (`TableVisualization`):   
The choice of incrementing the blue value for cycling through different colors was chosen arbitrarily. Due to time constraints, I wasn't able to implement a richer color cycling scheme (could have created a local variable `currentColorCycle` to change which color is incremented at each full cycle and maybe even have it as an option for the user to change directly with different styles to choose from).

Favorite times table visualizations comment (`FavoriteVisualization`):   
Instead of a "tour" of the favorite visualizations mentioned in the project spec, a drop down menu was implemented via the `ComboBox` class in JavaFX. There might be errors with the names of the `FavoriteVisualization` objects since the `StringConvertor` used for formatting the selection names was not made robust (the `fromString()` method was left as default).   
In addition, I wasn't able to find an option to create an initial message for the drop down menu, so I found a way to create a "dummy" object to hold the initial message with some default parameters (the initial option could still run, just nothing would happen since I initialized it with only 1 point). The good thing is the event listener doesn't use its parameters when the window first starts up.
