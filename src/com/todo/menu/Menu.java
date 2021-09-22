package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("ToDoList 플래너");
        System.out.println("add: 새로운 ToDo 아이템을 추가하세요 ! ");
        System.out.println("del: 무슨 항목을 삭제하시겠어요 ?");
        System.out.println("edit: 수정할 아이템을 선택해주세요 !");
        System.out.println("ls: ToDo! 할 일이 뭐가 있는 지 볼까요? ");
        System.out.println("ls_name_asc : 리스트를 제목 순으로 정렬합니다.");
        System.out.println("ls_name_desc : 리스트를 제목 역 순으로 정렬합니다.");
        System.out.println("ls_date : 리스트를 날짜 순으로 정렬합니다.");
        System.out.println("exit: 잘 가요! ToDdList를 종료합니다.");
    }
    public static void prompt()
    {
    	System.out.print("무엇을 하시겠어요 ??");
    }
}
