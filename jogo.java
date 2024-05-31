import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JogoDaVelha extends JFrame implements ActionListener {
    private JButton[][] botoes;
    private boolean turnoX;
    private boolean jogoFinalizado;

    public JogoDaVelha() {
        setTitle("Jogo da Velha");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);

        botoes = new JButton[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botoes[i][j] = new JButton();
                botoes[i][j].setFont(new Font("Arial", Font.PLAIN, 24));
                botoes[i][j].addActionListener(this);
                add(botoes[i][j]);
            }
        }

        turnoX = true;
        jogoFinalizado = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (jogoFinalizado) return;

        JButton botaoClicado = (JButton) e.getSource();
        if (botaoClicado.getText().isEmpty()) {
            if (turnoX) {
                botaoClicado.setText("X");
            } else {
                botaoClicado.setText("O");
            }
            turnoX = !turnoX;

            verificarVencedor();
        }
    }

    private void verificarVencedor() {
        String[][] tabuleiro = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tabuleiro[i][j] = botoes[i][j].getText();
            }
        }

        
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[i][0].equals(tabuleiro[i][1]) && tabuleiro[i][1].equals(tabuleiro[i][2])) {
                if (!tabuleiro[i][0].isEmpty()) {
                    exibirVencedor(tabuleiro[i][0]);
                    return;
                }
            }
            if (tabuleiro[0][i].equals(tabuleiro[1][i]) && tabuleiro[1][i].equals(tabuleiro[2][i])) {
                if (!tabuleiro[0][i].isEmpty()) {
                    exibirVencedor(tabuleiro[0][i]);
                    return;
                }
            }
        }

        if (tabuleiro[0][0].equals(tabuleiro[1][1]) && tabuleiro[1][1].equals(tabuleiro[2][2])) {
            if (!tabuleiro[0][0].isEmpty()) {
                exibirVencedor(tabuleiro[0][0]);
                return;
            }
        }
        if (tabuleiro[0][2].equals(tabuleiro[1][1]) && tabuleiro[1][1].equals(tabuleiro[2][0])) {
            if (!tabuleiro[0][2].isEmpty()) {
                exibirVencedor(tabuleiro[0][2]);
                return;
            }
        }

        
        boolean empate = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j].isEmpty()) {
                    empate = false;
                    break;
                }
            }
        }
        if (empate) {
            JOptionPane.showMessageDialog(this, "Empate!");
            jogoFinalizado = true;
        }
    }

    private void exibirVencedor(String jogador) {
        JOptionPane.showMessageDialog(this, "Jogador " + jogador + " venceu!");
        jogoFinalizado = true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new JogoDaVelha().setVisible(true);
        });
    }
}
