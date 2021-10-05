package com.epam.esm.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.io.Serializable;
import java.util.Objects;

public class Certificate implements Serializable {
    private int id;
    private String name;
    private String description;
    private double price;
    private int duration;
    private String createDate;
    private String lastUpdateDate;
    private String tagName;

    public Certificate() {

    }

    public Certificate(int id) {
        this.id = id;
    }

    public Certificate(String name, double price, String description, int id) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.id = id;
    }

    @JsonCreator
    public Certificate(String name, double price,
                       int duration, String createDate, String lastUpdateDate, String description) {
        this.name = name;
        this.price = price;
        this.duration = duration;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.description = description;
    }

    @JsonCreator
    public Certificate(int id, String name, double price,
                       int duration, String createDate, String lastUpdateDate, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.duration = duration;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.description = description;
    }

    @JsonGetter("id")
    public int getId() {
        return id;
    }

    @JsonSetter("id")
    public void setId(int id) {
        this.id = id;
    }

    @JsonGetter("name")
    public String getName() {
        return name;
    }

    @JsonSetter("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonGetter("description")
    public String getDescription() {
        return description;
    }

    @JsonSetter("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonGetter("price")
    public double getPrice() {
        return price;
    }

    @JsonSetter("price")
    public void setPrice(double price) {
        this.price = price;
    }

    @JsonGetter("duration")
    public int getDuration() {
        return duration;
    }

    @JsonSetter("duration")
    public void setDuration(int duration) {
        this.duration = duration;
    }

    @JsonGetter("createDate")
    public String getCreateDate() {
        return createDate;
    }

    @JsonSetter("createDate")
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @JsonGetter("lastUpdateDate")
    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    @JsonSetter("lastUpdateDate")
    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Certificate)) return false;
        Certificate that = (Certificate) o;
        return getId() == that.getId() &&
                Double.compare(that.getPrice(), getPrice()) == 0 &&
                getDuration() == that.getDuration() &&
                getName().equals(that.getName()) &&
                getDescription().equals(that.getDescription()) &&
                getCreateDate().equals(that.getCreateDate()) &&
                getLastUpdateDate().equals(that.getLastUpdateDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getPrice(), getDuration(), getCreateDate(), getLastUpdateDate());
    }

    @Override
    public String toString() {
        return "Certificate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", duration=" + duration +
                ", createDate='" + createDate + '\'' +
                ", lastUpdateDate='" + lastUpdateDate + '\'' +
                '}';
    }
}
