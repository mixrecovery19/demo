package com.bit235.model;
//class for the article model. this simply sets the title and content of each article.
// for example, if we wanted to add an author field, or a date field, we would apply that here.
public class Article {

    private String title;
    private String content;

    public Article() {}

    public String getTitle() { 
        return title; 
    }

    public void setTitle(String title) { 
        this.title = title; 
    }

    public String getContent() { 
        return content; 
    }

    public void setContent(String content) { 
        this.content = content; 
    }
}