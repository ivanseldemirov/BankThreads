package com.is;

import java.util.ArrayDeque;
import java.util.Queue;

public class FrontEndSystem {
    private Queue<Request> requestsQueue = new ArrayDeque<>();


    public synchronized void addRequest(Request request) throws InterruptedException{
        while (requestsQueue.size() == 2)
            wait();
        requestsQueue.add(request);
        System.out.println();
        notifyAll();
    }

    public int getQueueSize(){
        return requestsQueue.size();
    }

    public synchronized Request getRequest() throws InterruptedException {
        Request request = null;
        if(requestsQueue.size() == 0)
            return null;
        try {
            request = requestsQueue.remove();
            System.out.println(Thread.currentThread().getName() + ": получена заявка на обработку по клиенту - " + request.getClient().getName());
            return request;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            notifyAll();
        }
        return request;
    }
}
