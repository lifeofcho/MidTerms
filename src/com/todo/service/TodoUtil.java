package com.todo.service;

import java.util.*;
import java.io.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) 
	{
		String title, desc, category, due_date;
		Scanner sc = new Scanner(System.in);
		System.out.print("[Category]\n" + ">> ");
		
		category = sc.next();
		sc.nextLine();
		System.out.print("[Title]\n" + ">> ");
		title = sc.next();
		if (list.isDuplicate(title)) 
		{
			System.out.printf("�̹� ���� ������ �ֽ��ϴ�.");
			return;
		}
		sc.nextLine();
		
		System.out.println("[Description]\n" + ">> ");
		desc = sc.nextLine().trim();
		System.out.println("[Due_Date]\n" + ">> ");
		due_date = sc.nextLine().trim();
		
		
		TodoItem t = new TodoItem(title, desc, category, due_date);
		list.addItem(t);
		System.out.println("TodoList�� �߰��Ǿ����ϴ�.");
	}

	public static void deleteItem(TodoList l)
	{
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println();
		System.out.print("[������ �׸��� ��ȣ�� �Է����ּ���!]\n" + ">> ");
		int num = sc.nextInt();
		
		for (TodoItem item : l.getList()) 
		{
			if (num == l.indexOf(item) + 1) 
			{
				print(l, item);
				System.out.println("�����Ͻðڽ��ϱ�? (y/n)");
				char ans = sc.next().charAt(0);
				if(ans == 'y')
				{
				item.getCategory();
				l.deleteItem(item);
				System.out.println("�����Ǿ����ϴ�.\n");
				}
				else
				System.out.println("�������� �ʰڽ��ϴ�");
				break;
			}
		}
	}

	public static void find(TodoList l)
	{
		Scanner s = new Scanner(System.in);
		String find = s.nextLine().trim();
		int count = 0;
		for(TodoItem item : l.getList())
		{
			if(item.getTitle().contains(find) || item.getDesc().contains(find))
			{
				print(l, item);
				count++;
			}
		}
		System.out.println("�� " + count + "���� �׸��� ã�ҽ��ϴ�.");
			
		
	}
	public static void find_cate(TodoList l)
	{
		Scanner s= new Scanner(System.in);
		String find = s.nextLine();
		int count = 0;
		for(TodoItem item : l.getList())
		{
			if(item.getCategory().contains(find))
			{
				print(l, item);
				count++;
			}
		}
		System.out.println("�� " + count + "���� �׸��� ã�ҽ��ϴ�.");
	}
	public static void ls_cate(TodoList l, HashSet<String> set)
	{
		for(TodoItem item : l.getList())
		{
			set.add(item.getCategory());
		}
			
		Iterator<String> iter = set.iterator();
		while(true)
		{
			System.out.print(iter.next());
			if(!iter.hasNext())	
				break;
			System.out.print("/");
		}
		System.out.println();
		System.out.println("�� " + set.size() + "���� ī�װ��� ��ϵǾ��ֽ��ϴ�");
		set.clear();
		
	}
	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[������ �׸��� ��ȣ�� �Է����ּ���.]\n" + ">> ");
		int num = sc.nextInt();
		int check = 0;
		for(TodoItem item : l.getList())
		{
			if(num == l.indexOf(item) + 1)
			{
				print(l, item);
				l.deleteItem(item);
				check = 1;
				break;
			}
		}
		if(check == 0)
		{
			System.out.println("��ġ�ϴ� �׸��� ��ȣ�� �����ϴ�. �ٽ� �Է����ּ���.");
			return;
		}

		System.out.println("[New Title]\n" + ">> ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("�̹� ���� ������ �����մϴ�.");
			return;
		}
		System.out.println("[New Category]\n" + ">> ");
		String new_category = sc.next();
		sc.nextLine();
		System.out.println("[New Description]\n" + ">> ");
		String new_description = sc.next().trim();
		sc.nextLine();
		System.out.println("[New Due Date]\n" + ">> ");
		String new_Duedate = sc.next().trim();
		TodoItem t = new TodoItem(new_title, new_description, new_category, new_Duedate);
		l.addItem(t);
		System.out.println("�������� �����Ǿ����ϴ�.");


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
	public static void loadList(TodoList l, String filename, HashSet<String> set)
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String oneline;
			int i = 0;
				while((oneline = br.readLine()) != null)
				{
				StringTokenizer st = new StringTokenizer(oneline, "##");
				String category = st.nextToken();
				set.add(category);
				String title = st.nextToken();
				String desc = st.nextToken();
				String due_date = st.nextToken();
				TodoItem t = new TodoItem(title, desc, category, due_date);
				t.setCurrent_date(st.nextToken());
				l.addItem(t);
				i++;
				}
			System.out.println(i + "���� �׸��� �о����ϴ�.");
			br.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.print(filename + "�� �����ϴ�.");
			//e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void print(TodoList l, TodoItem t)
	{
		System.out.println(l.indexOf(t) + 1 + ". |" + t.getCategory() + "|" + t.getTitle() + "--" + t.getDesc() + "|||" + "Due_Date >> " + t.getDue_date() + "|||" + t.getCurrent_date());
	}
	public static void listAll(TodoList l) 
	{
		boolean empty =  l.isEmpty();
		if(empty == true)
		{
			System.out.print("�� ���� �ƹ��͵� �����ϴ�.");
			return;
		}
	
		System.out.println("[List]");
		for (TodoItem item : l.getList()) 
		{
			print(l, item);
		}
	}
}
