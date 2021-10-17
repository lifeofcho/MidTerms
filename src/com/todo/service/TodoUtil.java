package com.todo.service;

import java.util.*;
import java.io.*;
import java.sql.PreparedStatement;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	
	public static void createItem(TodoList list) 
	{
		String title, desc, category, due_date, place;
		Scanner sc = new Scanner(System.in);
		System.out.print("[Title]\n" + ">> ");
		
		title = sc.next().trim();
		sc.nextLine();
//		if (list.isDuplicate(title)) 
//		{
//			System.out.printf("�̹� ���� ������ �ֽ��ϴ�.");
//			return;
//		}
		System.out.print("[Category]\n" + ">> ");
		category = sc.next();
		sc.nextLine();
		System.out.print("[Description]\n" + ">> ");
		desc = sc.nextLine().trim();
		System.out.print("[Due_Date]\n" + ">> ");
		due_date = sc.nextLine().trim();
		System.out.print("[Place]\n" + ">> ");
		place = sc.nextLine().trim();
		TodoItem t = new TodoItem(title, desc, category, due_date, place);
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
	
	public static void multicheck(TodoList l)
	{
		Scanner s = new Scanner(System.in);
		System.out.print("�� ���� �׸��� �ٷ�ó���?\n" + ">>");
		int num = s.nextInt();
		s.nextLine();
		System.out.print("[�Ϸ��� �׸��� ��ȣ�鸦 �Է����ּ���!]\n" + ">> ");
		int check = 0;
		for(int i = 0; i < num; i++)
		{
			int index = s.nextInt();
			if(l.completeItem(index) > 0)
				check++;
		}
		if(check == num)
			System.out.println("�����Ͻ� �����۵��� �Ϸ��Ͽ����ϴ�.");	
	}
	
	public static void multidel(TodoList l)
	{
		Scanner s = new Scanner(System.in);
		System.out.print("[�� ���� �׸��� �ٷ�ó���?]\n" + ">>");
		int num = s.nextInt();
		s.nextLine();
		System.out.print("[������ �׸���� ��ȣ�鸦 �Է����ּ���!]\n" + ">> ");
		int check = 0;
		for(int i = 0; i < num; i++)
		{
			int index = s.nextInt();
			if(l.deleteItem(index) > 0)
				check++;
		}
		if(check == num)
			System.out.println("�����Ͻ� �����۵��� �Ϸ��Ͽ����ϴ�.");	
	}
	
	public static void uncheckItem(TodoList l)
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Uncheck�� �׸��� ��ȣ�� �Է����ּ���!\n" + ">> ");
		int num = sc.nextInt();
		if(l.uncheck(num) > 0)
			System.out.println("�����Ͻ� �������� �ٽ� �̿Ϸ�� �ٲ�����ϴ�.");
	}
	
	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[������ �׸��� ��ȣ�� �Է����ּ���.]\n" + ">> ");
		int num = sc.nextInt();

		System.out.print("[New Title]\n" + ">> ");
		String new_title = sc.next().trim();

		System.out.print("[New Category]\n" + ">> ");
		String new_category = sc.next().trim();
		sc.nextLine();
		System.out.print("[New Description]\n" + ">> ");
		String new_description = sc.nextLine().trim();
		System.out.print("[New Due Date]\n" + ">> ");
		String new_Duedate = sc.next().trim();
		sc.nextLine();
		System.out.print("[New Place]\n" + ">> ");
		String new_place = sc.nextLine();

		TodoItem t = new TodoItem(new_title, new_description, new_category, new_Duedate, new_place);
		t.setId(num);
		t.setis_Comp(0);
		t.setPriority(0);

		if(l.updateItem(t) > 0)
			System.out.println("����Ʈ�� �����Ǿ����ϴ�.");
	}
	public static void empty(TodoList l)
	{
		Scanner s = new Scanner(System.in);
		System.out.print("[���� �Ϸ�� �������� ����Ʈ���� ���ðڽ��ϱ�? (y/n) ]\n" + ">>");
		String answer = s.next();
		int index = 0;
		int count = 0;
		if(answer.equals("y"))
		{
			for(TodoItem i : l.getList())
			{
				if(i.getis_Comp() == 1)
				{
					index = i.getId();
					l.deleteItem(index);
					count++;
				}
			}	
			if(count > 0)
				System.out.println("�Ϸ��Ͻ� �����۵��� �����߽��ϴ�.");
		}
		else
			System.out.println("�� �Ϸ�� �������� ����Ʈ���� ����� �ʰڽ��ϴ�.");
	}
	public static void is_urgent(TodoList l)
	{
		Scanner s = new Scanner(System.in);
		System.out.print("��� ������ �� ���� ��ȣ�� �Է����ּ���.\n" + ">> ");
		int num = s.nextInt();
		if(l.urgent(num) > 0)
			System.out.println("��� �Ϸ��ؾ� �ϴ� �׸��� �߰��Ǿ����ϴ�.");	
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
	public static void find_place(TodoList l, String place)
	{
		int count = 0;
		for(TodoItem item : l.getListplace(place))
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
		System.out.println();
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
	
	public static void ls_prioritized(TodoList l)
	{
		int count = 0;
		for(TodoItem item :  l.getPrioritizeditems())
		{
			System.out.println(item.toString());
			count++;
		}
		System.out.println("�� " + count + "���� �켱 �Ϸ��ؾ� �� �׸���� �ֽ��ϴ�.");
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
