package com.is;

public class RequestHandler extends Thread{
    private Request request;
    private FrontEndSystem fSystem;
    private BackEndSystem bSystem;
    private String name;

    RequestHandler(String name, FrontEndSystem fSystem, BackEndSystem bSystem){
        this.name = name;
        this.fSystem = fSystem;
        this.bSystem = bSystem;
    }

    @Override
    public void run(){
        while(!Thread.currentThread().isInterrupted()) {
            if(fSystem.getQueueSize() > 0)
            try {
               request =  fSystem.getRequest();
               if(request == null) continue;
               bSystem.handleRequest(request);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("Выполнение потока было прервано");
            }
        }
    }
}
