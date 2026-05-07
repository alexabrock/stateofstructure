package com.hhu.util.compiler;

import org.junit.Test;

public class CompilerTest {
    @Test
    public void testIfMethodReturnsRightValue() {
        String code = """
                public class Foo {
                    public String bar() { return "baz"; }
                }
                """;

        Compiler.compile(code);
    
    }
}
