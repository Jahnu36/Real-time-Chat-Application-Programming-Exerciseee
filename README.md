# **Exercise 1**
## **Behavioural Design**
## **1. WeatherStation**
**Description**
Simulates weather changes over time, providing outputs such as temperature and humidity for each simulated time step.

**Sample Output**:

![WhatsApp Image 2024-10-02 at 19 17 58_f46018eb](https://github.com/user-attachments/assets/a112c21d-e5bb-43a7-9687-4338ce9286f8)

## **2. NavigationDemo**
**Description**
Calculates different routes from New York to Los Angeles, including the fastest, shortest, and a route avoiding tolls.

**Sample Output**

![WhatsApp Image 2024-10-02 at 19 18 14_a4df9673](https://github.com/user-attachments/assets/b8ea1b00-82e8-4cca-a821-251031567846)


##  Structural Design

## **1. AdapterPatternDemo**
**Description**
Demonstrates the Adapter Design Pattern by playing various media types and showing adaptability with unsupported formats.

**Sample Output**

![WhatsApp Image 2024-10-02 at 19 19 19_79f4ddc6](https://github.com/user-attachments/assets/52805f82-6534-46e4-9096-c768482e4583)


## **2. CoffeeShop**
**Description**
Interactive Java application simulating a coffee shop order process where the user can add different coffee customizations and see the updated order summary after each addition, concluding with a final order summary.

**Sample Output**

![WhatsApp Image 2024-10-02 at 19 29 41_715ecc10](https://github.com/user-attachments/assets/e863e32f-eaa0-409d-8f9f-d22831856a10)

## Creational Design
## **1. DocumentDemo**
**Description**
Demonstrates the creation of different types of documents (PDF, DOC, TXT) and confirms their creation.

**Sample Output**

![WhatsApp Image 2024-10-02 at 19 18 30_01d76229](https://github.com/user-attachments/assets/c3afb19e-d50f-46f1-8034-988656959c6e)

## **2. LoggerDemo**
**Description**
Demonstrates logging various messages to a log file, providing a simple example of using logging in Java applications.

**Sample Output**
![WhatsApp Image 2024-10-02 at 19 18 52_4614da05](https://github.com/user-attachments/assets/5721d343-c18b-4e75-8767-d2d89ae78551)




# Exercise 2
# **Chat Application - Real-Time Console Chat**

## **Overview**
This Java-based project is a simple real-time chat application that allows users to create and join chat rooms. It implements a client-server architecture where users can interact through a console. The focus of this application is on message exchange, chat room management, and client-server communication.

## **Features**
- **Chat Server**: Handles multiple chat rooms and manages user connections.
- **Chat Client**: Connects to the server, allowing users to join and participate in specific chat rooms.
- **Multi-user Support**: Users can join multiple rooms, and messages are shared across all participants in a room.
- **Concurrency**: Uses Java's concurrent collections to handle multiple chat rooms and users.

## **Project Structure**
The application consists of the following key components:

- **Server**: Manages client connections and handles communication for different chat rooms.
- **Client**: Connects to the server, sends messages, and receives real-time updates from the server.
- **`ClientHandler` (Inner Class)**: Manages each client connected to the server, running on a separate thread for concurrency.

## **Key Classes and Methods**
1. **`ChatApp` (Main Class)**
   - **`startServer()`**: Initializes the server socket on a specified port (5000). Waits for incoming connections and starts a new thread (`ClientHandler`) for each client.
   - **`startClient()`**: Connects to the server and allows users to send and receive messages via console input/output.

2. **`ClientHandler` (Inner Class)**
   - Handles individual client connections.
   - Manages message broadcasting within a chat room.
   - Implements the logic for creating, joining, and leaving chat rooms.

## **How to Run**
### **Requirements**
- **Java Development Kit (JDK 8 or higher)** installed on your system.

### **Steps to Run the Code**
1. **Clone the Repository**:
   ```sh
   git clone https://github.com/Jahnu36/Real-time-Chat-Application-Programming-Exerciseee
2. **Compile the Java Files**: Navigate to the directory containing the Java file and compile it using the javac command:
   ```sh
   cd /path/to/ChatApp
   javac ChatApp.java
3. **Run the Server**: Start the server using the following command:
   ```sh
   java ChatApp server
This command will start the server on port 5000 and wait for client connections.


4. **Run the Client**: Open a new terminal window and run the client:
   ```sh
   java ChatApp 

This will start a chat client, connecting it to the server. Users can then interact with the server and join chat rooms.

## **Usage Examples**
**Example 1: Creating a Chat Room and Messaging**
1. Client 1 starts and creates a room named "RoomA".
2. Client 2 joins "RoomA" and sends a message.
**Output Example**

**Server Side**

![image](https://github.com/user-attachments/assets/a47e4303-a9ea-44bb-a202-0bba7b07a69b)

**Client 1 Console:**

![image](https://github.com/user-attachments/assets/20c78f88-18b3-4637-91d3-b2358b51deca)

**Client 2 Console**

![image](https://github.com/user-attachments/assets/5db1c34c-7e91-4c7a-979c-b2d60c6db0d5)

**Client 1 Console (After message from Client 2)**

![image](https://github.com/user-attachments/assets/2feca7ba-111e-4796-abb3-fe9f6a74fab8)

**Active users in Room 1**

![image](https://github.com/user-attachments/assets/021b396b-5d24-4a2c-8d46-dcbe42b92ff2)


**Example 2: Adding Another Room**
1. Client 3 starts and creates a new room called "RoomB".
2. Client 4 joins "RoomB" and starts a separate conversation.
**Output Example:**

**Client 3 Console:**

![image](https://github.com/user-attachments/assets/0b0573ca-1288-46d7-859a-28e6c50b840f)


**Client 4 Console:**

![image](https://github.com/user-attachments/assets/e0855ca3-b1c5-42d4-84db-5ff67c80cee6)


**Client 3 Console (After message from Client 4):**

![image](https://github.com/user-attachments/assets/8e5c899c-ef39-4b04-bd3d-2251108b37ea)

**Active users in Room 2**

![image](https://github.com/user-attachments/assets/292b3830-33e9-45a1-b0c9-538400b65379)


**Example 3: Multiple Users in the Same Room**
1. Client 5 joins "RoomA" where Client 1 and Client 2 are already present.
2. Client 5 sends a message to everyone in "RoomA".
**Output Example:**

**Client 5 Console:**

![image](https://github.com/user-attachments/assets/4811a26b-0aef-470b-ad89-e150aea91f25)

**Client 1 and Client 2 Consoles:**

![image](https://github.com/user-attachments/assets/b7c7d0e0-0eac-4147-b9cf-cd534e5f63a5)

![image](https://github.com/user-attachments/assets/b74f05d1-538b-40e1-b33d-47b2dad32780)


**Example 4: Disconnecting from the Chat**
1. Client 2 decides to leave "RoomA".
**Output Example:**

**Client 2 Console:**

![image](https://github.com/user-attachments/assets/2610f0a8-31d8-4e60-aa6a-d9b36c889ae9)

**Client 1 and Client 5 Consoles:**

![image](https://github.com/user-attachments/assets/c1afab75-b023-4c5a-a4c9-dbe7d7627fc3)

![image](https://github.com/user-attachments/assets/82491486-0a11-4964-abac-36a31051c0cc)


## **Commands**
Clients can use the following commands:
- create [room_name]: Create a new chat room.
- join [room_name]: Join an existing chat room.
- leave [room_name]: Leave the current chat room.
- !users: To see active users in room
- !quit: Exit the chat application entirely.

## **Logging and Exception Handling** 
- Logging: The server console provides updates on connected clients and errors.
- Exception Handling: The application handles I/O exceptions gracefully, ensuring the server continues running without interruption even if a client disconnects unexpectedly.
## **Design Patterns Utilized**
- Singleton Pattern: The server ensures that the chat room manager is a singleton instance.
- Observer Pattern: Implements real-time updates to clients when a new message is posted in a room.
- Command Pattern: Commands are used to handle client requests, such as joining rooms or sending messages.
## **Author** 
Poluru Reddy Jahanve

