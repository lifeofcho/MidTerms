package com.todo.service;

import java.util.*;
import java.io.*;
import java.sql.PreparedStatement;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	
	public static void createItem(TodoList list) 
	{
		String title, desc, category, due_date;
		Scanner sc = new Scanner(System.in);
		System.out.print("[Title]\n" + ">> ");
		
		title = sc.next().trim();
		sc.nextLine();
		if (list.isDuplicate(title)) 
		{
			System.out.printf("�̹� ���� ������ �ֽ��ϴ�.");
			return;
		}
		System.out.print("[Category]\n" + ">> ");
		category = sc.next();
		sc.nextLine();
		System.out.println("[Description]\n" + ">> ");
		desc = sc.nextLine().trim();
		System.out.println("[Due_Date]\n" + ">> ");
		due_date = sc.nextLine().trim();

		TodoItem t = new TodoItem(title, desc, category, due_date);
		if(list.addItem(t) > 0)
			System.out.println("TodoList�� �߰��Ǿ����ϴ�.");
	}

	public static void deleteItem(TodoList l)
	{
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println();
		System.out.print("[������ �׸��� ��ȣ�� �Է����ּ���!]\n" + ">> ");
		int num = sc.nextInt();
		if(l.deleteItem(num) > 0)
			System.out.println("�����Ͻ� �������� �����Ͽ����ϴ�!!");
	}
	
	public static void comp(TodoList l)
	{
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println();
		System.out.print("[�Ϸ�� �׸��� ��ȣ�� �Է����ּ���!]\n" + ">> ");
		int num = sc.nextInt();
		if(l.completeItem(num) > 0)
			System.out.println("�����Ͻ� �������� üũ/�Ϸ� �Ͽ����ϴ�.");
	}
	
	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[������ �׸��� ��ȣ�� �Է����ּ���.]\n" + ">> ");
		int num = sc.nextInt();

		System.out.println("[New Title]\n" + ">> ");
		String new_title = sc.next().trim();

		System.out.println("[New Category]\n" + ">> ");
		String new_category = sc.next();
		sc.nextLine();
		System.out.println("[New Description]\n" + ">> ");
		String new_description = sc.nextLine().trim();
		sc.nextLine();
		System.out.println("[New Due Date]\n" + ">> ");
		String new_Duedate = sc.next().trim();
		
		TodoItem t = new TodoItem(new_title, new_description, new_category, new_Duedate);
		t.setId(num);

		if(l.updateItem(t) > 0)
			System.out.println("����Ʈ�� �����Ǿ����ϴ�.");
	}

	public static void find(TodoList l, String keyword)
	{
		int count = 0;
		for(TodoItem item : l.getList(keyword))
		{
			System.out.println(item.toString());
			count++;
		}
		System.out.println("�� " + count + "���� �׸��� ã�ҽ��ϴ�.");
			
	}
	public static void find_cate(TodoList l,String category)
	{
		int count = 0;
		for(TodoItem item : l.getListCategory(category))
		{
			System.out.println(item.toString());
			count++;
		}
		System.out.println("�� " + count + "���� �׸��� ã�ҽ��ϴ�.");
	}
	
	public static void listCateAll(TodoList l)
	{
		int count = 0;
		for(String item : l.getCategories())
		{
			System.out.print(item + " ");
			count++;
		}
		System.out.println("�� " + count + "���� ī�װ��� ��ϵǾ��ֽ��ϴ�");
	}
	public static void ls_comp(TodoList l)
	{
		int count = 0;
		for(TodoItem item : l.getCompletedItems())
		{
			System.out.println(item.toString());
			count++;
		}
		System.out.println("�� " + count + "���� �������� �Ϸ��Ͽ����ϴ�.");
	}


	public static void listAll(TodoList l) 
	{	
		System.out.printf(" [��ü ���, �� %d��]", l.getCount());
		System.out.println();
		
		for (TodoItem item : l.getList()) 
		{
			System.out.println(item.toString());
		}
	}
	
	public static void listAll(TodoList l, String orderby, int ordering)
	{
		System.out.printf("\t[��ü ���, �� %d��]\n", l.getCount());
		for(TodoItem item : l.getOrderedList(orderby, ordering))
		{
			System.out.println(item.toString());
		}
	}
	
//	public static void saveList(TodoList l, String filename)
//	{
//		try
//		{
//			Writer w = new FileWriter(filename);
//			for(TodoItem item: l.getList())
//			{
//				w.write(item.toSaveString());
//			}	w.close();
//		}
//		catch (FileNotFoundException e)
//		{
//			e.printStackTrace();
//		}
//		catch(IOException e)
//		{
//			e.printStackTrace();
//		}
//	}
//	public static void loadList(TodoList l, String filename, HashSet<String> set)
//	{
//		try
//		{
//			BufferedReader br = new BufferedReader(new FileReader(filename));
//			String oneline;
//			int i = 0;
//				while((oneline = br.readLine()) != null)
//				{
//				StringTokenizer st = new StringTokenizer(oneline, "##");
//				String category = st.nextToken();
//				set.add(category);
//				String title = st.nextToken();
//				String desc = st.nextToken();
//				String due_date = st.nextToken();
//				TodoItem t = new TodoItem(title, desc, category, due_date);
//				t.setCurrent_date(st.nextToken());
//				l.addItem(t);
//				i++;
//				}
//			System.out.println(i + "���� �׸��� �о����ϴ�.");
//			br.close();
//		}
//		catch(FileNotFoundException e)
//		{
//			System.out.print(filename + "�� �����ϴ�.");
//			//e.printStackTrace();
//		}
//		catch(IOException e)
//		{
//			e.printStackTrace();
//		}
//	}

//	public static void print(TodoList l, TodoItem t)
//	{
//		System.out.println(l.indexOf(t)+ 1 + ". |" + t.getCategory() + "|" + t.getTitle() + "--" + t.getDesc() + "|||" + "Due_Date >> " + t.getDue_date() + "|||" + t.getCurrent_date());
//	}
	
//	public static void ls_cate(TodoList l, HashSet<String> set)
//	{
//		for(TodoItem item : l.getList())
//		{
//			set.add(item.getCategory());
//		}
//			
//		Iterator<String> iter = set.iterator();
//		while(true)
//		{
//			System.out.print(iter.next());
//			if(!iter.hasNext())	
//				break;
//			System.out.print("/");
//		}
//		System.out.println();
//		System.out.println("�� " + set.size() + "���� ī�װ��� ��ϵǾ��ֽ��ϴ�");
//		set.clear();
//		
//	}
}
