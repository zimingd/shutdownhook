# shutdownhook
testing Java shutdown hooks in Docker.

Moral of the story:
Use `CMD exec <command>` instead of `CMD <command>` because Docker only sends the termination signal to the process with pid=1, the latter command runs /bin/sh and forks the `<command>` to a child process. /bin/sh does not forward the termination signal to its children so the process running `<command>` will be unable to terminate gracefully. `exec()` is a system call that replaces the current process image with that of `<command>` so, in this context, the process running `<command>` will have pid=1 and be able to recieve the termination signal.

