package Forms;

import Data.Database;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignIn extends JDialog{
    private JPanel SignInPanel;
    private JLabel lbRegister;
    private JTextField tfEmail;
    private JPasswordField pfPwd;
    private JButton btnLogin;

    public SignIn(JFrame parent) {

        // define atributos do formulario
        super(parent);
        setTitle("Oliveira Trade");
        setContentPane(SignInPanel);
        setMinimumSize(new Dimension(300, 300));
        setModalityType(ModalityType.APPLICATION_MODAL);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        lbRegister.setForeground(Color.BLUE.darker());
        lbRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        btnLogin.addActionListener(e -> { //botão de login
            String email = tfEmail.getText().trim();
            String password = String.valueOf(pfPwd.getPassword()).trim();
            login(email, password);
        });

        lbRegister.addMouseListener(new MouseAdapter() { //botão de registro
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                SignUp signUp = new SignUp(null);
            }
        });

        setVisible(true);
    }

    private void login(String email, String password) {
        //efetua login

        if (Database.checkUser(email)) {
            if (Database.checkPwd(email, password)) {
                JOptionPane.showMessageDialog(
                        this,
                        "Login efetuado com sucesso!",
                        "",
                        JOptionPane.INFORMATION_MESSAGE
                );

                dispose();
            }
            else {
                JOptionPane.showMessageDialog(
                        this,
                        "Senha incorreta.",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        }
        else {
            JOptionPane.showMessageDialog(
                    this,
                    "Usuário não encontrado.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }
}
