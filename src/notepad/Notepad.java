package notepad;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import action.Action;

public class Notepad {

    private int id = 1;
    private String notepad[];
    private Stack<Action> undoAction;
    private Stack<Action> redoAction;
    private Queue<String> clipBoard;
    private int capacity;

    public Notepad(int capacity) {
        notepad = new String[capacity];

        for (int index = 0; index < capacity; index++) {
            notepad[index] = "";
        }

        undoAction = new Stack<>();
        redoAction = new Stack<>();
        clipBoard = new LinkedList<>();
        this.capacity = capacity;
    }

    public void display() {
        for (int i = 0; i < notepad.length; i++) {
            System.out.println(notepad[i]);
        }
    }

    public void display(int startingLine, int endingLine) {
        if (startingLine <= 0 || startingLine > endingLine || endingLine > this.capacity) {
            System.out.println("Unable to diplay the text, please check your input.");
            return;
        }

        for (int i = startingLine - 1; i < endingLine; i++) {
            System.out.println(notepad[i]);
        }
    }

    public void insertLine(int lineNumber, String text) {
        if (lineNumber > capacity) {
            System.out.println("Notepad limit crossed");
            return;
        }

        notepad[lineNumber - 1] = text;
        undoAction.push(new Action(id++, LocalDateTime.now(), true, lineNumber, text));
    }

    public void delete(int lineNumber) {
        if (lineNumber > capacity) {
            System.out.println("No such row to delete");
            return;
        }

        if (notepad[lineNumber - 1].isEmpty()) {
            System.out.println("NOthing to delete");
            return;
        }

        redoAction.push(new Action(id++, LocalDateTime.now(), false, lineNumber, notepad[lineNumber - 1]));
        notepad[lineNumber - 1] = "";
    }

    public void delete(int startingLine, int endingLine) {
        if (startingLine > endingLine || endingLine > capacity) {
            System.out.println("Uable to delete, please the check inputs");
            return;
        }

        for (int index = startingLine; index <= endingLine; index++) {
            delete(index);
        }
    }

    public void copy(int startingLine, int endingLine) {
        if (startingLine > endingLine || endingLine > this.capacity) {
            System.out.println("Unable to copy");
            return;
        }

        String copyText = "";
        for (int index = startingLine; index <= endingLine; index++) {
            copyText += (notepad[index] + "\n");
        }

        if (!copyText.isEmpty())
            clipBoard.add(copyText);

    }

    public void paste(int lineNumber) {
        if (this.clipBoard.isEmpty()) {
            System.out.println("Nothis to paste");
            return;
        }

        String text = clipBoard.peek();
        insertLine(lineNumber, text);
    }

    public void undo() {
        if (this.undoAction.isEmpty()) {
            System.out.println("Nothing to Undo");
            return;
        }

        Action action = this.undoAction.peek();
        this.undoAction.pop();

        delete(action.getLineNumber());
    }

    public void redo() {
        if (this.redoAction.isEmpty()) {
            System.out.println("Nothing to Redo");
            return;
        }

        Action action = this.redoAction.peek();
        this.redoAction.pop();

        insertLine(action.getLineNumber(), action.getText());
    }

}
