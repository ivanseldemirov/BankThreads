package com.is;

import java.util.Random;

public class Request {

    private int sum;
    private OperationType opType;
    private Client client;

    public Request(int sum, OperationType opType){
        this.sum = sum;
        this.opType = opType;
    }

    public OperationType getOpType(){
        return opType;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client){
        this.client = client;
    }

    public int getSum(){
        return sum;
    }


}
