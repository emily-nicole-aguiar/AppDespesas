package com.jorzelio.despesa.model;

public class ExpenseModel {

    private String expenseId;
    private String expenseName;

    public ExpenseModel() {
        // Construtor vazio necessário para o Firebase
    }

    public ExpenseModel(String expenseId, String expenseName) {
        this.expenseId = expenseId;
        this.expenseName = expenseName;
    }

    public String getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(String expenseId) {
        this.expenseId = expenseId;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public String getExpenseName() {
        return expenseName;
    }
}
