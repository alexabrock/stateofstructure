package com.hhu.util;
//Doesnt do much right now, will be a starting point for when I have 

//added draw() calls from methods.

public class StackReader {
    private final String[] stackLines;
    private int currentIndex;
    private final StackWalker walker;

    public StackReader(String stackTrace) {
        this.stackLines = stackTrace.split("\\r?\\n");
        this.currentIndex = 0;
        this.walker = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);
    }

    public String getCallStack() {
        if (currentIndex >= stackLines.length) {
            return "End of stack trace reached.";
        }
        return String.join("\n", stackLines);
    }




}
