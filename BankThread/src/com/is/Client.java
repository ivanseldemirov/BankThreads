package com.is;

public class Client extends Thread{
    private String clientName;
    private Request request;
    private FrontEndSystem fSystem;

    public Client(String clientName, Request request, FrontEndSystem fSystem){
        this.clientName = clientName;
        this.request = request;
        this.request.setClient(this);
        this.fSystem = fSystem;
    }

    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName() + ": Заявка{clientThreadName='"
                + Thread.currentThread().getName() + "', amount=" + request.getSum()+ "', " +
                "requestType=" + request.getOpType() + "} отправлена в банк");
        try {
            fSystem.addRequest(request);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("Выполнение потока было прервано");
        }
    }
}
