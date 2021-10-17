package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("ToDoList 플래너");
        System.out.println("add: 새로운 ToDo 아이템을 추가하세요 ! ");
        System.out.println("del: 무슨 항목을 삭제하시겠어요 ?");
        System.out.println("update: 수정할 아이템을 선택해주세요 !");
        System.out.println("comp: 완료한 항목이 있으신가요?");
        System.out.println("uncheck: 완료한 항목을 다시 미완료로 바꿀 수 있습니다.");
        System.out.println("emtpy: 완료한 항목을 전부 리스트에서 지울 수 있습니다.");
        System.out.println("urgent: 즉시 끝내야 할 항목을 선택할 수 있습니다.");
        System.out.println("multidel: 여러개의 항목을 삭제할 수 있습니다.");
        System.out.println("multicheck: 여러개의 항목을 완료할 수 있습니다.");
        System.out.println("find: 원하시는 제목, 내용을 키워드로 검색할 수 있습니다.");
        System.out.println("find_cate: 원하시는 카테고리를 키워드로 검색할 수 있습니다.");
        System.out.println("find_place: 원하시는 장소를 키워드로 검색할 수 있습니다.");
        System.out.println("ls: ToDo! 할 일이 뭐가 있는 지 볼까요?");
        System.out.println("ls_cate: 카테고리 종류를 보여드립니다.");
        System.out.println("ls_comp: 완료된 항목들만 보여드립니다.");
        System.out.println("ls_priority: 즉시 끝내야 할 항목들만 보여드립니다.");
        System.out.println("ls_name : 리스트를 제목 순으로 정렬합니다.");
        System.out.println("ls_name_desc : 리스트를 제목 역 순으로 정렬합니다.");
        System.out.println("ls_date : 리스트를 날짜 순으로 정렬합니다.");
        System.out.println("ls_date_desc: 리스트 날짜 역 순으로 정렬합니다.");
        System.out.println("help : 메뉴를 다시 한번 띄어드릴게요.");
        System.out.println("exit: 잘 가요! ToDdList를 종료합니다.");
    }
    public static void prompt()
    {
    	System.out.print("무엇을 하시겠어요 ??");
    }
}
