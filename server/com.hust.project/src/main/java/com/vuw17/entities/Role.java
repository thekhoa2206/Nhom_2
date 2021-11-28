package com.vuw17.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "role")
public class Role extends BaseEntity{

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "description", length = 100)
    private String description;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "roles")
    private List<User> users = new ArrayList<User>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
