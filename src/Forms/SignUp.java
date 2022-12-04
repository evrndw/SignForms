package Forms;

import Data.Database;
import Data.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class SignUp extends JDialog {
    private JPanel SignUpPanel;
    private JTextField tfName;
    private JTextField tfSurname;
    private JTextField tfEmail;
    private JPasswordField pfPwd;
    private JPasswordField pfConfirmPwd;
    private JButton btnSignUp;
    private JLabel lbLogin;

    public SignUp (JFrame parent) {

        // define atributos do formulario
        super(parent);
        setTitle("Oliveira Trade");
        setContentPane(SignUpPanel);
        setMinimumSize(new Dimension(300, 410));
        setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        lbLogin.setForeground(Color.BLUE.darker());
        lbLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        btnSignUp.addActionListener(e -> {
            try {
                createUser();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        lbLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                SignIn signIn = new SignIn(null);
            }
        });

        setVisible(true);
    }

    private void createUser() throws IOException {
        // cria novo usuario

        String name, surname, email, password, confirmPwd;
        name = tfName.getText();
        surname = tfSurname.getText();
        email = tfEmail.getText();
        password = String.valueOf(pfPwd.getPassword());
        confirmPwd = String.valueOf((pfConfirmPwd.getPassword()));

        User user = new User(name, surname, email, password);

        if (name.isEmpty() || surname.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPwd.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Preencha todos os campos.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (Database.checkUser(email)) {
            JOptionPane.showMessageDialog(
                    null,
                    "Email já cadastrado.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (!password.equals(confirmPwd)) {
            JOptionPane.showMessageDialog(
                    this,
                    "Senhas não conferem.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // adiciona usuario a base de dados
        Database.addUser(user);
        Database.updateCSV(user);

        JOptionPane.showMessageDialog(
                this,
                "Usuário cadastrado com sucesso!",
                "",
                JOptionPane.INFORMATION_MESSAGE
        );

        dispose();
        SignIn signIn = new SignIn(null);
    }
}
