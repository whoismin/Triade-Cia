/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.securelogincia;

import java.awt.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.*;

public class SecureLoginCIA extends JFrame {

    private JTextField campoUsuario;
    private JPasswordField campoSenha;
    private JTextArea areaResultado;

    private static final String usuarioCadastrado = "yasmin";
    private static String senhaHashArmazenada;
    private static String hashIntegridadeOriginal;

    private static int tentativasFalhas = 0;
    private static final int LIMITE_TENTATIVAS = 3;
    private static boolean sistemaBloqueado = false;

    public SecureLoginCIA() {
        senhaHashArmazenada = gerarHashSHA256("123456");

        String dadosUsuario = usuarioCadastrado + senhaHashArmazenada;
        hashIntegridadeOriginal = gerarHashSHA256(dadosUsuario);

        setTitle("SecureLogin CIA - Segurança da Informação");
        setSize(520, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel painel = new JPanel();
        painel.setLayout(null);
        painel.setBackground(new Color(15, 23, 42));

        JLabel titulo = new JLabel("SecureLogin CIA");
        titulo.setBounds(145, 30, 300, 35);
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setForeground(new Color(34, 197, 94));
        painel.add(titulo);

        JLabel subtitulo = new JLabel("Aplicação da Tríade CIA em Java");
        subtitulo.setBounds(140, 65, 300, 25);
        subtitulo.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitulo.setForeground(Color.WHITE);
        painel.add(subtitulo);

        JLabel labelUsuario = new JLabel("Usuário:");
        labelUsuario.setBounds(80, 125, 150, 25);
        labelUsuario.setForeground(Color.WHITE);
        painel.add(labelUsuario);

        campoUsuario = new JTextField();
        campoUsuario.setBounds(80, 150, 350, 35);
        painel.add(campoUsuario);

        JLabel labelSenha = new JLabel("Senha:");
        labelSenha.setBounds(80, 200, 150, 25);
        labelSenha.setForeground(Color.WHITE);
        painel.add(labelSenha);

        campoSenha = new JPasswordField();
        campoSenha.setBounds(80, 225, 350, 35);
        painel.add(campoSenha);

        JButton botaoLogin = new JButton("Entrar");
        botaoLogin.setBounds(80, 285, 350, 40);
        botaoLogin.setBackground(new Color(34, 197, 94));
        botaoLogin.setForeground(Color.BLACK);
        botaoLogin.setFont(new Font("Arial", Font.BOLD, 16));
        painel.add(botaoLogin);

        areaResultado = new JTextArea();
        areaResultado.setBounds(80, 345, 350, 110);
        areaResultado.setEditable(false);
        areaResultado.setLineWrap(true);
        areaResultado.setWrapStyleWord(true);
        areaResultado.setBackground(new Color(30, 41, 59));
        areaResultado.setForeground(Color.WHITE);
        areaResultado.setFont(new Font("Arial", Font.PLAIN, 13));
        painel.add(areaResultado);

        JLabel dadosTeste = new JLabel("Teste: usuário yasmin | senha 123456");
        dadosTeste.setBounds(125, 465, 300, 25);
        dadosTeste.setForeground(new Color(148, 163, 184));
        painel.add(dadosTeste);

        botaoLogin.addActionListener(e -> realizarLogin());

        add(painel);
    }

    private void realizarLogin() {
        if (sistemaBloqueado) {
            areaResultado.setText("Sistema bloqueado por excesso de tentativas.\n"
                    + "Disponibilidade protegida contra força bruta.");
            return;
        }

        String usuarioDigitado = campoUsuario.getText();
        String senhaDigitada = new String(campoSenha.getPassword());

        String senhaDigitadaHash = gerarHashSHA256(senhaDigitada);

        if (usuarioDigitado.equals(usuarioCadastrado)
                && senhaDigitadaHash.equals(senhaHashArmazenada)) {

            String resultadoIntegridade = verificarIntegridade();

            areaResultado.setText("Login realizado com sucesso!\n\n"
                    + resultadoIntegridade + "\n\n"
                    + "CIA demonstrada:\n"
                    + "Confidencialidade: senha protegida por SHA-256.\n"
                    + "Integridade: verificação por hash.\n"
                    + "Disponibilidade: limite de tentativas aplicado.");

            JOptionPane.showMessageDialog(this,
                    "Login realizado com sucesso!",
                    "Acesso permitido",
                    JOptionPane.INFORMATION_MESSAGE);

        } else {
            tentativasFalhas++;

            int restantes = LIMITE_TENTATIVAS - tentativasFalhas;

            if (tentativasFalhas >= LIMITE_TENTATIVAS) {
                sistemaBloqueado = true;
                areaResultado.setText("Usuário ou senha incorretos.\n"
                        + "Tentativas restantes: 0\n\n"
                        + "Sistema bloqueado.\n"
                        + "Disponibilidade protegida contra força bruta.");

                JOptionPane.showMessageDialog(this,
                        "Sistema bloqueado após 3 tentativas incorretas.",
                        "Bloqueio de segurança",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                areaResultado.setText("Usuário ou senha incorretos.\n"
                        + "Tentativas restantes: " + restantes);

                JOptionPane.showMessageDialog(this,
                        "Usuário ou senha incorretos.",
                        "Erro de login",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private String verificarIntegridade() {
        String dadosAtuais = usuarioCadastrado + senhaHashArmazenada;
        String hashAtual = gerarHashSHA256(dadosAtuais);

        if (hashAtual.equals(hashIntegridadeOriginal)) {
            return "Integridade confirmada: os dados não foram alterados.";
        } else {
            return "Alerta: os dados foram modificados.";
        }
    }

    private static String gerarHashSHA256(String texto) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(texto.getBytes());

            StringBuilder hexString = new StringBuilder();

            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);

                if (hex.length() == 1) {
                    hexString.append('0');
                }

                hexString.append(hex);
            }

            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao gerar hash SHA-256", e);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SecureLoginCIA().setVisible(true);
        });
    }
}