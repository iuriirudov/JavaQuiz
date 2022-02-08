package com.company.main;

import java.io.*;
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

    public Answer (int id, String answer, int question_id) {
        this.id = id;
        this.answer = answer;
        this.question_id = question_id;
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

    public ArrayList<Answer> getAnswers() {
        String file = "./data/answers.csv";
        String line = null;

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                String[] str = line.split("::");
                int id = Integer.parseInt(str[0]);
                String answer = str[1];
                int question_id = Integer.parseInt(str[2]);
                this.answers.add(new Answer(id, answer, question_id));
            }
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        return this.answers;
    }

    @Override
    public String toString() {
        return "id: " + id + ", question_id: " + question_id + ", answer: " + answer + ".";
    }

    public ArrayList<Answer> addAnswers (Question q) {
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
            }
        }
        System.out.println("Add another answer? (y/n): ");
        String anotherAnswer = in.nextLine();

        if (anotherAnswer.equals("y")) {
            addAnswers(q);
        }
        return this.answers;
    }

    public void listOfAnswers () {
        for (Answer answer : this.getAnswers()) {
            System.out.println(answer.toString());
        }
    }
}
