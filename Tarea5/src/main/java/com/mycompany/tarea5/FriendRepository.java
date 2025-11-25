package com.mycompany.tarea5;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class FriendRepository {

    private static final String FILE_NAME = "friendsContact.txt";

    public void addFriend(String name, long number) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(FILE_NAME, "rw")) {
            raf.seek(raf.length());
            String contact = name + "!" + number + System.lineSeparator();
            raf.writeBytes(contact);
        }
    }

    // AHORA DEVUELVE OBJETOS FRIEND, NO STRINGS
    public List<Friend> getAllFriends() throws IOException {
        List<Friend> friendsList = new ArrayList<>();
        File file = new File(FILE_NAME);
        
        if (!file.exists()) return friendsList;

        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            String line;
            while ((line = raf.readLine()) != null) {
                String[] parts = line.split("!");
                if (parts.length == 2) {
                    try {
                        String name = parts[0];
                        long number = Long.parseLong(parts[1]);
                        friendsList.add(new Friend(name, number));
                    } catch (NumberFormatException e) {
                        // Ignorar líneas corruptas
                    }
                }
            }
        }
        return friendsList;
    }

    public void updateFriend(String searchName, long newNumber) throws IOException {
        File originalFile = new File(FILE_NAME);
        File tempFile = new File("temp.txt");

        if (!originalFile.exists()) return;

        try (RandomAccessFile raf = new RandomAccessFile(originalFile, "r");
             RandomAccessFile tempRaf = new RandomAccessFile(tempFile, "rw")) {

            String line;
            while ((line = raf.readLine()) != null) {
                String[] parts = line.split("!");
                if (parts.length == 2) {
                    String name = parts[0];
                    if (name.equals(searchName)) {
                        String updatedContact = name + "!" + newNumber + System.lineSeparator();
                        tempRaf.writeBytes(updatedContact);
                    } else {
                        tempRaf.writeBytes(line + System.lineSeparator());
                    }
                }
            }
        }
        deleteAndRename(originalFile, tempFile);
    }

    public void deleteFriend(String nameToDelete) throws IOException {
        File originalFile = new File(FILE_NAME);
        File tempFile = new File("temp.txt");

        if (!originalFile.exists()) return;

        try (RandomAccessFile raf = new RandomAccessFile(originalFile, "r");
             RandomAccessFile tempRaf = new RandomAccessFile(tempFile, "rw")) {

            String line;
            while ((line = raf.readLine()) != null) {
                String[] parts = line.split("!");
                if (parts.length == 2) {
                    String name = parts[0];
                    if (!name.equals(nameToDelete)) {
                        tempRaf.writeBytes(line + System.lineSeparator());
                    }
                }
            }
        }
        deleteAndRename(originalFile, tempFile);
    }

    private void deleteAndRename(File original, File temp) throws IOException {
        if (original.delete()) {
            if (!temp.renameTo(original)) {
                throw new IOException("Error al procesar el archivo temporal.");
            }
        } else {
            throw new IOException("No se pudo borrar el archivo original.");
        }
    }
}