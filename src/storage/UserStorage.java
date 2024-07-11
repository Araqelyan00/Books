package storage;

import model.User;

public class UserStorage {
    private static User[] users = new User[10];
    private static int size = 0;

    public static void add(User author){
        if (users.length == size){
            extend();
        }
        users[size++] = author;
    }

    private static void extend(){
        User[] temp = new User[users.length + 10];
        for (int i = 0; i < size; i++){
            temp[i] = users[i];
        }
        users = temp;
    }

    public static void printUsers(){
        for (int i = 0; i < size; i++){
            System.out.println(users[i].toString());
        }
        System.out.println();
    }

    public boolean isEmpty(User[] array){
        return (size == 0);
    }

    public void add(int index, User author){
        if (index < 0 || index > size){
            System.out.println("Index out of bounds.\n");
        }else{
            for (int i = index; i < users.length - 1; i++){
                users[i + 1] = users[i];
                users[index] = author;
            }
            System.out.println("User has been added.\n");
        }
    }

    public int getSize(){
        return size;
    }

    public void deleteUserByIndex(int index){
        if (index < 0 || index > size){
            System.out.println("Index out of bounds.\n");
        } else {
            for (int i = index; i < size; i++){
                users[i] = users[i + 1];
            }
            size--;
            System.out.println("User has been deleted.\n");
        }
    }

    public User getUserByEmail(String email){
        for (int i = 0; i < size; i++){
            if(users[i].getEmail().equals(email)){
                return users[i];
            }
        }
        return null;
    }
}
