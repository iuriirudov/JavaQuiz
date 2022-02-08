package com.company.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Answer {
    private int id;
    private int question_id;
    private String answer;

    private static int next_id = 1;
    ArrayList<Answer> answers = new ArrayList<>();

    public Answer() {
    }

    public Answer (int question_id, String answer) {
        this.id = next_id;
        this.question_id = question_id;
        this.answer = answer;

        next_id++;
    }

    public int getId() {
        return id;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    @Override
    public String toString() {
        return "id: " + id + ", question_id: " + question_id + ", answer: " + answer + ".";
    }

    public void addAnswers (Question q) {
        Scanner in = new Scanner(System.in);

        System.out.println("Type the answer: ");
        String userAnswer = in.nextLine();
        Answer answer = new Answer(q.getId(), userAnswer);
        answers.add(answer);

        // select the correct answer for the question
        if (q.getAnswer_id() == 0) {
            System.out.println("Is this the correct answer? (y/n): ");
            String res = in.nextLine();
            if (res.equals("y")) {
                q.setAnswer_id(answer.getId());
            } else {
                addAnswers(q);
            }
        }
        System.out.println("Add another answer? (y/n): ");
        String anotherAnswer = in.nextLine();

        if (anotherAnswer.equals("y")) {
            addAnswers(q);
        } else {
            try {
                File fileAnswer = new File("./data/answers.csv");
                FileWriter fw = new FileWriter(fileAnswer);
                BufferedWriter bw = new BufferedWriter(fw);
                String separator = "::";

                for (Answer e : this.answers) {
                    bw.write(e.getId() + separator + e.getAnswer() + separator + e.getQuestion_id());
                    bw.newLine();
                    System.out.println("Added: " + e.toString());
                }
                bw.close();
                fw.close();
            } catch (IOException e) {
                System.out.println("There was a problem with writing the file questions.csv");
            }
        }
    }

    public void listOfAnswers () {
        for (Answer answer : this.answers) {
            System.out.println(answer.toString());
        }
    }
}
