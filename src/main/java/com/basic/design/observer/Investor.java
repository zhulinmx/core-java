package com.basic.design.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式/监听
 */
public interface Investor {
    public void update(int price);
}


class BeijingInvestor implements Investor {
    private String name;

    public BeijingInvestor(String name) {
        this.name = name;
    }

    @Override
    public void update(int price) {
        System.out.println("Beijing Investor " + this.getName() + " know the stock change to " + price);
    }

    public String getName() {
        return this.name;
    }
}

class ShenyangInvestor implements Investor {
    private String name;

    public ShenyangInvestor(String name) {
        this.name = name;
    }

    @Override
    public void update(int price) {
        System.out.println("Shenyang Investor " + this.getName() + " know the stock change to " + price);
    }

    public String getName() {
        return name;
    }
}


abstract class Stock {
    private List<Investor> investors = new ArrayList();
    protected int price = 0;

    public void addInvestor(Investor i) {
        this.investors.add(i);
    }

    public void delInvestor(Investor i) {
        this.investors.remove(i);
    }

    protected void notifyInvestors(int price) {
        System.out.println("begin to notify the investors.");
        for (Investor i : investors) {
            i.update(price);
        }
    }

    ;

    public abstract void setPrice(int price);
}

class TxStock extends Stock {
    @Override
    public void setPrice(int price) {
        System.out.println("...TxStock change price.");
        this.price = price;
        this.notifyInvestors(price);
    }
}

class AlStock extends Stock {
    @Override
    public void setPrice(int price) {
        System.out.println("...AlStock change price.");
        this.price = price;
        this.notifyInvestors(price);
    }
}

class BookClient {
    public static void main(String[] args) {
        BeijingInvestor bj = new BeijingInvestor("Lindz");
        ShenyangInvestor sy = new ShenyangInvestor("Xun");
        Stock stock1 = new TxStock();
        stock1.addInvestor(bj);
        stock1.addInvestor(sy);
        stock1.setPrice(19);
        stock1.delInvestor(sy);
        stock1.setPrice(25);

        Stock stock2 = new AlStock();
        stock2.addInvestor(bj);
        stock2.setPrice(19);
    }
}

