package com.test.app.rest.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column
    @JsonIgnore
    private long created;

    @Column
    @JsonIgnore
    private long updated;

    @Column(name = "full_name")
    private String fullName;

    @Column
    private String email;

    @Column
    private String phone;

    @JsonIgnore
    @Column(name = "is_active")
    private boolean isActive = true;

    @PrePersist
    protected void onCreate() {
        created = System.currentTimeMillis();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public long getUpdated() {
        return updated;
    }

    public void setUpdated(long updated) {
        this.updated = updated;
    }

    public String getFull_name() {
        return fullName;
    }

    public void setFull_name(String full_name) {
        if (full_name != null && full_name.length() >= 2 && full_name.length() <= 50)
            this.fullName = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email != null && email.length() >= 2 && email.length() <= 100 && counter(email, '@') == 1)
            this.email = email;
    }

    private int counter(String str, char ch) {
        return (int) str.chars().filter(c -> c == ch).count();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (validPhone(phone))
            this.phone = phone;
    }

    private boolean validPhone(String phone) {
        if (phone == null || phone.length() < 6 || phone.length() > 14 || !phone.startsWith("+")) {
            System.out.println("You should use + at the start of your number");
            return false;
        }

        for (int i = 1; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i))) {
                System.out.println("You can use only digits in your number");
                return false;
            }
        }
        return true;
    }

    public boolean getIs_active() {
        return isActive;
    }

    public void setIs_active(boolean is_active) {
        this.isActive = is_active;
    }
}
