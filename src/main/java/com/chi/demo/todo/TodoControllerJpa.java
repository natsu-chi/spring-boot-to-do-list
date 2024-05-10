package com.chi.demo.todo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@SessionAttributes("username")
public class TodoControllerJpa {

    private TodoService todoService;
    private TodoRepository todoRepository;

    public TodoControllerJpa(TodoService todoService, TodoRepository todoRepository) {
        super();
        this.todoService = todoService;
        this.todoRepository = todoRepository;
    }

    private String GetUsername(ModelMap model) {
        return (String)model.get("username");
    }

    @RequestMapping("/list-todos")
    public String ListAllTodos(ModelMap model) {
        String username = GetUsername(model);

        List<Todo> todos = todoRepository.findByUsername(username);
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
        todoRepository.deleteById(id);
        return "redirect:list-todos";
    }

    @RequestMapping(value="update-todo", method=RequestMethod.GET)
    public String GotoUpdateTodo(@RequestParam int id, ModelMap model) {
        Todo todo = todoRepository.findById(id).get();
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
        todoRepository.save(todo);
        return "redirect:list-todos";
    }
}
