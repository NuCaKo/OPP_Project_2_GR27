package com.group27.model;

public enum Role {
    TESTER("Tester"),
    JUNIOR_DEV("Junior Developer"),
    SENIOR_DEV("Senior Developer"),
    MANAGER("Manager");

    private final String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}