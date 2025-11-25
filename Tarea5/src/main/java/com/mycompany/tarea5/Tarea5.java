
package com.mycompany.tarea5;

import javax.swing.SwingUtilities;

/**
 *
 * @author thomasmolinamolina
 */
public class Tarea5 {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FriendView view = new FriendView();
            FriendRepository repository = new FriendRepository();
            new FriendController(view, repository);
            view.setVisible(true);
        });
    }
}