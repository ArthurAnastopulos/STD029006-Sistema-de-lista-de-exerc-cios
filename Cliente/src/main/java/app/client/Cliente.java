package app.client;

import app.AppDist;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {
    private static String serverName = "127.0.0.1";
    private static int port = 12345;
    private static final String OBJDISTNAME = "MyApp";

    public static void main(String[] args) {

        try {
            serverName = args[0];

            System.out.println("> Conectando no servidor "+ serverName);
            Registry registro = LocateRegistry.getRegistry(serverName, port);
            AppDist stub =  (AppDist) registro.lookup(OBJDISTNAME);
            Scanner teclado = new Scanner(System.in);

            boolean isRunning = true;
            while (isRunning) {
                System.out.println("----------------");
                System.out.println("Digite 1 - Para login | 2 - Sair");
                System.out.print("opcao: ");
                String opcao = teclado.nextLine();
                switch (opcao) {
                    case "1":
                        System.out.println("Digite sua matricula e senha");
                        System.out.print("Matricula: ");
                        String matricula = teclado.nextLine();
                        System.out.print("Senha: ");
                        String senha = teclado.nextLine();
                        if (stub.login(matricula, senha)) {
                            System.out.println("Usuário Logado.");
                            app(stub);
                        } else
                            System.out.println("Usuário incorreto.");
                        break;
                    case "2":
                        System.out.println("Encerando o progama");
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Opcao invalida");
                        break;
                }
            }
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void app(AppDist stub) throws RemoteException {
        boolean logout = true;
        while (logout) {
            System.out.println("----------------");
            System.out.println("Digite 1 - Iniciar Lista de Exercicio | 2 - Continuar Lista | 3 - Logout");
            Scanner teclado = new Scanner(System.in);
            System.out.print("opcao: ");
            String opcao = teclado.nextLine();
            switch (opcao) {
                case "1":
                    stub.startQuiz();
                    if(stub.isQuizOver()) {
                        System.out.println("Lista de Exercicio Iniciada!");
                        for (int i = 1; i <= 5; i++) {
                            getUserQuestion(stub);
                        }
                    }
                    else
                        System.out.println("Você já fez a lista de Exercicio!");
                    System.out.println(stub.getResult());
                    break;
                case "2":
                    if(stub.isQuizSet()) {
                        if (stub.isQuizOver()) {
                            for (int i = 0; i <= 5; i++) {
                                if (stub.getNumber_question() == 5) {
                                    break;
                                }
                                getUserQuestion(stub);
                            }
                        }
                        else
                            System.out.println("Você já fez a lista de Exercicio!");
                        System.out.println(stub.getResult());
                    }
                    else
                        System.out.println("Você ainda não começou a sua lista de Exercicio!");
                    break;
                case "3":
                    System.out.println("Deslogando!");
                    logout = false;
                default:
                    System.out.println("Opcao invalida");
                    break;
            }
        }
    }

    private static void getUserQuestion(AppDist stub) throws RemoteException {
        Scanner teclado = new Scanner(System.in);
        System.out.println(stub.getQuestion());
        boolean respondeu = true;
        while (respondeu) {
            System.out.print("Digite o número da alternativa correta:");
            String resposta = teclado.nextLine();
            switch (resposta) {
                case "1":
                    if (stub.isQuestionCorrect(1))
                        System.out.println("Alternativa Correta");
                    else
                        System.out.println("Alternativa Errada");
                    respondeu = false;
                    break;
                case "2":
                    if (stub.isQuestionCorrect(2))
                        System.out.println("Alternativa Correta");
                    else
                        System.out.println("Alternativa Errada");
                    respondeu = false;
                    break;
                case "3":
                    if (stub.isQuestionCorrect(3))
                        System.out.println("Alternativa Correta");
                    else
                        System.out.println("Alternativa Errada");
                    respondeu = false;
                    break;
                case "4":
                    if (stub.isQuestionCorrect(4))
                        System.out.println("Alternativa Correta");
                    else
                        System.out.println("Alternativa Errada");
                    respondeu = false;
                    break;
                default:
                    System.out.println("Por favor digite um resposta valida!" + "\n");
                    break;
            }
        }
    }
}
