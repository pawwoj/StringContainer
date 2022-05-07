import exceptions.DuplicatedElementOnListException;
import exceptions.InvalidStringContainerPatternException;
import exceptions.InvalidStringContainerValueException;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class StringContainer {
    private final Pattern pattern;
    private int size = 0;
    private Node head = null;
    private boolean duplicatedNotAllowed = false;

    public StringContainer(String pattern) {
        this.pattern = stringToPattern(pattern);
    }

    public StringContainer(String pattern, boolean duplicatedNotAllowed) {
        this.pattern = stringToPattern(pattern);
        this.duplicatedNotAllowed = duplicatedNotAllowed;
    }

    public Pattern stringToPattern(String s) {
        Pattern pattern;
        try {
            pattern = Pattern.compile(s);
        } catch (PatternSyntaxException exception) {
            throw new InvalidStringContainerPatternException(s);
        }
        return pattern;
    }

    public boolean isCorrectValueForPattern(String s) {
        if (!pattern.matcher(s).matches()) {
            throw new InvalidStringContainerValueException(s);
        }
        return true;
    }

    public boolean containString(String o) {
        Node searched = head;
        while (searched != null) {
            if (searched.getString().equals(o)) {
                return true;
            }
            searched = searched.getNext();
        }
        return false;
    }

    public void initializeHead(String s) {
        head = new Node(s);
        size++;
    }

    public boolean add(String s) {
        isCorrectValueForPattern(s);

        if (head == null) {
            initializeHead(s);
            return true;
        }

        if (duplicatedNotAllowed && containString(s)) {
            throw new DuplicatedElementOnListException();
        }

        Node current = head;
        while (current.getNext() != null) {
            current = current.getNext();
        }

        current.setNext(new Node(s));
        size++;
        return true;
    }

    public String get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            if (current.getNext() != null)
                current = current.getNext();
        }
        return current.getString();
    }

    public void removeHead() {
        head = head.getNext();
        size--;
    }

    public boolean remove(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            removeHead();
            return true;
        }

        Node current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.getNext();
        }
        current.setNext(current.getNext().getNext());
        size--;
        return true;
    }

    public boolean remove(String s) {
        if (s == null) {
            throw new NullPointerException();
        }

        if (head.getString().equals(s)) {
            removeHead();
            return true;
        }

        Node current = head;
        while (current.getNext() != null) {
            if (current.getNext().getString().equals(s)) {
                current.setNext(current.getNext().getNext());
                size--;
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public int size() {
        return size;
    }

    private static class Node {
        private final String s;
        private Node next;

        private Node(String s) {
            next = null;
            this.s = s;
        }

        public String getString() {
            return s;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
