package com.webapp.springboot.webapp.Todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("name")
public class TodoControllerjpa {
	
	private TodoRepository todoRepository;
	//list-todos
	
	public TodoControllerjpa( TodoRepository todoRepository) {
		super();
		this.todoRepository=todoRepository;
	}
	
	@RequestMapping("list-todos")
	public String listallTodos(ModelMap model)
	{
		String username=getLoggedinUsername(model);
		List<Todo> todos=todoRepository.findByUsername(username);
		model.put("todos", todos);
		return "listTodos";
	}

	private String getLoggedinUsername(ModelMap model) {
		org.springframework.security.core.Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
	@RequestMapping(value="add-todo",method=RequestMethod.GET)
	public String showNewTodoPage(ModelMap model)
	{
		String username = getLoggedinUsername(model);
//		bean to form binding
		Todo todo=new Todo(0,username,"",LocalDate.now().plusYears(1),false);
//		or
//		Todo todo=new Todo();
		model.put("todo", todo);
		
		return "todo";
	}
	@RequestMapping(value="add-todo",method=RequestMethod.POST)
	public String addNewTodoPage(ModelMap model,@Valid Todo todo,BindingResult result)
	{
		if(result.hasErrors())
		{
			return "todo";
		}
		String username = getLoggedinUsername(model);
//		form to bean
		todo.setUsername(username);
		todoRepository.save(todo);
		return "redirect:list-todos";
	}
	@RequestMapping("delete-todo")
	public String deleteTodo(@RequestParam int id)
	{
		todoRepository.deleteById(id);
//		todoService.deleteById(id);
		return "redirect:list-todos";
	}
	@RequestMapping(value="update-todo",method=RequestMethod.GET)
	public String showupdatedTodo(@RequestParam int id,ModelMap model)
	
	{
		Todo todo=todoRepository.findById(id).get();
		model.addAttribute("todo",todo);
		return "todo";
	}
	@RequestMapping(value="update-todo",method=RequestMethod.POST)
	public String updateTodo(ModelMap model,@Valid Todo todo,BindingResult result)
	{
		if(result.hasErrors())
		{
			return "todo";
		}
		String username = getLoggedinUsername(model);
//		form to bean
		todo.setUsername(username);
		todoRepository.save(todo);
		return "redirect:list-todos";
	}
}
