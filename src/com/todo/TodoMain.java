	package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		boolean isList = false;
		boolean quit = false;
//		l.alter();
//		l.importData("todolist.txt");
		Menu.displaymenu();
		do {
			isList = false;
			Menu.prompt();
			System.out.println();
			String choice = sc.next();
			switch (choice) 
			{
			case "empty":
				TodoUtil.empty(l);
				break;
			case "multicheck":
				TodoUtil.multicheck(l);
				break;
			case "multidel":
				TodoUtil.multidel(l);
				break;
			case "urgent":
				TodoUtil.is_urgent(l);
				break;
			case "comp":
				TodoUtil.comp(l);
				break;
			case "uncheck":
				TodoUtil.uncheckItem(l);
				break;
			case "find":
				sc.nextLine();
				String keyword = sc.nextLine().trim();
				TodoUtil.find(l, keyword);
				break;
			case "find_cate":
				sc.nextLine();
				String category = sc.nextLine().trim();
				TodoUtil.find_cate(l, category);
				break;
			case "find_place":
				sc.nextLine();
				String place = sc.nextLine().trim();
				TodoUtil.find_place(l, place);
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
			case "update":
				TodoUtil.updateItem(l);
				break;
			case "ls":
				TodoUtil.listAll(l);
				break;
			case "ls_cate":
				TodoUtil.listCateAll(l);
				break;
			case "ls_priority":
				TodoUtil.ls_prioritized(l);
				break;
			case "ls_comp":
				TodoUtil.ls_comp(l);
				break;
			case "ls_name":
				System.out.println("제목순으로 정렬합니다.");
				TodoUtil.listAll(l, "title", 1);
				break;
			case "ls_name_desc":
				System.out.println("제목역순으로 정렬합니다.");
				TodoUtil.listAll(l, "title", 0);
				break;
			case "ls_date":
				System.out.println("날짜순으로 정렬합니다.");
				TodoUtil.listAll(l, "due_date", 1);
				break;
			case "ls_date_desc":
				System.out.println("제목순으로 정렬합니다.");
				TodoUtil.listAll(l, "due_date", 0);
				break;
			case "exit":
				quit = true;
				break;
			default:
				System.out.println("다시 한번 입력해주새요!!!");
				break;
			}
			if(isList) TodoUtil.listAll(l);
		} while (!quit);
	}
}
