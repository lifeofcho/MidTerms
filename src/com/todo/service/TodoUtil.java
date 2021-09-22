package com.todo.service;

import java.util.*;
import java.io.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.println();
		System.out.println("[To Do]\n" + " >> ");
		
		title = sc.next();
		if (list.isDuplicate(title)) 
		{
			System.out.printf("이미 같은 제목이 있습니다.");
			return;
		}
		sc.nextLine();
		
		System.out.println("[Description] >> ");
		desc = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
		System.out.println("TodoList에 추가되었습니다.");
		System.out.println();
	}

	public static void deleteItem(TodoList l)
	{
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println();
		System.out.print("[지우고 싳은 항목이 있으면 제목을 입력해주세요!]\n" + ">> ");
		String title = sc.next();
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				System.out.println("삭제되었습니다.\n");
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[수정할 제목을 입력해주세요]\n" + ">> ");
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) 
		{
			System.out.println(title + " 이라는 아이템은 존재하지 않습니다.");
			return;
		}

		System.out.println("[New ToDo] >> ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("이미 같은 제목이 존재합니다.");
			return;
		}
		
		System.out.println("[New Description] >> ");
		String new_description = sc.next().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("아이템이 수정되었습니다.");
			}
		}

	}
	public static void saveList(TodoList l, String filename)
	{
		try
		{
			Writer w = new FileWriter(filename);
			for(TodoItem item: l.getList())
			{
				w.write(item.toSaveString());
			}	w.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public static void loadList(TodoList l, String filename)
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String oneline;
			int i = 0;
				while((oneline = br.readLine()) != null)
				{
				StringTokenizer st = new StringTokenizer(oneline, "##");
				String title = st.nextToken();
				String desc = st.nextToken();
				TodoItem t = new TodoItem(title, desc);
				t.setCurrent_date(st.nextToken());
				l.addItem(t);
				i++;
				}
			System.out.println(i + "개의 항목을 읽었습니다.");
			br.close();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public static void listAll(TodoList l) {
		System.out.println("[List] ");
		for (TodoItem item : l.getList()) {
			System.out.println("[To Do] " + item.getTitle() + " & [Description] " + item.getDesc() + " <" + item.getCurrent_date() + ">");
		}
	}
}
