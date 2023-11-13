package ru.vodolazhsky.otus.model;

import java.util.List;

public record Question(String question, List<String> correctAnswers) {
}
