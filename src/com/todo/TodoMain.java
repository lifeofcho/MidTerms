	package com.todo;

import java.util.HashSet;
import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		HashSet<String> set = new HashSet<String>();
		boolean isList = false;
		boolean quit = false;
		TodoUtil.loadList(l, "todolist.txt", set);
		Menu.displaymenu();
		do {
			isList = false;
			Menu.prompt();
			System.out.println();
			String choice = sc.next();
			switch (choice) {
				
			case "find":
				TodoUtil.find(l);
				break;
			case "find_cate":
				TodoUtil.find_cate(l);
				break;
			case "help":
				Menu.displaymenu();
				break;
			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;
			case "ls_cate":
				TodoUtil.ls_cate(l, set);
				break;
			case "ls_name_asc":
				l.sortByName();
				isList = true;
				break;

			case "ls_name_desc":
				l.sortByName();
				l.reverseList();
				isList = true;
				break;
				
			case "ls_date":
				l.sortByDate();
				isList = true;
				break;
			case "ls_date_desc":
				l.sortByDate();
				l.reverseList();
				isList = true;
				break;
			case "exit":
				quit = true;
				TodoUtil.saveList(l, "todolist.txt");
				break;

			default:
				System.out.println("please enter one of the above mentioned command");
				break;
			}
			if(isList) TodoUtil.listAll(l);
		} while (!quit);
	}
}
