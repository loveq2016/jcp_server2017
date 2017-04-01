package com.jucaipen.test;

import java.io.IOException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ParseHtml {

	
	public static void main(String[] args) {
		try {
			Document doc=Jsoup.connect("http://www.iximo.com/febook/bookstore_list.php?categoryType=all&categoryID=377").get();
		    Elements books = doc.getElementsByClass("bookstore_booklist");
		    for (int i = 0; i < books.size(); i++) {
				Element book = books.get(i);
				System.out.println("b:"+book.toString());
				Elements a = book.getElementsByTag("img");
				System.out.println(a.toString());
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
