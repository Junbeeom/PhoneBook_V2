package com.phonebook.apllication;

import java.util.HashMap;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PhoneBook {
    private int samePersonCnt = 1;

    PhoneBook() {}

    HashMap<String, PhoneUserData> userDataMap = new HashMap<>();

    //등록하기
    public void registered(String userName, String userNumber, String userEmail) {
        Scanner scanner = new Scanner(System.in);

        userName = userSameNameCheck(userName);
        userNumber = userNumberCheck(userNumber);
        userEmail = userEmailCheck(userEmail);

        //등록 일시
        SimpleDateFormat userFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        Date time = new Date();
        String userRegistrationDate = userFormat.format(time);

        userDataMap.put(userName, new PhoneUserData(userName, userNumber, userEmail, userRegistrationDate, "없음"));

        System.out.println();
        System.out.println(userName + "의 정보가 등록 완료 되었습니다. ");
        System.out.println("등록일시 : " + userRegistrationDate);
    }

    //삭제하기
    public void deleted(String userName) {
        Scanner scanner = new Scanner(System.in);

        if(userDataMap.get(userName) == null) {
            System.out.println("존재하지 않는 이름입니다");
        } else {
            System.out.println("삭제 1번, 취소 2번");
            int switchNumber = scanner.nextInt();
            switch (switchNumber) {
                case 1:
                    userDataMap.remove(userName);
                    System.out.println(userName + "의 정보가 삭제되었습니다.");
                    break;
                default:
                    System.out.println("취소 되었습니다");
                    break;
            }
        }
    }

    //검색하기
   public void searched(String userName) {
       boolean flag = false;
       for (String key : userDataMap.keySet()) {
           if(key.contains(userName)) {
               System.out.println("==============================");
               System.out.println("이    름 : " + userDataMap.get(key).getName());
               System.out.println("전화번호 : " + userDataMap.get(key).getNumber());
               System.out.println("메    일 : " + userDataMap.get(key).getEmail());
               System.out.println("등록일시 : " + userDataMap.get(key).getRegistrationDate());
               System.out.println("수정일시 : " + userDataMap.get(key).getModificationDate());
               System.out.println("==============================");
               flag = true;
           }
       }
       if(!flag) {
           System.out.println("등록된 이름이 없습니다.");
       }
   }

    //수정하기
    public void modified(String userName) {
        Scanner scanner = new Scanner(System.in);

        if(userDataMap.get(userName) == null) {
            System.out.println("존재하지 않는 이름입니다");
        } else {
            System.out.println("수정하실 항목을 선택하세요 1.이름 2.전화번호 3.이메일");

            switch (scanner.nextInt()) {
                case 1:
                    System.out.println("변경하실 이름을 입력하세요.");
                    String UserInputNewName = scanner.next();

                    System.out.println("변경 1번, 취소 2번");

                    switch(scanner.nextInt()) {
                        case 1:
                            String newName = userSameNameCheck(UserInputNewName);
                            String modifyTime = modificationDate();

                            userDataMap.get(userName).setName(newName);
                            userDataMap.get(userName).setModifyTime(modifyTime);
                            userDataMap.put(newName, userDataMap.get(userName));

                            userDataMap.remove(userName);

                            System.out.println("수정이 완료되었습니다.\n수정일시 : " + modifyTime);
                            break;

                        default :
                            System.out.println("취소 되었습니다");
                            break;
                    }
                    break;

                //휴대전화 번호 수정
                case 2:
                    System.out.println("변경하실 번호를 입력하세요 : ");
                    String userInputNewNumber = scanner.next();

                    System.out.println("정말 변경 하실 껀가요? 변경 1번, 취소 2번");

                    switch(scanner.nextInt()) {
                        case 1:
                            String newNumber = userNumberCheck(userInputNewNumber);
                            String modifyTime = modificationDate();

                            userDataMap.get(userName).setNumber(newNumber);
                            userDataMap.get(userName).setModifyTime(modifyTime);

                            System.out.println("수정이 완료되었습니다.\n수정일시 : " + modifyTime);
                            break;

                        default :
                            System.out.println("취소 되었습니다");
                            break;
                    }
                    break;
//
                //이메일 주소 변경
                case 3:
                    System.out.println("변경하실 이메일을 입력하세요.");
                    String userInputNewEmail = scanner.next();

                    System.out.println("정말 변경 하실 껀가요? 변경 1번, 취소 2번");

                    switch(scanner.nextInt()) {
                        case 1:
                            String newEmail = userEmailCheck(userInputNewEmail);
                            String modifyTime = modificationDate();

                            userDataMap.get(userInputNewEmail).setEmail(newEmail);
                            userDataMap.get(userInputNewEmail).setModifyTime(modifyTime);

                            System.out.println("수정이 완료되었습니다.\n수정일시 : " + modifyTime);
                            break;
                        default :

                            System.out.println("취소 되었습니다");
                            break;
                    }
            }
        }

    }

    //목록보기
    public void listed() {
        for (PhoneUserData phoneBookList : userDataMap.values()) {
            System.out.println("==============================");
            System.out.println("이    름 : " + phoneBookList.getName());
            System.out.println("전화번호 : " + phoneBookList.getNumber());
            System.out.println("메    일 : " + phoneBookList.getEmail());
            System.out.println("등록일시 : " + phoneBookList.getRegistrationDate());
            System.out.println("수정일시 : " + phoneBookList.getModificationDate());
            System.out.println("==============================");
        }
    }

    //userName의 중복처리를 위한 메소드
    public String userSameNameCheck(String userName) {
        if (userDataMap.get(userName) != null) {
            userName = userName + samePersonCnt;
            samePersonCnt++;

            System.out.println("동명이인이 존재하여 귀하의 성명 " + userName + "로 저장되었습니다.");
        }
        return userName;
    }

    //userNumber의 유효성 검사를 위한 메소드
    public String userNumberCheck(String userNumber) {
        Scanner scanner = new Scanner(System.in);
        char arr[] = userNumber.toCharArray();

        for (int i = 0; i < arr.length; i++) {
            if (Character.isDigit(arr[i])) {
                userNumber = userNumber.replaceAll("[^0-9]", "");
                return userNumber;
            } else {
                System.out.println("숫자만 저장됩니다.");
                break;
            }
        }
        boolean flag = true;
        while (flag) {
            System.out.println("번호를 다시 입력하세요 : ");
            userNumber = scanner.next();

            char arr2[] = userNumber.toCharArray();

            for (int i = 0; i < arr2.length; i++) {
                if (Character.isDigit(arr2[i])) {
                    userNumber = userNumber.replaceAll("[^0-9]", "");
                    flag = false;
                }
            }
        }
        return userNumber;
    }

    //userEmail의 유효성 검사를 위한 메소드
    public String userEmailCheck(String userEmail) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String emailCheck = "^[a-z0-9A-Z._-]*@[a-z0-9A-Z]*.[a-zA-Z.]*$";

            if (userEmail.matches(emailCheck) == true) {
                break;
            } else {
                System.out.println("올바른 형식을 입력하세요. ex)wns_p@naver.com");
                System.out.print("이메일 : ");
                userEmail = scanner.next();
            }
        }

        return userEmail;
    }

    //수정시간 등록을 위한 메소드
    public String modificationDate() {
        SimpleDateFormat userFormat = new SimpleDateFormat ( "yyyy.MM.dd HH:mm:ss");
        Date time = new Date();
        String userModificationDate = userFormat.format(time);

        return userModificationDate;
    }

    //search메서드를 위한 name 유효성 검사 메소드
    public Boolean userNameSearchCheck(String userName) {
        for (String key : userDataMap.keySet()) {
            if(key.contains(userName)) {
                return true;
            }
        }
        System.out.println("등록된 이름이 없습니다.");
        return false;
    }

    //name 유효성 검사를 위한 메소드
    public Boolean userNameCheck(String userName) {

        if(userDataMap.get(userName) == null) {
            System.out.println("존재하지 않는 이름입니다");
            return false;
        }
        return true;
    }
}




