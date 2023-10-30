package com.cuscatlan.test.shoppingcartapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public class Rating  implements Serializable {
    public Rating(){}
    public Rating(int id, float rate, int count) {
        this.id = id;
        this.rate = rate;
        this.count = count;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private float rate;
    private int count;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", rate=" + rate +
                ", count=" + count +
                '}';
    }
}
