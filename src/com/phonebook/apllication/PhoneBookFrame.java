package com.phonebook.apllication;

import java.util.Scanner;

public class PhoneBookFrame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        PhoneBook phoneBook = new PhoneBook();

        boolean flag = true;

        while(flag) {
            System.out.println("======");
            System.out.println("1.등록");
            System.out.println("2.삭제");
            System.out.println("3.검색");
            System.out.println("4.수정");
            System.out.println("5.목록");
            System.out.println("6.종료");
            System.out.println("======");

            switch(scanner.nextInt()) {
                case 1:
                    System.out.println("이름을 입력하세요 : ");
                    String userName = scanner.next();
                    userName = phoneBook.userSameNameCheck(userName);

                    System.out.println("번호를 입력하세요 : ");
                    String userNumber = scanner.next();
                    userNumber = phoneBook.userNumberCheck(userNumber);

                    System.out.println("이메일을 입력하세요 : ");
                    String userEmail = scanner.next();
                    userEmail = phoneBook.userEmailCheck(userEmail);

                    phoneBook.registered(userName, userNumber, userEmail);
                    break;
                case 2:
                    System.out.println("삭제할 이름을 입력하세요 : ");
                    userName = scanner.next();
                    if(phoneBook.userNameCheck(userName)){
                        phoneBook.deleted(userName);
                    }
                    break;
                case 3:
                    System.out.println("검색할 이름을 입력하세요 : ");
                    userName = scanner.next();
                    if(phoneBook.userNameSearchCheck(userName)){
                        phoneBook.searched(userName);
                    }
                    break;
                case 4:
                    System.out.println("수정하실 이름을 입력하세요. ex)조준범");
                    userName = scanner.next();
                    if(phoneBook.userNameCheck(userName)){
                        phoneBook.modified(userName);
                    }
                    break;
                case 5:
                    phoneBook.listed();
                    break;
                default:
                    flag = false;
            }
        }
        scanner.close();
    }
}
