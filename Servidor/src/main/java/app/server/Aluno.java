package app.server;

public class Aluno {
    private final String name;
    private final String matricula;
    private final String password;
    private Quiz quiz = new Quiz();

    public Aluno(String name, String matricula, String password) {
        this.name = name;
        this.matricula = matricula;
        this.password = password;
    }

    public boolean isMatricula(String matricula)
    {
        return this.matricula.equals(matricula);
    }

    public boolean isPassword(String password)
    {
        return this.password.equals(password);
    }

    public boolean isQuiz(Quiz quiz)
    {
        return this.quiz.equals(quiz);
    }

    public boolean isName(String name)
    {
        return this.name.equals(name);
    }

    public String getMatricula()
    {
        return this.matricula;
    }

    public String getPassword()
    {
        return this.password;
    }

    public String getName( )
    {
        return this.name;
    }

    public Quiz getQuiz()
    {
        return this.quiz;
    }
}
