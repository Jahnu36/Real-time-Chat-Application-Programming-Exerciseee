
# Real-time-Chat-Application-Programming-Exerciseee
Chat Application - Real-Time Console Chat
Overview
This Java-based project is a simple real-time chat application that allows users to create and join chat rooms. It implements a client-server architecture where users can interact through a console. The focus of this application is on message exchange, chat room management, and client-server communication.

Features
Chat Server: Handles multiple chat rooms and manages user connections.
Chat Client: Connects to the server, allowing users to join and participate in specific chat rooms.
Multi-user Support: Users can join multiple rooms, and messages are shared across all participants in a room.
Concurrency: Uses Java's concurrent collections to handle multiple chat rooms and users.
Project Structure
The application consists of the following key components:

Server: Manages client connections and handles communication for different chat rooms.
Client: Connects to the server, sends messages, and receives real-time updates from the server.
ClientHandler (Inner Class): Manages each client connected to the server, running on a separate thread for concurrency.
Key Classes and Methods
ChatApp (Main Class)

startServer(): Initializes the server socket on a specified port (5000). Waits for incoming connections and starts a new thread (ClientHandler) for each client.
startClient(): Connects to the server and allows users to send and receive messages via console input/output.
ClientHandler (Inner Class)

Handles individual client connections.
Manages message broadcasting within a chat room.
Implements the logic for creating, joining, and leaving chat rooms.
How to Run
Requirements
Java Development Kit (JDK) 8 or higher installed on your system.
Steps to Run the Code
Clone the Repository:

sh
Copy code
git clone <repository-url>
Compile the Java Files: Navigate to the directory containing the Java file and compile it using the javac command:

sh
Copy code
cd /path/to/ChatApp
javac ChatApp.java
Run the Server: Start the server using the following command:

sh
Copy code
java ChatApp server
This command will start the server on port 5000 and wait for client connections.

Run the Client: Open a new terminal window and run the client:

sh
Copy code
java ChatApp
This will start a chat client, connecting it to the server. Users can then interact with the server and join chat rooms.

Example Commands
Create a Chat Room: When connected as a client, users can create a chat room by typing its name.
Join a Chat Room: Users can join an existing room by entering its name.
Send Messages: Once in a chat room, simply type the message and press Enter to broadcast it.
Logging and Exception Handling
Logging: The server console provides updates on connected clients and errors.
Exception Handling: The application handles I/O exceptions gracefully, ensuring the server continues running without interruption even if a client disconnects unexpectedly.
Design Patterns Utilized
Singleton Pattern: The server ensures that the chat room manager is a singleton instance.
Observer Pattern: Implements real-time updates to clients when a new message is posted in a room.
Command Pattern: Commands are used to handle client requests, such as joining rooms or sending messages.
Evaluation Criteria
Code Quality: Adheres to coding best practices, following proper naming conventions, and logical separation of concerns.
Functionality: Full support for creating, joining, and communicating within multiple chat rooms.
Performance: Efficient use of Java's concurrency tools to handle multiple clients simultaneously.
Author
[Your Name]
[Contact Information]
