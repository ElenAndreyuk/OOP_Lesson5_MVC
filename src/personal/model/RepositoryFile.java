package personal.model;

import java.util.ArrayList;
import java.util.List;

public class RepositoryFile implements Repository {
    private UserMapperTwo mapper = new UserMapperTwo();
    private FileOperationJSON fileOperationJSON;

    public RepositoryFile(FileOperation fileOperation) {
        this.fileOperationJSON = fileOperationJSON;
    }

    @Override
    public List<User> getAllUsers() {
        List<String> lines = fileOperationJSON.readAllLines();
        List<User> users = new ArrayList<>();
        for (String line : lines) {
            users.add(mapper.map(line));
        }
        return users;
    }

    @Override
    public String CreateUser(User user) {

        List<User> users = getAllUsers();
        int max = 0;
        for (User item : users) {
            int id = Integer.parseInt(item.getId());
            if (max < id) {
                max = id;
            }
        }
        int newId = max + 1;
        String id = String.format("%d", newId);
        user.setId(id);
        users.add(user);
        writeDown(users);
        return id;
    }

    @Override
    public void updateUser(String id, User user) {
        List<User> users = getAllUsers();
        User toEdit = users.stream().filter(i -> i.getId().equals(id)).findFirst().get();
        toEdit.setFirstName(user.getFirstName());
        toEdit.setLastName(user.getLastName());
        toEdit.setPhone(user.getPhone());
        writeDown(users);
    }

    @Override
    public void deleteUser(String id) {
        List<User> users = getAllUsers();
        User toDelete = users.stream().filter(i -> i.getId().equals(id)).findFirst().get();
        users.remove(toDelete);
        writeDown(users);
    }

    private void writeDown(List<User> users) {
        List<String> lines = new ArrayList<>();
        for (User item : users) {
            lines.add(mapper.map(item));
        }
        fileOperationJSON.saveAllLines(lines);
    }
}
