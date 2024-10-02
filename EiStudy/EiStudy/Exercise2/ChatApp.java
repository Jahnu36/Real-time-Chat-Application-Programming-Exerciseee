import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class ChatApp {
    private static final int PORT = 5000;
    private static Map<String, Set<ClientHandler>> chatRooms = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("server")) {
            startServer();
        } else {
            startClient();
        }
    }

    private static void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Chat server is running on port " + PORT);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void startClient() {
        try (
            Socket socket = new Socket("localhost", PORT);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
        ) {
            System.out.println("Connected to chat server. Enter your username:");
            String username = stdIn.readLine();
            out.println(username);

            System.out.println("Enter room name to join:");
            String roomName = stdIn.readLine();
            out.println(roomName);

            // Start a new thread to handle incoming messages
            new Thread(() -> {
                String serverMessage;
                try {
                    while ((serverMessage = in.readLine()) != null) {
                        System.out.println(serverMessage);
                    }
                } catch (IOException e) {
                    System.out.println("Lost connection to server");
                }
            }).start();

            // Main thread handles user input
            System.out.println("You can now chat. Type '!users' to see active users or '!quit' to exit.");
            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                if (userInput.equalsIgnoreCase("!quit")) {
                    break;
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Unknown host: localhost");
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to localhost");
        }
    }

    static void broadcastMessage(String roomName, String message, ClientHandler sender) {
        chatRooms.getOrDefault(roomName, Collections.emptySet()).forEach(client -> {
            if (client != sender) {
                client.sendMessage(message);
            }
        });
    }

    static void addClientToRoom(String roomName, ClientHandler client) {
        chatRooms.computeIfAbsent(roomName, k -> ConcurrentHashMap.newKeySet()).add(client);
    }

    static void removeClientFromRoom(String roomName, ClientHandler client) {
        Set<ClientHandler> room = chatRooms.get(roomName);
        if (room != null) {
            room.remove(client);
            if (room.isEmpty()) {
                chatRooms.remove(roomName);
            }
        }
    }

    static List<String> getActiveUsers(String roomName) {
        return chatRooms.getOrDefault(roomName, Collections.emptySet()).stream()
                .map(ClientHandler::getUsername)
                .sorted()
                .toList();
    }

    static class ClientHandler extends Thread {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;
        private String username;
        private String roomName;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                username = in.readLine();
                roomName = in.readLine();

                addClientToRoom(roomName, this);
                broadcastMessage(roomName, username + " has joined the chat.", this);

                String message;
                while ((message = in.readLine()) != null) {
                    if (message.equalsIgnoreCase("!users")) {
                        sendMessage("Active users in " + roomName + ": " + String.join(", ", getActiveUsers(roomName)));
                    } else if (message.equalsIgnoreCase("!quit")) {
                        break;
                    } else {
                        broadcastMessage(roomName, username + ": " + message, this);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    removeClientFromRoom(roomName, this);
                    broadcastMessage(roomName, username + " has left the chat.", this);
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        void sendMessage(String message) {
            out.println(message);
        }

        String getUsername() {
            return username;
        }
    }
}