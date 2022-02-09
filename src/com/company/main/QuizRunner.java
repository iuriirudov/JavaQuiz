package com.company.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class QuizRunner {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Question q = new Question();
        Answer a = new Answer();
        double score = 0;
        int response = 0;

        ArrayList<Question> questions = q.getQuestions();
        ArrayList<Answer> answers = a.getAnswers();
        Collections.shuffle(answers);
        double totalQuestions = questions.size();

        System.out.println("Welcome to the Quiz!");
        for (Question question : questions) {
            System.out.println("Question #" + question.getId() + ": " + question.getQuestion() + "\n");
            System.out.println("Answer options: ");
            int i = 1;
            ArrayList<Answer> specificAnswers = new ArrayList<>();
            for (Answer answer : answers) {
                if (answer.getQuestion_id() == question.getId()) {
                    specificAnswers.add(answer);
                }
            }

            for (Answer e : specificAnswers) {
                System.out.println(i + ". " + e.getAnswer());
                i++;
            }
            System.out.println("Please type the number of the correct answer from the list above: ");
            response = in.nextInt();
            if (question.getAnswer_id() == specificAnswers.get(response - 1).getId()) {
                score += 1;
                System.out.println("You answered correct!");
            }
        }
        double percent = score / totalQuestions * 100.0;
        String result = null;
        if (percent > 60) {
            result = "passed";
        } else {
            result = "failed";
        }
        System.out.println("Your total score is " + percent + "%. You " + result + " this test!");
    }
}