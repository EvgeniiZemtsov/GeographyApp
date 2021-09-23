package com.eugeneze.study.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public void run() {

        try (ServerSocket server = new ServerSocket(8000)) {
            Router router = new Router();
            while (true) {
                try (Socket socket = server.accept();
                     BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                     BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                    new Thread(() -> {
                        try {
                            StringBuilder requestText = new StringBuilder();
                            while (reader.ready()) {
                                String line = reader.readLine();
                                requestText.append(line);
                            }

                            HttpRequest request = new HttpRequest(requestText.toString());
                            String response = router.handle(request);
                            writer.write(response);
                            writer.flush();

                        } catch (IOException exception) {
                            exception.printStackTrace();
                        }
                    }).start();

                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
