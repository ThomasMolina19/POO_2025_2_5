package com.mycompany.tarea5;

import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;

public class FriendController {

    private final FriendView view;
    private final FriendRepository repository;

    public FriendController(FriendView view, FriendRepository repository) {
        this.view = view;
        this.repository = repository;
        initController();
        // Cargar datos al iniciar para que no esté vacía
        readContacts(); 
    }

    private void initController() {
        view.getBtnCreate().addActionListener(e -> createContact());
        view.getBtnRead().addActionListener(e -> readContacts());
        view.getBtnUpdate().addActionListener(e -> updateContact());
        view.getBtnDelete().addActionListener(e -> deleteContact());
        view.getBtnClear().addActionListener(e -> view.clearInputs());

        // LISTENER DE LA TABLA: Magia de selección
        view.getTable().getSelectionModel().addListSelectionListener((ListSelectionEvent event) -> {
            // Verificamos que la selección haya terminado (evita eventos duplicados)
            if (!event.getValueIsAdjusting() && view.getTable().getSelectedRow() != -1) {
                fillFormFromSelection();
            }
        });
    }

    // Toma los datos de la fila seleccionada y los pone en los TextFields
    private void fillFormFromSelection() {
        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow >= 0) {
            String name = view.getTable().getValueAt(selectedRow, 0).toString();
            String number = view.getTable().getValueAt(selectedRow, 1).toString();
            
            view.setInputs(name, number);
        }
    }

    private void createContact() {
        try {
            String name = view.getNameInput();
            String numberStr = view.getNumberInput();

            if (name.isEmpty() || numberStr.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Complete todos los campos.");
                return;
            }

            long number = Long.parseLong(numberStr);
            repository.addFriend(name, number);
            
            view.clearInputs();
            readContacts(); // Refrescar tabla
            JOptionPane.showMessageDialog(view, "Contacto creado.");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "El número no es válido.");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(view, "Error: " + ex.getMessage());
        }
    }

    private void readContacts() {
        try {
            List<Friend> friends = repository.getAllFriends();
            view.updateTable(friends);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(view, "Error al leer datos: " + ex.getMessage());
        }
    }

    private void updateContact() {
        try {
            String name = view.getNameInput();
            String numberStr = view.getNumberInput();

            if (name.isEmpty() || numberStr.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Seleccione un contacto o escriba el nombre.");
                return;
            }

            long newNumber = Long.parseLong(numberStr);
            repository.updateFriend(name, newNumber);
            
            view.clearInputs();
            readContacts();
            JOptionPane.showMessageDialog(view, "Contacto actualizado.");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Número inválido.");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(view, "Error al actualizar: " + ex.getMessage());
        }
    }

    private void deleteContact() {
        try {
            String name = view.getNameInput();

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Seleccione un contacto para eliminar.");
                return;
            }
            
            int confirm = JOptionPane.showConfirmDialog(view, "¿Eliminar a " + name + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                repository.deleteFriend(name);
                view.clearInputs();
                readContacts();
                JOptionPane.showMessageDialog(view, "Eliminado.");
            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(view, "Error al eliminar: " + ex.getMessage());
        }
    }
}