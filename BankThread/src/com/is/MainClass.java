package com.is;

import java.util.Random;

public class MainClass {

    static BackEndSystem bSystem = new BackEndSystem(new Random().nextInt(900000), 4);
    static FrontEndSystem fSystem = new FrontEndSystem();

    public static void main(String[] args) throws InterruptedException {
    Thread client1 = new Client("Клиент №1",new Request(new Random().nextInt(50000), OperationType.values()[new Random().nextInt(2)]), fSystem);
    client1.setName("Клиент №1");

    Thread client2 = new Client("Клиент №2", new Request(new Random().nextInt(50000),OperationType.values()[new Random().nextInt(2)]), fSystem);
    client2.setName("Клиент №2");

    Thread client3 = new Client("Клиент №3",new Request(new Random().nextInt(50000),OperationType.values()[new Random().nextInt(2)]), fSystem);
    client3.setName("Клиент №3");

    Thread client4 = new Client("Клиент №4", new Request(new Random().nextInt(50000),OperationType.values()[new Random().nextInt(2)]), fSystem);
    client4.setName("Клиент №3");

    Thread requestHandler1 = new RequestHandler("Обработчик заявок №1", fSystem, bSystem);
    requestHandler1.setName("Обработчик заявок №1");

    Thread requestHandler2 = new RequestHandler("Обработчик заявок №2", fSystem, bSystem);
    requestHandler2.setName("Обработчик заявок №2");

    client1.start();
    client2.start();
    client3.start();
    client4.start();

    requestHandler1.start();
    requestHandler2.start();
        while(true){
            if(bSystem.getClientsLeft() == 0){

                client1.interrupt();
                client2.interrupt();
                client3.interrupt();
                client4.interrupt();

                requestHandler1.interrupt();
                requestHandler2.interrupt();
                System.out.println("Потоки прерваны");
                break;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
