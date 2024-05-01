package com.todo.app.todo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false)
    private String title;
    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private TodoStatus status;
    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    @Column(name = "user_id",nullable=false)
    private Long userId;
}
