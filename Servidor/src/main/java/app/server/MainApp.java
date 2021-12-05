package app.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Classe que implementa a interface do objeto distribuido
 */
public class MainApp {
    private final List<Aluno> alunos = this.genarateAlunoList();
    private final HashMap<String, Integer> currentQuestion = new HashMap<String, Integer>();
    private Aluno aluno;
    private Quiz quiz_atual = null;

   public String menuLogin()
   { 
       return("Menu\n" +
            "Digite 1 para Logar;\n" +
            "Digite 2 para Sair.\n");
   }

   public String menuApp()
   { 
       return("Menu\n" +
            "Digite 1 Iniciar o questionário;\n" +
            "Digite 2 Retomar o questionário;\n" +
            "Digite 3 para Deslogar.\n");
   }

    public boolean login(String matricula, String password)
    {
         try {
             for(int i = 0; i <= this.alunos.size(); i++)
             {
                 Aluno aux = this.alunos.get(i);
                 if((aux.isMatricula(matricula)) && (aux.isPassword(password)))
                 {
                    this.aluno = aux;
                    if(!this.currentQuestion.containsKey(aux.getMatricula())){
                        this.currentQuestion.put(aux.getMatricula(), 0);
                    }
                    return true;
                 }
             }
             return false;
         } catch (Exception e) {
             return false;
         }
    }

    
    public void startQuiz()
    {
        if(this.quiz_atual == null)
            this.quiz_atual = this.aluno.getQuiz();
        else{
            this.quiz_atual.clearResult();
            this.currentQuestion.put(this.aluno.getMatricula(), 0); 
        }
    }

    
    public boolean isQuizSet()
    {
        return this.quiz_atual != null;
    }

    
    public void setNumber_question() {
            this.currentQuestion.put(this.aluno.getMatricula(), this.currentQuestion.get(this.aluno.getMatricula()) + 1);

    }

    public String getQuestion(){
        return this.quiz_atual.getQuestion(this.currentQuestion.get(this.aluno.getMatricula()));
    }

    
    public boolean isQuestionCorrect(int solution_idx)
    {
        if(this.quiz_atual.checkSolution(this.currentQuestion.get(this.aluno.getMatricula()), solution_idx)) {
            this.setNumber_question();
            return true;
        }
        this.setNumber_question();
        return false;
    }

    
    public int getNumber_question() {
        return this.currentQuestion.get(this.aluno.getMatricula());
    }

    
    public String getResult()
    {
        return this.quiz_atual.getResult();
    }

    
    public boolean isQuizOver()
    {
        return this.currentQuestion.get(this.aluno.getMatricula()) < 5;
    }

    private List<Aluno> genarateAlunoList() {
        List<Aluno> alunos = new ArrayList<Aluno>();
        String currentDirectory = new File("").getAbsolutePath();
        try( BufferedReader br = new BufferedReader(new FileReader(currentDirectory + "/../../../../resources/ALUNOS.txt")))
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
