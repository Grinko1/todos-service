package com.todo.app.todo.entities;

import com.todo.app.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name="todos")
@ToString
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
    private User user;
}
