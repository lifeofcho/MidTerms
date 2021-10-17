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
//			System.out.printf("이미 같은 제목이 있습니다.");
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
			System.out.println("TodoList에 추가되었습니다.");
	}

	public static void deleteItem(TodoList l)
	{
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println();
		System.out.print("[삭제할 항목의 번호를 입력해주세요!]\n" + ">> ");
		int num = sc.nextInt();
		if(l.deleteItem(num) > 0)
			System.out.println("지목하신 아이템을 삭제하였습니다!!");

	}
	
	public static void comp(TodoList l)
	{
		
		Scanner sc = new Scanner(System.in);
		System.out.println();
		System.out.print("[완료된 항목의 번호를 입력해주세요!]\n" + ">> ");
		int num = sc.nextInt();
		if(l.completeItem(num) > 0)
			System.out.println("지목하신 아이템을 체크/완료 하였습니다.");
	}
	
	public static void multicheck(TodoList l)
	{
		Scanner s = new Scanner(System.in);
		System.out.print("몇 개의 항목을 다루시나요?\n" + ">>");
		int num = s.nextInt();
		s.nextLine();
		System.out.print("[완료한 항목의 번호들를 입력해주세요!]\n" + ">> ");
		int check = 0;
		for(int i = 0; i < num; i++)
		{
			int index = s.nextInt();
			if(l.completeItem(index) > 0)
				check++;
		}
		if(check == num)
			System.out.println("지목하신 아이템들을 완료하였습니다.");	
	}
	
	public static void multidel(TodoList l)
	{
		Scanner s = new Scanner(System.in);
		System.out.print("[몇 개의 항목을 다루시나요?]\n" + ">>");
		int num = s.nextInt();
		s.nextLine();
		System.out.print("[삭제할 항목들의 번호들를 입력해주세요!]\n" + ">> ");
		int check = 0;
		for(int i = 0; i < num; i++)
		{
			int index = s.nextInt();
			if(l.deleteItem(index) > 0)
				check++;
		}
		if(check == num)
			System.out.println("지목하신 아이템들을 완료하였습니다.");	
	}
	
	public static void uncheckItem(TodoList l)
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Uncheck할 항목의 번호를 입력해주세요!\n" + ">> ");
		int num = sc.nextInt();
		if(l.uncheck(num) > 0)
			System.out.println("지목하신 아이템을 다시 미완료로 바뀌었습니다.");
	}
	
	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[수정할 항목의 번호를 입력해주세요.]\n" + ">> ");
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
			System.out.println("리스트가 수정되었습니다.");
	}
	public static void empty(TodoList l)
	{
		Scanner s = new Scanner(System.in);
		System.out.print("[정말 완료된 아이템을 리스트에서 비우시겠습니까? (y/n) ]\n" + ">>");
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
				System.out.println("완료하신 아이템들을 삭제했습니다.");
		}
		else
			System.out.println("네 완료된 아이템을 리스트에서 비우지 않겠습니다.");
	}
	public static void is_urgent(TodoList l)
	{
		Scanner s = new Scanner(System.in);
		System.out.print("즉시 끝내야 할 일의 번호를 입력해주세요.\n" + ">> ");
		int num = s.nextInt();
		if(l.urgent(num) > 0)
			System.out.println("즉시 완료해야 하는 항목이 추가되었습니다.");	
	}
	
	public static void find(TodoList l, String keyword)
	{
		int count = 0;
		for(TodoItem item : l.getList(keyword))
		{
			System.out.println(item.toString());
			count++;
		}
		System.out.println("총 " + count + "개의 항목을 찾았습니다.");
			
	}
	public static void find_cate(TodoList l,String category)
	{
		int count = 0;
		for(TodoItem item : l.getListCategory(category))
		{
			System.out.println(item.toString());
			count++;
		}
		System.out.println("총 " + count + "개의 항목을 찾았습니다.");
	}
	public static void find_place(TodoList l, String place)
	{
		int count = 0;
		for(TodoItem item : l.getListplace(place))
		{
			System.out.println(item.toString());
			count++;
		}
		System.out.println("총 " + count + "개의 항목을 찾았습니다.");
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
		System.out.println("총 " + count + "개의 카테고리가 등록되어있습니다");
	}
	
	public static void ls_comp(TodoList l)
	{
		int count = 0;
		for(TodoItem item : l.getCompletedItems())
		{
			System.out.println(item.toString());
			count++;
		}
		System.out.println("총 " + count + "개의 아이템을 완료하였습니다.");
	}
	
	public static void ls_prioritized(TodoList l)
	{
		int count = 0;
		for(TodoItem item :  l.getPrioritizeditems())
		{
			System.out.println(item.toString());
			count++;
		}
		System.out.println("총 " + count + "개의 우선 완료해야 할 항목들이 있습니다.");
	}

	public static void listAll(TodoList l) 
	{	
		System.out.printf(" [전체 목록, 총 %d개]", l.getCount());
		System.out.println();
		
		for (TodoItem item : l.getList()) 
		{
			System.out.println(item.toString());
		}
	}
	
	public static void listAll(TodoList l, String orderby, int ordering)
	{
		System.out.printf("\t[전체 목록, 총 %d개]\n", l.getCount());
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
//			System.out.println(i + "개의 항목을 읽었습니다.");
//			br.close();
//		}
//		catch(FileNotFoundException e)
//		{
//			System.out.print(filename + "이 없습니다.");
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
//		System.out.println("총 " + set.size() + "개의 카테고리가 등록되어있습니다");
//		set.clear();
//		
//	}
}
