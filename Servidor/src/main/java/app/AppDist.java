package app;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface que deve ser compartilhada por servidor e cliente
 */

public interface AppDist extends Remote{

    public boolean login(String matricula, String password) throws RemoteException;
    public void startQuiz() throws RemoteException;
    public void setNumber_question() throws RemoteException;
    public int getNumber_question() throws RemoteException;
    public int getRemaining_question() throws RemoteException;
    public String getQuestion() throws RemoteException;
    public boolean isQuestionCorrect(int solution_idx) throws RemoteException;
    public boolean isQuizSet() throws RemoteException;
    public String getResult() throws RemoteException;
    public boolean isQuizOver() throws RemoteException;
}
