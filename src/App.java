import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class App {
    private List<Contact> contacts = new ArrayList<>();

    private JTextField nomField, adresseField, telField, mailField, descriptionField;
    private DefaultListModel<Contact> contactListModel;
    private JList<Contact> contactList;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            App carnet = new App();
            carnet.createAndShowGUI();
        });
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Carnet d'adresses");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Création des composants
        nomField = new JTextField(20);
        adresseField = new JTextField(20);
        telField = new JTextField(20);
        mailField = new JTextField(20);
        descriptionField = new JTextField(20);

        JButton ajouterButton = new JButton("Ajouter");
        ajouterButton.addActionListener(new AjouterAction());

        contactListModel = new DefaultListModel<>();
        contactList = new JList<>(contactListModel);
        contactList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        contactList.addListSelectionListener(e -> displayContactDetails(contactList.getSelectedValue()));

        // Mise en page
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Nom:"), gbc);
        gbc.gridx = 1;
        panel.add(nomField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Adresse:"), gbc);
        gbc.gridx = 1;
        panel.add(adresseField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Téléphone:"), gbc);
        gbc.gridx = 1;
        panel.add(telField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        panel.add(mailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Description:"), gbc);
        gbc.gridx = 1;
        panel.add(descriptionField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(ajouterButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 6;
        panel.add(new JScrollPane(contactList), gbc);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    private void displayContactDetails(Contact contact) {
        if (contact != null) {
            nomField.setText(contact.getNom());
            adresseField.setText(contact.getAdresse());
            telField.setText(contact.getTel());
            mailField.setText(contact.getMail());
            descriptionField.setText(contact.getDescription());
        } else {
            clearFields();
        }
    }

    private void clearFields() {
        nomField.setText("");
        adresseField.setText("");
        telField.setText("");
        mailField.setText("");
        descriptionField.setText("");
    }

    private class AjouterAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nom = nomField.getText();
            String adresse = adresseField.getText();
            String tel = telField.getText();
            String mail = mailField.getText();
            String description = descriptionField.getText();

            Contact contact = new Contact(nom, adresse, tel, mail, description);
            contacts.add(contact);
            contactListModel.addElement(contact);
            clearFields();
        }
    }

    private class Contact {
        private String nom;
        private String adresse;
        private String tel;
        private String mail;
        private String description;

        public Contact(String nom, String adresse, String tel, String mail, String description) {
            this.nom = nom;
            this.adresse = adresse;
            this.tel = tel;
            this.mail = mail;
            this.description = description;
        }

        public String getNom() {
            return nom;
        }

        public String getAdresse() {
            return adresse;
        }

        public String getTel() {
            return tel;
        }

        public String getMail() {
            return mail;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return nom;
        }
    }
}
