package com.mycompany.tarea5;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FriendView extends JFrame {

    private JTextField txtName;
    private JTextField txtNumber;
    
    // Componentes de Tabla
    private JTable table;
    private DefaultTableModel tableModel;
    
    private JButton btnCreate;
    private JButton btnRead;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnClear; // Nuevo botón para limpiar formulario

    public FriendView() {
        setTitle("Gestor CRUD Profesional");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        initComponents();
    }

    private void initComponents() {
        // --- Panel Superior (Formulario) ---
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Detalles del Contacto"));
        
        formPanel.add(new JLabel("Nombre (ID):"));
        txtName = new JTextField();
        formPanel.add(txtName);

        formPanel.add(new JLabel("Número:"));
        txtNumber = new JTextField();
        formPanel.add(txtNumber);

        // Agregamos un botón para limpiar campos manualmente
        formPanel.add(new JLabel("")); // Espaciador
        btnClear = new JButton("Limpiar Campos");
        formPanel.add(btnClear);

        // --- Panel Central (Tabla) ---
        // Definimos las columnas
        String[] columnNames = {"Nombre", "Número"};
        // Modelo con 0 filas iniciales
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacemos que la tabla no sea editable directamente
            }
        };
        
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Solo seleccionar una fila
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Lista de Contactos (Seleccione para editar)"));

        // --- Panel Inferior (Botones CRUD) ---
        JPanel buttonPanel = new JPanel(new FlowLayout());
        btnCreate = new JButton("Create");
        btnRead = new JButton("Read"); // Ahora actúa como "Refrescar"
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");

        buttonPanel.add(btnCreate);
        buttonPanel.add(btnRead);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);

        // --- Agregar al Frame ---
        add(formPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Método para llenar la tabla con datos
    public void updateTable(List<Friend> friends) {
        // Limpiar tabla actual
        tableModel.setRowCount(0);
        
        // Agregar filas
        for (Friend f : friends) {
            tableModel.addRow(new Object[]{f.getName(), f.getNumber()});
        }
    }

    // Getters
    public String getNameInput() { return txtName.getText().trim(); }
    public String getNumberInput() { return txtNumber.getText().trim(); }
    
    public void setInputs(String name, String number) {
        txtName.setText(name);
        txtNumber.setText(number);
    }
    
    public void clearInputs() {
        txtName.setText("");
        txtNumber.setText("");
        table.clearSelection();
    }

    public JButton getBtnCreate() { return btnCreate; }
    public JButton getBtnRead() { return btnRead; }
    public JButton getBtnUpdate() { return btnUpdate; }
    public JButton getBtnDelete() { return btnDelete; }
    public JButton getBtnClear() { return btnClear; }
    public JTable getTable() { return table; }
}