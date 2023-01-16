package personal.views;

import personal.controllers.UserController;
import personal.model.Repository;
import personal.model.User;

import java.util.List;
import java.util.Scanner;

public class ViewUser {

    private UserController userController;

    public ViewUser(UserController userController) {
        this.userController = userController;
    }

    public void run() throws Exception {
        Commands com = Commands.NONE;

        while (true) {
            String command = prompt("Введите команду: ");
            com = Commands.valueOf(command.toUpperCase());
            if (com == Commands.EXIT) return;
            try {
                switch (com) {
                    case CREATE:
                        userController.saveUser(createUser());
                        break;
                    case READ:
                        String id = prompt("Идентификатор пользователя: ");
                        User user = userController.readUser(id);
                        System.out.println(user);
                        break;
                    case LIST:
                        List<User> lst = userController.readList();
                        lst.forEach(i -> System.out.println(i + "\n"));
                        break;
                    case UPDATE:
                        String numId = prompt("Идентификатор пользователя для замены данных: ");
                        userController.validationIsHasID(numId);
                        userController.updateUser(numId, createUser());
                        break;
                    case DELETE:
                        String numID = prompt("Идентификатор пользователя: ");
                        userController.deleteUser(numID);
                        System.out.println(numID + " удален");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Oops!\n" + e.getMessage());
            }
        }
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

    private User createUser() {
        String firstName = prompt("Имя: ");
        String lastName = prompt("Фамилия: ");
        String phone = prompt("Номер телефона: ");
        User user = new User(firstName, lastName, phone);
        return user;
    }
}
