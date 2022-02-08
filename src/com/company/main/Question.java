package com.company.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Question {
    private int id;
    private String question;
    private int answer_id = 0;

    static int next_id = 1;
    private ArrayList<Question> questions = new ArrayList<>();

    public Question() {
    }

    public Question (String question) {
        this.question = question;
        this.id = next_id;
        next_id++;
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public int getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(int answer_id) {
        this.answer_id = answer_id;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    @Override
    public String toString() {
        return "id: " + id + ", question: " + question + ", answer_id: " + answer_id + ".\n";
    }

    public void addQuestion () {
        Scanner in = new Scanner(System.in);
        Answer a = new Answer();

        System.out.println("Please type your question: ");
        String userQuestion = in.nextLine();
        Question question = new Question(userQuestion);
        this.questions.add(question);
        a.addAnswers(question);

        System.out.println("Do you want to add another question for your quiz? (y/n): ");
        String response = in.nextLine();

        if (response.equals("y")) {
            addQuestion();
        } else {
            try {
                File fileQuestion = new File("./data/questions.csv");
                FileWriter fw = new FileWriter(fileQuestion);
                BufferedWriter bw = new BufferedWriter(fw);
                String separator = "::";

                for (Question e : this.questions) {
                    bw.write(e.getId() + separator + e.getQuestion() + separator + e.getAnswer_id());
                    bw.newLine();
                }
                bw.close();
                fw.close();
            } catch (IOException e) {
                System.out.println("There was a problem with writing the file questions.csv");
            }
        }
        in.close();

    }
    public void listOfQuestions () {
        for (Question question : this.questions) {
            System.out.println(question.toString());
        }
    }
}