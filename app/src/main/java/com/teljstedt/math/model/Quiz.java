package com.teljstedt.math.model;

import java.util.HashMap;
import java.util.Random;

public class Quiz {
    private static Integer quizId = 0;
    private static final Integer ReUseAfter = 10;
    private Integer term1;
    private Integer term2;
    private Integer facit;
    private Operator oper;

    private Integer min = 1;
    private Integer max = 10;

    private static HashMap<Integer, Integer> historyMap1;
    private static HashMap<Integer, Integer> historyMap2;

    public Quiz(String quizType) {
        historyMap1 = new HashMap<>();
        historyMap2 = new HashMap<>();
        quizId++;
        switch (quizType) {
            case "Addition":
                oper = Operator.Addition;
                createAdditionQuiz();
                break;
            case "Subtraktion":
                oper = Operator.Subtraktion;
                createSubtractionQuiz();
                break;
            case "Multiplikation":
                oper = Operator.Multiplikation;
                createMultiQuiz();
                break;
            case "Division":
                oper = Operator.Division;
                break;
            default:
                Integer rnd = RND(1, 4);
                switch (rnd) {
                    case 1:
                        oper = Operator.Addition;
                        createAdditionQuiz();
                        break;
                    case 2:
                        oper = Operator.Subtraktion;
                        createSubtractionQuiz();
                        break;
                    case 3:
                        oper = Operator.Multiplikation;
                        createMultiQuiz();
                        break;
                    case 4: // Todo Division
                        oper = Operator.Multiplikation;
                        createMultiQuiz();
                        break;
                    default:
                        oper = Operator.Addition;
                        createAdditionQuiz();
                }
        }

    }

    //Todo Division
    // private void createDivisionQuiz(){

    private void createMultiQuiz() {
        term1 = getRandom(historyMap1, min, max);
        term2 = getRandom(historyMap2, min, max);
        facit = term1 * term2;
    }

    private void createAdditionQuiz() {
        term1 = getRandom(historyMap1, min, max);
        term2 = getRandom(historyMap2, min, max);
        facit = term1 + term2;
    }

    private void createSubtractionQuiz() {
        term1 = getRandom(historyMap1, min, max);
        do {
            term2 = getRandom(historyMap2, min, max);
        } while (term2 < term1);
        facit = term1 - term2;
    }

    private boolean inHistory(HashMap<Integer, Integer> historyMap, Integer term) {
        Integer count = historyMap.getOrDefault(term, 0);
        if (count.equals(0) && ((quizId - count) < ReUseAfter)) {
            return false;
        } else return true;
    }

    private void saveToHistory(HashMap<Integer, Integer> historyMap, Integer term) {
        historyMap.put(term, quizId);
    }

    private Integer RND(Integer min, Integer max) {
        Random r = new Random();
        return r.nextInt(max - min) + min;
    }

    private int getRandom(HashMap<Integer, Integer> historyMap, Integer min, Integer max) {
        Integer term = 0;
        do {
            term = RND(min, max);
        } while (inHistory(historyMap, term));
        saveToHistory(historyMap, term);
        return term;
    }

    public boolean answer(Integer ans) {
        return facit.equals(ans);
    }

    public static Integer getQuizId() {
        return quizId;
    }

    public Integer getTerm1() {
        return term1;
    }

    public Integer getTerm2() {
        return term2;
    }

    public Integer getFacit() {
        return facit;
    }

    public Operator getOper() {
        return oper;
    }
}
