package com.group27.model;

/**
 * Enumeration representing the roles a user can have in the system.
 */
public enum Role {
    /**
     * Role for software testers.
     */
    TESTER("Tester"),
    /**
     * Role for junior developers.
     */
    JUNIOR_DEV("Junior Developer"),
    /**
     * Role for senior developers.
     */
    SENIOR_DEV("Senior Developer"),
    /**
     * Role for managers.
     */
    MANAGER("Manager");

    private final String displayName;

    /**
     * Constructs a Role with a display name.
     *
     * @param displayName the display name of the role
     */
    Role(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Returns the display name of the role.
     *
     * @return the display name
     */
    @Override
    public String toString() {
        return displayName;
    }
}