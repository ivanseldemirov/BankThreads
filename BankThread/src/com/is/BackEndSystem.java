package com.is;

import java.util.Random;

public class BackEndSystem {
    private long bankBalance;
    private int clientsLeft;

    public BackEndSystem(long bankBalance, int clientsLeft){
        this.bankBalance = bankBalance;
        this.clientsLeft = clientsLeft;
    }

    public void increaseBankBalance(int sum){
        bankBalance += sum;
    }

    public void decreaseBankBalance(int sum){
        bankBalance -= sum;
    }

    public void decreaseClients(){
        clientsLeft--;
    }

    public int getClientsLeft(){
        return clientsLeft;
    }

    public void handleRequest(Request request){
        if(request.getOpType().equals(OperationType.CREDIT)){
            if(bankBalance > request.getSum()){
                decreaseBankBalance(request.getSum());
                System.out.println("Бэк Система: Заявка{clientThreadName='" + request.getClient().getName() + "'," +
                        " amount=" + request.getSum() + "', requestType=" + request.getOpType() + "} " +
                        "УСПЕШНО ВЫПОЛНЕНА. Получена от " + Thread.currentThread().getName()+". Баланс Банка: " + bankBalance);
            }
            else{
                System.out.println("Бэк Система: Заявка{clientThreadName='" + request.getClient().getName() + "', amount="
                        + request.getSum() + "', requestType=" + request.getOpType() + "} НЕ ВЫПОЛНЕНА. Получена от "
                        + Thread.currentThread().getName() + ". Сумма больше баланса банка. Баланс Банка: "+ bankBalance);
            }
        }
        else{
            increaseBankBalance(request.getSum());
            System.out.println("Бэк Система: Заявка{clientThreadName='"+request.getClient().getName()+"', amount="
                    +request.getSum()+"', requestType="+request.getOpType()+"} УСПЕШНО ВЫПОЛНЕНА. Получена от "
                    + Thread.currentThread().getName()+". Баланс Банка: "+ bankBalance);
        }
        decreaseClients();
    }

}
