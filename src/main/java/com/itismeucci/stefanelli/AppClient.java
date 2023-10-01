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

        client.send("questo in uppercase");

        System.out.println(client.receive());

        scanner.close();
    }
}
