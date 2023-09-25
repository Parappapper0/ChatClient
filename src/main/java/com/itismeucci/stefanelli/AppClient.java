package com.itismeucci.stefanelli;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class AppClient {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Client client = new Client();

        String ip;
        int port;

        System.out.println("Inserisci l'ip");
        ip = scanner.nextLine();
        System.out.println("Inserisci la porta");
        port = scanner.nextInt();

        client.start(ip, port);

        System.out.println(client.receive());
        client.send("Connessione riuscita");

        Thread receive = new ReceivingThread(client);
        receive.run();

        String input = "";

        while (true) {

            input = scanner.nextLine();

            System.out.println("\t a " + input.toUpperCase());

            if (input != "/exit")
                client.send(input);
            else
                break;
        }

        receive.interrupt();
        scanner.close();
    }

    private static class ReceivingThread extends Thread {

        protected Client client;

        public ReceivingThread(Client client) {

            this.client = client;
        }

        @Override
        public void run() {

            String in;

            while (true) {

                in = client.receive();
                if (in != null)
                    System.out.println(in);

                try {

                    wait(5);

                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
            }
        }
    }
}
