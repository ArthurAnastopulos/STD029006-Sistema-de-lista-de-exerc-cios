package app.server;

import java.util.List;

public class Question {
    private final int id;
    private final String question;
    private final List<String> solutions;
    private final int correctIndex;

    public Question(int id, String question, List<String> solutions, int correctIndex)
    {
        this.id = id;
        this.question = question;
        this.solutions = solutions;
        this.correctIndex = correctIndex;
    }

    @Override
    public String toString() {
        StringBuilder aux = new StringBuilder();
        aux.append(this.id + "-" +this.question).append("\n").append("correta" + this.correctIndex + "\n");
        for (int i = 0; i < this.solutions.size(); i++){
            aux.append(this.solutions.get(i)).append("\n");
        }
        return aux.toString();
    }

    public int getId()
    {
        return this.id;
    }

    public String getQuestion()
    {
        return this.question;
    }

    public List<String> getSolutions()
    {
        return this.solutions;
    }

    public int getCorrectSolution()
    {
        return this.correctIndex;
    }

    public boolean isCorrect(int alternativeIndex)
    {
        return this.correctIndex == alternativeIndex;
    }
}
