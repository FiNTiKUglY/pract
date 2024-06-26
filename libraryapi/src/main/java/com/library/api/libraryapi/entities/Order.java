package com.library.api.libraryapi.entities;

import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @Column(name = "id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    @JsonIgnoreProperties(value = {"orders", "handler", "hibernateLazyInitializer"}, allowSetters=true)
    private User user;

    @Column(name = "adress")
    private String adress;
    
    @Column(name = "status")
    private boolean status;

    @ManyToMany()
    @JsonIgnoreProperties(value = {"orders", "handler", "hibernateLazyInitializer"}, allowSetters=true)
    @JoinTable(name="books_orders",
            joinColumns=@JoinColumn(name="order_id",referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="book_id", referencedColumnName="id"))
    private Set<Book> books;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
