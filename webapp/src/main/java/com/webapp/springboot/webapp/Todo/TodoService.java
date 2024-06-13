package com.webapp.springboot.webapp.Todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
@Service
public class TodoService {
	private static List<Todo> todos=new ArrayList<>();
	private static int todoscount=3;
	static {
		todos.add(new Todo(1,"Rahul","Learn Aws",LocalDate.now().plusYears(1),false));
		todos.add(new Todo(2,"Shridhar","Learn Devops",LocalDate.now().plusYears(2),false));
		todos.add(new Todo(3,"Rahul","Learn Spring",LocalDate.now().plusYears(2),false));
	}
	public List<Todo> findByUsername(String username)
	{
		Predicate<? super Todo> predicate
		=todo->todo.getUsername().equalsIgnoreCase(username);
	return todos.stream().filter(predicate).toList();	
	}
	public void addTodo(String username,String description,LocalDate targetDate,boolean done)
	{
		Todo todo=new Todo(++todoscount,username,description,targetDate,done);
		todos.add(todo);
	}
	public void deleteById(int id)
	{
		
		Predicate<? super Todo> predicate
		=todo->todo.getId()==id;
		todos.removeIf(predicate);
	}
	public Todo findById(int id) {
		// TODO Auto-generated method stub
		Predicate<? super Todo> predicate
		=todo->todo.getId()==id;
		Todo todo=todos.stream().filter(predicate).findFirst().get();
		return todo;
	}
	public void updateTodo(@Valid Todo todo) {
		
		// TODO Auto-generated method stub
		deleteById(todo.getId());
		todos.add(todo);
		
		
	}
}
