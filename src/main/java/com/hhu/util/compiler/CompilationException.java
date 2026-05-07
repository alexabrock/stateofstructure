package com.hhu.util.compiler;

class CompilationException extends RuntimeException {
    CompilationException(String msg , Exception e) {
        super(msg, e);
    }
}
