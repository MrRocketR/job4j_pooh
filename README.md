A simple socket displaying information from different modes

The program receives POST and GET requests.

The parameters specify the type of operation (queue and topic).

The queue is a common queue for all clients.

Topic is an individual queue.

The program outputs the queue value on the basis of a GET request.

After the output the queue is cleared via poll(). 



