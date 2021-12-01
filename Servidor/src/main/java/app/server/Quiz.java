package app.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Quiz
{
    private List<Question> questions = new ArrayList<Question>();
    private Result result = new Result(3);

    public Quiz()
    {
        String currentDirectory = new File("").getAbsolutePath();
        try( BufferedReader br = new BufferedReader(new FileReader(currentDirectory + "/src/main/resources/listaSTD.txt")))
        {
            List<Question> questions = new ArrayList<Question>();
            String line;
            while ((line = br.readLine()) != null)
            {
                if(line.length() > 0)
                {
                   String[] segments = line.split("-");
                   List<String> solutions = new ArrayList<>();
                   int correctIdx = 0;
                    for (String segment : segments) {
                        if (segment.contains("&")) { // errada
                            String[] aux = segment.split("#");
                            solutions.add(aux[1]);
                        }
                        if (segment.contains("$")) { //correta
                            String[] aux = segment.split("#");
                            solutions.add(aux[1]);
                            correctIdx = Integer.parseInt(Character.toString(aux[1].charAt(0)));
                        }
                    }

                   Question question = new Question(Integer.parseInt(segments[0]), segments[1], solutions, correctIdx);
                   questions.add(question);
                }
            };
            this.questions = questions;
        }
        catch (IOException e)
        {
            System.out.println (e.toString());
            System.out.println("Could not find file ALUNOS.txt");
        }
    }

    public String getQuestion(int currentQuestion)
    {
        return this.questions.get(currentQuestion).toString();
    }

    public boolean checkSolution(int question_idx, int solution_idx)
    {
        if(this.questions.get(question_idx).isCorrect(solution_idx))
        {
            this.result.setCorrect();
            return true;
        }
        else
        {
            this.result.setIncorrect();
            return false;
        }
    }

    public String getResult()
    {
        return result.toString();
    }
}
