package com.company.main;

public class QuizRunner {
    public static void main(String[] args) {
        Question q = new Question();
        Answer a = new Answer();

        q.listOfQuestions();
        a.listOfAnswers();
    }
}