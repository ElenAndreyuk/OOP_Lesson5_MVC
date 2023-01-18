package personal.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileOperationJSON implements FileOperation {
    private String fileName;


    public FileOperationJSON(String fileName) {
        this.fileName = fileName;
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


    @Override
    public List<String> readAllLines() {
        return null;
    }

    public void saveAllLines(List<String> lines) {

        try (FileWriter writer = new FileWriter(fileName, false)) {
            for (String line: lines) {
                UserMapper userMapper = new UserMapper();
                writer.write("{\n");
                writer.write("\"ID\":\"" + userMapper.map(line).getId()+ "\",\n");
                writer.write("\"first name\":\"" + userMapper.map(line).getFirstName() + "\",\n");
                writer.write("\"last name\":" + userMapper.map(line).getLastName() + ",\n");
                writer.write("\"phone\":" + userMapper.map(line).getPhone() + "\n");
                writer.write("}\n");
                writer.flush();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }



}
