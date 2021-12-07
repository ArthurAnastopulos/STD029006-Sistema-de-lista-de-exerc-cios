package app.server;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor
{
    private static int port = 12345;

    public static void main(String[] args) {
        try
        {
            MainApp app = new MainApp();

            /* Registra servico na porta 1234 e aguarda por conexoes */
            ServerSocket servidor = new ServerSocket(port);

            System.out.println("Aguardando por conexoes");
            Socket conexao = servidor.accept();
            System.out.println("Cliente conectou " + conexao);

            DataInputStream fluxoEntrada = new DataInputStream( new BufferedInputStream(conexao.getInputStream()));
            DataOutputStream fluxoSaida = new DataOutputStream(conexao.getOutputStream());

            fluxoSaida.writeUTF(app.menuLogin());
            boolean response = true;
            String saida = "sair";
            int op = 0;
            while(response)
            {
                op = Integer.parseInt(fluxoEntrada.readUTF());
                if(op == 1) {
                    fluxoSaida.writeUTF("Digite Matricula:");
                    String matricula = fluxoEntrada.readUTF();
                    fluxoSaida.writeUTF("Digite Senha:");
                    String senha = fluxoEntrada.readUTF();
                    if(app.login(matricula, senha)){
                        fluxoSaida.writeUTF("Bem Vindo! \n" + app.menuApp());
                        app(app, conexao);
                    }
                    else{
                        fluxoSaida.writeUTF("Usuario Invalido. \n" + app.menuLogin());
                    }
                }
                else if(op == 2){
                    fluxoSaida.writeUTF(saida.toUpperCase());
                    response  = false;
                }
                else{
                    fluxoSaida.writeUTF("Opção Invalida \n" + app.menuLogin()); 
                }
            }
            

            fluxoSaida.close();
            fluxoEntrada.close();
            conexao.close();
            servidor.close();
        }
        catch (Exception ex)
        {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void app(MainApp app, Socket conexao) throws IOException{
        DataInputStream fluxoEntrada = new DataInputStream( new BufferedInputStream(conexao.getInputStream()));
        DataOutputStream fluxoSaida = new DataOutputStream(conexao.getOutputStream());
        boolean response = true;
        int op = 0;
        String avc = null;
        while(response) {
            op = Integer.parseInt(fluxoEntrada.readUTF());
            if(op == 1){
                app.startQuiz();
                if(app.isQuizOver())
                {
                    boolean start = true;
                    for(int i = 1; i <= 5; i++)
                    {
                        if((i == 1) || (avc.equals("a"))){
                            getUserQuestion(app, conexao, false, start);
                            avc = fluxoEntrada.readUTF().toLowerCase();
                        }
                        else{
                            fluxoSaida.writeUTF("A lista foi parada por entrada invalida. \n" + "Lista de Exercicio Encerrada \n" 
                                + app.getResult() + "\n" + app.menuApp());
                            break;
                        }
                        start = false;
                    }
                    if(!app.isQuizOver()){
                        fluxoSaida.writeUTF("Lista Encerrada! \n" + app.getResult() + "\n" + app.menuApp());
                    }
                }
            } else if(op == 2){
                if(app.isQuizSet()){
                    if(app.isQuizOver()){
                        boolean start = true;
                        for(int i = 1; i <= 5; i++)
                        {
                            if(app.getNumber_question() == 5){
                                fluxoSaida.writeUTF("Lista Encerrada! \n" + app.getResult() + "\n" + app.menuApp());
                                break;
                            }
                            if((i == 1) || (avc.equals("a"))){
                                getUserQuestion(app, conexao, true, start);
                                avc = fluxoEntrada.readUTF().toLowerCase();
                            }
                            else{
                                fluxoSaida.writeUTF("A lista foi parada por entrada invalida. \n" + "Lista de Exercicio Encerrada \n" 
                                    + app.getResult() + "\n" + app.menuApp());
                                break;
                            }
                            start = false;
                        }
                        if(!app.isQuizOver()){
                            fluxoSaida.writeUTF("Lista Encerrada! \n" + app.getResult() + "\n" + app.menuApp());
                        }
                    }
                    else{
                        fluxoSaida.writeUTF("Você já fez a lista de Exercicio! \n" + app.getResult() + "\n" + app.menuApp());
                    }
                }
                else{
                    fluxoSaida.writeUTF("A lista ainda não foi iniciada. \n" + app.menuApp());
                }
                
            } else if (op == 3){
                fluxoSaida.writeUTF("Deslogando do Sistema. \n" + app.menuLogin());
                response = false;
            } else {
                fluxoSaida.writeUTF("Opção Invalida \n");
            }
        }
    }

    public static void getUserQuestion(MainApp app, Socket conexao, boolean retomando,boolean start) throws IOException {
        DataInputStream fluxoEntrada = new DataInputStream( new BufferedInputStream(conexao.getInputStream()));
        DataOutputStream fluxoSaida = new DataOutputStream(conexao.getOutputStream());
        if(!retomando && start){
            fluxoSaida.writeUTF("Lista de Exercicio Iniciada! \n" + app.getQuestion());
        }
        else if(retomando && start){
            fluxoSaida.writeUTF("Lista de Exercicio Retomada! \n" + app.getQuestion());
        }
        else{
            fluxoSaida.writeUTF("\n" + app.getQuestion());
        }
        boolean respondeu = true;
        int op = 0;
        while (respondeu) {   
            op = Integer.parseInt(fluxoEntrada.readUTF());
            if(op == 1){
                if (app.isQuestionCorrect(1))
                    fluxoSaida.writeUTF("Alternativa Respondida! Digite: a , para continuar \n");
                else
                    fluxoSaida.writeUTF("Alternativa Respondida! Digite: a , para continuar \n");
                respondeu = false; 
            }
            else if(op == 2){
                if (app.isQuestionCorrect(2))
                    fluxoSaida.writeUTF("Alternativa Respondida! Digite: a , para continuar \n");
                else
                    fluxoSaida.writeUTF("Alternativa Respondida! Digite: a , para continuar \n");
                respondeu = false; 
            }
            else if(op == 3){
                if (app.isQuestionCorrect(3))
                    fluxoSaida.writeUTF("Alternativa Respondida! Digite: a , para continuar \n");
                else
                    fluxoSaida.writeUTF("Alternativa Respondida! Digite: a , para continuar \n");
                respondeu = false; 
            }
            else if(op == 4){
                if (app.isQuestionCorrect(4))
                    fluxoSaida.writeUTF("Alternativa Respondida! Digite: a , para continuar \n");
                else
                    fluxoSaida.writeUTF("Alternativa Respondida! Digite: a , para continuar \n");
                respondeu = false; 
            } 
            else
            {
                fluxoSaida.writeUTF("Valor Invalido \n" + app.getQuestion());
            }
        }
    }
}