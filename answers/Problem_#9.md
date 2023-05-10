# #9 You have multi-threaded java application where the threads appear to dead lock in production.

### Analysis:
- using tools like `VisualVM` by identifying the thread states & locks from thread dumps
- look for root cause of the deadlock
- command\
`jstack <pid>` for finding the stack trace of a thread
`jvisualvm` to open the visual vm for threads info


### Fix Code:
- fix the code for multi-threaded environment
- use concurrency 
- efficient resource locking / unlocking

