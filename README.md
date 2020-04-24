# IT Project Management Calculator
This is a command-line program to solve project management problems easily

###### Disclaimer
I'm not resposible for any incorrect output :3 I think it is logically correct and I tested it on a bunch of examples.

## Input Format
1. Number of tasks
2. Name and duration pairs of the tasks
3. Number of predecessors + list of predecessors, in the same order the tasks where entered

#### Example
```
5
A 3 B 4 C 2 D 3 E 1
0
0
1 A
2 A B
1 B
```
This input represents 5 tasks:
- **A**, duration of 3, 0 predecessors
- **B**, duration of 4, 0 predecessors
- **C**, duration of 2, 1 predecessor, **A**
- **D**, duration of 3, 2 predecessors, **A** and **B**
- **E**, duration of 1, 1 predecessor, **B**

## Output Format
The output comes as a line for each task, with each line containing:
- Name
- Duration
- Early Start
- Early Finish
- Late Start
- Late Finish
- Total Slack Time
- Free Slack Time
If the line has a `*`, this indicates a critical task.

#### Example
```
 {name='A', duration=3, earlyStart=0, earlyFinish=3, lateStart=1, lateFinish=4, totalSlackTime=1, freeSlackTime=0}
*{name='B', duration=4, earlyStart=0, earlyFinish=4, lateStart=0, lateFinish=4, totalSlackTime=0, freeSlackTime=0}
 {name='C', duration=2, earlyStart=3, earlyFinish=5, lateStart=5, lateFinish=7, totalSlackTime=2, freeSlackTime=2}
*{name='D', duration=3, earlyStart=4, earlyFinish=7, lateStart=4, lateFinish=7, totalSlackTime=0, freeSlackTime=0}
 {name='E', duration=1, earlyStart=4, earlyFinish=5, lateStart=6, lateFinish=7, totalSlackTime=2, freeSlackTime=2}
```
B and D are the critical tasks.
