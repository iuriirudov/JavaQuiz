package com.company.main;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Question {
    private int id;
    private String question;
    private int answer_id = 0;

    static int next_id = 1;
    private ArrayList<Question> questions = new ArrayList<>();
    ArrayList<Answer> answers = new ArrayList<>();

    public Question() {
    }

    public Question (String question) {
        this.question = question;
        this.id = next_id;
        next_id++;
    }

    public Question (int id, String question, int answer_id) {
        this.id = id;
        this.question = question;
        this.answer_id = answer_id;
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
        String file = "./data/questions.csv";
        String line = null;

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                String[] str = line.split("::");
                int id = Integer.parseInt(str[0]);
                String question = str[1];
                int answer_id = Integer.parseInt(str[2]);
                this.questions.add(new Question(id, question, answer_id));
            }

        } catch (IOException e) {
            System.out.println("Error: " + e);
        }

        return this.questions;
    }

    @Override
    public String toString() {
        return "id: " + id + ", question: " + question + ", answer_id: " + answer_id + ".";
    }

    public void addQuestion () {
        Scanner in = new Scanner(System.in);
        Answer a = new Answer();

        System.out.println("Please type your question: ");
        String userQuestion = in.nextLine();
        Question question = new Question(userQuestion);
        this.questions.add(question);
        this.answers.addAll(a.addAnswers(question));

        System.out.println("Do you want to add another question for your quiz? (y/n): ");
        String response = in.nextLine();

        if (response.equals("y")) {
            addQuestion();
        } else {
            try {
                String separator = "::";
                File fileQuestion = new File("./data/questions.csv");
                FileWriter fwq = new FileWriter(fileQuestion);
                BufferedWriter bwq = new BufferedWriter(fwq);

                for (Question e : this.questions) {
                    bwq.write(e.getId() + separator + e.getQuestion() + separator + e.getAnswer_id());
                    bwq.newLine();
                }
                bwq.close();
                fwq.close();

                File fileAnswer = new File("./data/answers.csv");
                FileWriter fwa = new FileWriter(fileAnswer);
                BufferedWriter bwa = new BufferedWriter(fwa);
                for (Answer e : this.answers) {
                    bwa.write(e.getId() + separator + e.getAnswer() + separator + e.getQuestion_id());
                    bwa.newLine();
                }

                bwa.close();
                fwa.close();
            } catch (IOException e) {
                System.out.println("Error: " + e);
            }
        }
        in.close();

    }
    public void listOfQuestions () {
        for (Question question : this.getQuestions()) {
            System.out.println(question.toString());
        }
    }
}