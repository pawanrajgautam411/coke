# #7 You are running a web application on Jetty and Apache on a Linux operating system, and you want to make sure no one can exploit any unknown vulnerability in the site you created to gain root access to the Linux Operating System.

### Non-root user:
- non-root user accounts with the minimal access to run the application
- create users for each user who can access the application
- no sudo users

### File & Directory permissions:
- `chmod` command to define file & directory permissions as read, write, executables
- `chown` change the owner
- `ssh-keygen` to generate key pairs

### Authentication:
- SSH key authentication


### Launch Script
- dedicated user for launching the scripts
- use of environment variables & relative paths
- logging 
