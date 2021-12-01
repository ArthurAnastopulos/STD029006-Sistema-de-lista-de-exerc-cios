package app.server;
import app.AppDist;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que implementa a interface do objeto distribuido
 */
public class MainApp implements AppDist {
    private final List<Aluno> alunos = this.genarateAlunoList();
    private Aluno aluno;
    private Quiz quiz_atual = null;
    private int current_question = 0;
    private int remaining_question = 5;

    @Override
    public boolean login(String matricula, String password)
    {
         try {
             for(int i = 0; i <= this.alunos.size(); i++)
             {
                 Aluno aux = this.alunos.get(i);
                 if((aux.isMatricula(matricula)) && (aux.isPassword(password)))
                 {
                     this.aluno = aux;
                     return true;
                 }
             }
             return false;
         } catch (Exception e) {
             return false;
         }
    }

    @Override
    public void startQuiz()
    {
        this.quiz_atual = this.aluno.getQuiz();
    }

    @Override
    public boolean isQuizSet()
    {
        return this.quiz_atual != null;
    }

    @Override
    public void setNumber_question() {
            this.current_question++;
            this.remaining_question--;
    }

    @Override
    public int getRemaining_question() {
        return remaining_question;
    }

    @Override
    public String getQuestion(){
        return this.quiz_atual.getQuestion(this.current_question);
    }

    @Override
    public boolean isQuestionCorrect(int solution_idx)
    {
        if(this.quiz_atual.checkSolution(this.current_question, solution_idx)) {
            this.setNumber_question();
            return true;
        }
        this.setNumber_question();
        return false;
    }

    @Override
    public int getNumber_question() {
        return current_question;
    }

    @Override
    public String getResult()
    {
        return this.quiz_atual.getResult();
    }

    @Override
    public boolean isQuizOver()
    {
        System.out.println("A" + String.valueOf(this.current_question));
        return current_question < 5;
    }

    private List<Aluno> genarateAlunoList() {
        List<Aluno> alunos = new ArrayList<Aluno>();
        String currentDirectory = new File("").getAbsolutePath();
        try( BufferedReader br = new BufferedReader(new FileReader(currentDirectory + "/src/main/resources/ALUNOS.txt")))
        {
            String line;
            List<String> matricula = new ArrayList<String>();
            List<String> name = new ArrayList<String>();
            List<String> password = new ArrayList<String>();
            while ((line = br.readLine()) != null)
            {
                if(line.length() > 0)
                {
                    String[] segments = line.split("-");
                    if (!segments[0].equals("Password")) {
                        matricula.add(segments[0]);
                        name.add(segments[1]);
                    }
                    if (segments[0].equals("Password")) {
                        password.add(segments[1]);
                    }
                }
            }
            if((matricula.size() == name.size() && name.size() == password.size()) && name.size() != 0)
            {
                for (int i = 0; i < matricula.size(); i++) {
                    Aluno aluno = new Aluno(name.get(i), matricula.get(i), password.get(i));
                    alunos.add(aluno);
                }
                return alunos;
            }
        }
        catch (IOException e)
        {
            System.out.println (e.toString());
            System.out.println("Could not find file ALUNOS.txt");
        }
        return null;
    }
}
