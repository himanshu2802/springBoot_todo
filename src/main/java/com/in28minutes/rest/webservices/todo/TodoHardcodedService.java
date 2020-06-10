package com.in28minutes.rest.webservices.todo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TodoHardcodedService {

	public static List<Todo> todos = new ArrayList<Todo>();
	static long counter = 0;
	
	static {
		todos.add(new Todo(++counter, "Learn Angular", new Date(), false));
		todos.add(new Todo(++counter, "Become an Intermediate", new Date(), false));
		todos.add(new Todo(++counter, "Learn to bind Angular with Spring", new Date(), false));
		todos.add(new Todo(++counter, "Create End-to-End Application", new Date(), false));
		todos.add(new Todo(++counter, "Learn Spring Security", new Date(), false));
	}
	
	public List<Todo> findAll() {
		return todos;
	}
	
	public Todo findById(long todoId) {
		
		return ((Todo) todos.stream()
				.filter(todo -> todo.getId() == todoId)
				.findAny()
				.orElse(null));
//		for(Todo todo : todos) {
//			if(todo.getId() == todoId) {
//				return todo;
//			}
//		}
//		return null;
	}
	
	public Todo save(Todo todo) {
		System.out.println("todo Id: "+todo.getId());
		if(todo.getId() == -1 || todo.getId() == 0) {
			todo.setId(++counter);
			todos.add(todo);
		} else {
			deleteById(todo.getId());
			todos.add(todo);
		}
		return todo;
	}
	
	public Todo deleteById(long todoId) {
		Todo todo = findById(todoId);
		if(todo == null) {
			return null;
		}else {
			todos.remove(todo);
			return todo;
		}
	}

}
