package app.client;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


public class Cliente {
    private static String serverName = "127.0.0.1";
    private static int port = 12345;

    public static void main(String[] args) {

        try {

            Scanner ler = new Scanner(System.in);
            
            /* Estabelece conexao com o servidor */
            Socket conexao = new Socket(serverName,port);
            System.out.println("Conectado! " + conexao);
            DataOutputStream fluxoSaida = new DataOutputStream(conexao.getOutputStream());
            DataInputStream fluxoEntrada = new DataInputStream( new BufferedInputStream(conexao.getInputStream()));

            //
            String op;
            String saida = "sair";
            String resposta = "";
            boolean bool_menu = true;
            while(bool_menu){
                resposta = fluxoEntrada.readUTF();
                if(resposta.equals(saida.toUpperCase()))break;
                else {
                    System.out.println(resposta);
                    op = ler.nextLine();
                    fluxoSaida.writeUTF(op);
                    fluxoSaida.flush();
                }
            }

            fluxoSaida.close();
            fluxoEntrada.close();
            conexao.close();
           
        } catch (Exception ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
