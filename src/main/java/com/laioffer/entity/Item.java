package com.laioffer.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import java.io.Serializable;

import javax.persistence.*;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "items")
public class Item implements Serializable {
    private static final long serialVersionUID = 5186013952828648626L;
    @Id
    @Column(name = "item_id")
    private String id;
    @Column(name = "name")
    private String title;
    @Column(name = "address")
    private String location;

    @Column(name = "image_url")
    private String companyLogo;
    @Column(name = "url")
    private String url;
    @Transient
    private String description;
    @ElementCollection
    private List<String> keywords;
    @Transient
    private boolean favorite;
    @JsonProperty("id")
    public String getId() {
        return id;
    }
    @JsonProperty("title")
    public String getTitle() {
        return title;
    }
    @JsonProperty("location")
    public String getLocation() {
        return location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return getFavorite() == item.getFavorite() && Objects.equals(getId(), item.getId()) && Objects.equals(getTitle(), item.getTitle()) && Objects.equals(getLocation(), item.getLocation()) && Objects.equals(getCompanyLogo(), item.getCompanyLogo()) && Objects.equals(getUrl(), item.getUrl()) && Objects.equals(getDescription(), item.getDescription()) && Objects.equals(getKeywords(), item.getKeywords());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getLocation(), getCompanyLogo(), getUrl(), getDescription(), getKeywords(), getFavorite());
    }

    @JsonProperty("company_logo")
    public String getCompanyLogo() {
        return companyLogo;
    }
    @JsonProperty("url")
    public String getUrl() {
        return url;
    }
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }
    public boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
    public Item() {
    }

    public Item(String id, String title, String location, String companyLogo, String url, String description, List<String> keywords, boolean favorite) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.companyLogo = companyLogo;
        this.url = url;
        this.description = description;
        this.keywords = keywords;
        this.favorite = favorite;
    }



}
