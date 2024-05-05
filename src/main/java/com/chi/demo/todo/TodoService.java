package com.chi.demo.todo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<>();

    private static int todosCount = 0;

    static {
        todos.add(new Todo(++todosCount, "aaa", "learn AWS", LocalDate.now().plusWeeks(1), false));
        todos.add(new Todo(++todosCount, "bbb", "learn Azure", LocalDate.now().plusYears(1), false));
        todos.add(new Todo(++todosCount, "aaa", "learn GCP", LocalDate.now().plusYears(1), false));
        todos.add(new Todo(++todosCount, "aaa", "learn Docker", LocalDate.now().plusYears(1), false));
    }

    public List<Todo> findByUsername(String username) {
//        Predicate <? super Todo> predicate = todo -> todo.getUsername().equals(username);
//        return todos.stream().filter(predicate).toList();
        return todos;
    }

    public void addTodo(String username, String description, LocalDate targetDate, Boolean done) {
        todos.add(new Todo(++todosCount, username, description, targetDate, done));
    }

    public void deleteTodo(int id) {
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        todos.removeIf(predicate);
    }

    public Todo findById(int id) {
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        Todo todo = todos.stream().filter(predicate).findFirst().get();
        return todo;
    }

    public void updateTodo(@Valid Todo todo) {
        deleteTodo(todo.getId());
        todos.add(todo);
    }
}
