package com.chi.demo.todo;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("username")
public class TodoController {

    private TodoService todoService;

    public TodoController() {
        super();
        this.todoService = new TodoService();
    }

    private String GetUsername(ModelMap model) {
        return (String)model.get("username");
    }

    @RequestMapping("/list-todos")
    public String ListAllTodos(ModelMap model) {
        List<Todo> todos = todoService.findByUsername("aaa");
        model.addAttribute("todos", todos);
        return "listTodos";
    }

    @RequestMapping(value="/add-todo", method=RequestMethod.GET)
    public String GotoAddTodo(ModelMap model) {
        String username = GetUsername(model);
        Todo todo = new Todo(0, username, "default desc", LocalDate.now(), false);
        model.put("todo", todo);
        return "addTodo";
    }


    // @RequestMapping(value="/add-todo", method=RequestMethod.POST)
    // public String AddTodoPost(@RequestParam String description, ModelMap model) {
    //     String username = (String)model.get("username");
    //     todoService.addTodo(username, description, LocalDate.now(), false);
    //     return "redirect:list-todos";
    // }

    // 取代上面 @RequestParam 參數寫法，包裹成Todo傳入，減少程式碼
    @RequestMapping(value="/add-todo", method=RequestMethod.POST)
    public String AddTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
        if(result.hasErrors()) {
            return "addTodo";
        }
        String username = GetUsername(model);
        todoService.addTodo(username, todo.getDescription(), todo.getTargetDate(), false);
        return "redirect:list-todos";
    }

    @RequestMapping("delete-todo")
    public String DeleteTodo(@RequestParam int id, ModelMap model) {
        todoService.deleteTodo(id);
        return "redirect:list-todos";
    }

    @RequestMapping(value="update-todo", method=RequestMethod.GET)
    public String GotoUpdateTodo(@RequestParam int id, ModelMap model) {
        Todo todo = todoService.findById(id);
        model.addAttribute("todo", todo);
        return "updateTodo";
    }

    @RequestMapping(value="update-todo", method=RequestMethod.POST)
    public String UpdateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
        if(result.hasErrors()) {
            return "updateTodo";
        }

        String username = GetUsername(model);;
        todo.setUsername(username);
        todoService.updateTodo(todo);
        return "redirect:list-todos";
    }
}
