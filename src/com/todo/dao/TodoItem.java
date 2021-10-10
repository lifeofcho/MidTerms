package com.todo.dao;

import java.text.SimpleDateFormat; 
import java.util.Date;

public class TodoItem {
    private int id;
	private String title;
    private String desc;
    private String current_date;
    private String category;
    private String due_date;
    private int is_comp;


    public TodoItem(String title, String desc, String category, String due_date)
    {
        this.title=title;
        this.desc=desc;
        this.category = category;
        this.due_date = due_date;
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
        this.current_date= f.format(new Date());
    }
    
    @Override
	public String toString() 
    {
    	if(is_comp == 1)
    	{	
    		if(id < 10)
    			return " " + id + " " + category + "| " + title + "[V]" +  " -- " + desc + " -- " + current_date + " -- " + due_date;
    		else
    			return id + " " + category + "| " + title + "[V]" + " -- " + desc + " -- " + current_date + " -- " + due_date;
    	}
    	
    	else
    	{
    		if(id < 10)
    			return " " + id + " " + category + "| " + title  +  " -- " + desc + " -- " + current_date + " -- " + due_date;
    		else
    			return id + " " + category + "| " + title + " -- " + desc + " -- " + current_date + " -- " + due_date;
    	}
	}
    public String getCategory()
    {
		return category;
	}

	public void setCategory(String category) 
	{
		this.category = category;
	}

	public String getDue_date() 
	{
		return due_date;
	}

	public void setDue_date(String due_date) 
	{
		this.due_date = due_date;
	}

	public String getTitle() 
	{
        return title;
    }
    public String toSaveString()
    {
    	return category + "##" + title + "##" + desc + "##" + due_date + "##"+ current_date + "\n";
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }
    
	public void setId(int id)
    {
    	this.id = id;
    }
    public int getId()
    {
    	return id;
    }
    public String getDesc() 
    {
        return desc;
    }

    public void setDesc(String desc) 
    {
        this.desc = desc;
    }

    public String getCurrent_date() 
    {
        return current_date;
    }

    public void setCurrent_date(String current_date) 
    {
        this.current_date = current_date;
    }
    public int getis_Comp()
    {
    	return is_comp;
    }
    public void setis_Comp(int is_comp)
    {
    	this.is_comp = is_comp;
    }
}
