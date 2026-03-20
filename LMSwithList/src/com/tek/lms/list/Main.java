package com.tek.lms.list;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        LMSMenu menu = new LMSMenu(library);
        menu.start();
    }
}
