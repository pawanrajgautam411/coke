# #5 You have a java process running on a Linux EC2 in production that is running out of memory daily in production even though there are 16 GB allocated to the JVM.

### Identify memory leaks:
- using tools like `jProfiler` & `jvisualvm` we can check the memory usage and garbage collectors efficiency

### JVM options:
- configure memory sizes for heap, stack, etc.
- garbage collector logs
- Xmx - maximum heap size
- Xms - initial heap size
- XX:MaxPermSize - maximum permanent generation size


### Possible code optimizations:
- modify the code to fix a memory leak if necessary

### Scaling horizontally:
- scaling the application horizontally by adding more instances