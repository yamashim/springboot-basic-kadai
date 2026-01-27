package com.example.springkadaitodo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.springkadaitodo.entity.ToDo;
import com.example.springkadaitodo.repository.ToDoRepository;

@Service
public class ToDoService {
	private final ToDoRepository todoRepository;
	
	public ToDoService(ToDoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}
	
	// 新規todoの登録メソッド
	/*
    public void addToDo(String title, String priority, String status) {
        // タイトル名の未入力チェック（空欄はNG）
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("todoのタイトル名を入力してください。");
        }

        // タイトル名の重複チェック（完全一致はNG）
        if (!todoRepository.findByTitle(title).isEmpty()) {
            throw new IllegalArgumentException("そのタイトル名は既に使用されています。");
        }

        // todo登録用のエンティティを作成
        ToDo todo = new ToDo();
        todo.setTitle(title);
        todo.setPriority(priority);
        todo.setStatus(status);

        // ユーザーの登録
        todoRepository.save(todo);
    }
    */

	public List<ToDo> getAllTodos() {
		return todoRepository.findAll();
	}


}
