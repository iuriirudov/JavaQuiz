package com.company.main;

public class Quiz {

    public static void main(String[] args) {

        Question q = new Question();
        Answer a = new Answer();
        q.addQuestion();

        q.listOfQuestions();
        a.listOfAnswers();
    }
}