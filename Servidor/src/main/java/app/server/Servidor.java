package app.server;
import app.AppDist;

import java.io.File;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor
{
    private static String serverName = "127.0.0.1";
    private static int port = 12345;
    private static final String OBJDISTNAME = "MyApp";

    public static void main(String[] args) {
        try
        {
            // recebendo nome por argumento de linha de comando
            if (args.length > 0)
            {
                if (args[0] != null)
                {
                    serverName = args[0];
                }
                if (args[1] != null)
                {
                    port = Integer.parseInt(args[1]);
                }
            }

            MainApp app = new MainApp();

            System.setProperty("java.rmi.app.server.hostname", serverName);
            AppDist std = (AppDist)
                    UnicastRemoteObject.exportObject( app, 0);

            Registry registry = LocateRegistry.createRegistry(port);

            registry.bind(OBJDISTNAME, std);
            System.out.println("Servidor pronto!\n");
            System.out.println("Pressione CTRL + C para encerrar...");
        }
        catch (RemoteException | AlreadyBoundException ex)
        {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}