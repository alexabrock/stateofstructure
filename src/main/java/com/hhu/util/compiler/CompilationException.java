package com.hhu.util.compiler;

public class CompilationException extends RuntimeException {
    CompilationException(String msg , Exception e) {
        super(msg, e);
        e.printStackTrace();
    }
}
