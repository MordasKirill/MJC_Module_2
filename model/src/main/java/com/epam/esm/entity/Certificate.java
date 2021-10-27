package com.epam.esm.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Certificate entity can be serialized
 * Contains of:
 * int id;
 * String name;
 * String description;
 * double price;
 * int duration;
 * String createDate;
 * String lastUpdateDate;
 * String tagName;
 */
public class Certificate implements Serializable {
    private Integer id;
    @Size(max = 45, min = 1)
    private String name;
    @Size(max = 45, min = 1)
    private String description;
    @Min(1)
    private Double price;
    @Min(1)
    private Integer duration;
    private String createDate;
    private String lastUpdateDate;
    private List<String> tagNames;

    public Certificate() {
    }

    public Certificate(Integer id) {
        this.id = id;
    }

    public Certificate(String name, Double price,
                       String description, Integer id) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.id = id;
    }

    public Certificate(String name, Double price, String description, List<String> tagNames, Integer duration) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.tagNames = tagNames;
        this.duration = duration;
    }

    @JsonCreator
    public Certificate(@JsonProperty("name") String name, @JsonProperty("price") Double price,
                       @JsonProperty("description") String description, @JsonProperty("id") Integer id,
                       @JsonProperty("tagName") List<String> tagNames, @JsonProperty("duration") Integer duration) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.id = id;
        this.tagNames = tagNames;
        this.duration = duration;
    }

    public Certificate(String name, Double price,
                       Integer duration, String createDate, String lastUpdateDate, String description) {
        this.name = name;
        this.price = price;
        this.duration = duration;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.description = description;
    }

    public Certificate(Integer id, String name, Double price,
                       Integer duration, String createDate, String lastUpdateDate, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.duration = duration;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.description = description;
    }

    @JsonGetter("id")
    public Integer getId() {
        return id;
    }

    @JsonSetter("id")
    public void setId(Integer id) {
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
    public Double getPrice() {
        return price;
    }

    @JsonSetter("price")
    public void setPrice(Double price) {
        this.price = price;
    }

    @JsonGetter("duration")
    public Integer getDuration() {
        return duration;
    }

    @JsonSetter("duration")
    public void setDuration(Integer duration) {
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

    public List<String> getTagNames() {
        return tagNames;
    }

    public void setTagNames(List<String> tagNames) {
        this.tagNames = tagNames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Certificate)) return false;
        Certificate that = (Certificate) o;
        return Objects.equals(getId(), that.getId()) &&
                Double.compare(that.getPrice(), getPrice()) == 0 &&
                Objects.equals(getDuration(), that.getDuration()) &&
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
