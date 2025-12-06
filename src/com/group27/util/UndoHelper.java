package com.group27.util;

import java.util.Stack;

public class UndoHelper<T> {
    private Stack<T> undoStack = new Stack<>();

    public void saveState(T state) {
        undoStack.push(state);
    }

    public T undo() {
        if (!undoStack.isEmpty()) {
            return undoStack.pop();
        }
        return null;
    }

    public boolean hasUndo() {
        return !undoStack.isEmpty();
    }
}