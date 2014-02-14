Ian Mundy
ian.m.mundy@vanderbilt.edu


The design of my benchmark client is farily straightforward.
It has two methods:
One runs the serial implementation.
One runs the threaded implementation.
There is a private instance of a thread class that runs multiple client
thread simultaneously for the threaded server. While I could have done
this as well for the serial server, it caused no speed change in the serial
execution so I left it as is.
These two methods record the time in nanoseconds at the beginning and end
and return that result to main. Main displays this result to the console.

While the results will vary from run to run as far as execution time,
the multithreaded server is faster than the serial server 100% of the
time when I run the program. The speed difference depends on the number
of threads in the client process.


The multithreaded server is better mostly because it makes sense.
Once the serversocket receives a connection, it should spawn a thread
to do whatever work needs done so it can continue to listen for new 
requests. The abililty to handle the two tasks at the same time allows
the multithreaded server to consistently perform faster when handling
a large number of requests. When I used two threads in the benchmark
client, the MT server appeared to be about 40% faster, but when i used
3 threads it became nearly twice a fast. The advantages of being able
to use multiple client threads that are matched by the server threads
became more obvious as I used more threads.