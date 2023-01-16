package personal.controllers;

import personal.model.Repository;
import personal.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserController {
    private final Repository repository;

    public UserController(Repository repository) {
        this.repository = repository;
    }

    public void saveUser(User user) throws Exception {
        validateUser(user);
        repository.CreateUser(user);
    }

    public User readUser(String userId) throws Exception {
        List<User> users = repository.getAllUsers();
        validationIsHasID(userId);
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }
        throw new Exception("User not found");
    }
    public void deleteUser(String userID) throws Exception{
        List<User> users = repository.getAllUsers();
        validationIsHasID(userID);
        repository.deleteUser(userID);
//        for (User user : users) {
//            if (user.getId().equals(userID)) {
//                repository.deleteUser(user);
//                return;
//            }
//        }

    }

    public List<User> readList() {
        List<User> result = repository.getAllUsers();
        return result;
    }

    public void updateUser(String id, User user) throws Exception {
        validationIsHasID(id);
        user.setId(id);
        validateUserWithID(user);
        repository.updateUser(id, user);
    }
    private void validateUser(User user) throws Exception {

        if(user.getFirstName().contains(" ")){
            throw new Exception("User name has wrong symbol");
        }
        if(user.getLastName().contains(" ")){
            throw new Exception("User last name has wrong symbol");
        }
    }
    private void validateUserWithID(User user) throws Exception {
        validateUser(user);
        if(user.getId().isEmpty()){
            throw new Exception("User hasn't ID");
        }
    }
    public void validationIsHasID(String id) throws Exception {
        List <User> users = repository.getAllUsers();
        for (User i: users) {
            if(i.getId().equals(id)){
                return;
            }
        }
        throw new Exception("No such ID here");
    }

}
