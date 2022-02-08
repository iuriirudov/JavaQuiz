package com.company.main;

import java.util.ArrayList;
import java.util.Scanner;

public class QuizRunner {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Question q = new Question();
        Answer a = new Answer();
        int score = 0;

        ArrayList<Question> questions = q.getQuestions();
        ArrayList<Answer> answers = a.getAnswers();
        int totalQuestions = questions.size();

        System.out.println("Welcome to the Quiz!");
        for (Question question : questions) {
            System.out.println("Question #" + question.getId() + ": " + question.getQuestion() + "\n");
            System.out.println("Answer options: ");
            int i = 1;
            for (Answer answer : answers) {
                if (answer.getQuestion_id() == question.getId()) {
                    System.out.println(i + ". " + answer.getAnswer());
                    i++;
                }
            }
            System.out.println();
        }

    }
}