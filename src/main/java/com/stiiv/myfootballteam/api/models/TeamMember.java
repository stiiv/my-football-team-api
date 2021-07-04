package com.stiiv.myfootballteam.api.models;

import java.util.Random;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "members")
public class TeamMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Integer id;

    @Column(name = "name", length = 50, nullable = false)
    @NotEmpty
    @Size(min = 3)
    private String name;

    public TeamMember() {}

    public TeamMember(String name) {
        this.id = new Random().nextInt(Integer.SIZE - 1);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
