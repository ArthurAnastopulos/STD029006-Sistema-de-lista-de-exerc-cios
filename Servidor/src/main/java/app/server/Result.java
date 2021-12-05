package app.server;

public class Result {
    private int correct = 0;
    private int incorrect = 0;
    private int passScore;

    public Result(int passScore)
    {
        this.passScore = passScore;
    }

    public int getCorrect() {
        return this.correct;
    }

    public int getIncorrect() {
        return this.incorrect;
    }

    @Override
    public String toString() {
        return "Result{" +
                "correct=" + this.correct +
                ", incorrect=" + this.incorrect +
                ", passScore=" + this.passScore +
                '}';
    }

    public int getTotal()
    {
       return this.correct + this.incorrect;
    }

    public int getPassScore() {
        return this.passScore;
    }

    public boolean hasPassed()
    {
        return this.correct >= this.passScore;
    }

    public int setCorrect()
    {
        return this.correct++;
    }

    public int setIncorrect()
    {
        return this.incorrect++;
    }

    public void clearResult()
    {
        this.correct = 0;
        this.incorrect = 0;
    }
}
