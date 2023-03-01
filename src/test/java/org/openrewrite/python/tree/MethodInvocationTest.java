/*
 * Copyright 2021 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openrewrite.python.tree;

import org.intellij.lang.annotations.Language;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openrewrite.test.RewriteTest;

import static org.openrewrite.python.Assertions.python;

public class MethodInvocationTest implements RewriteTest {

    @ParameterizedTest
    //language=py
    @ValueSource(strings = {
      "print()", "print( )",
      "print(42)", "print( 42 )", "print(1, 2, 3, 4)",
      "print( 1, 2, 3, 4 )", "print(1 , 2 , 3 , 4)",
      "print(1, 2, 3, 4, sep='+')",
      "print(1, 2, a=1, b=2)", "print(1, 2, a =1, b =2)", "print(1, 2, a= 1, b= 2)",
    })
    void print(@Language("py") String print) {
        rewriteRun(python(print));
    }

    @Test
    void qualifiedTarget() {
        /*
            This appears as a `qualifier` on `PyReferenceExpression`.
            This should be straightforward to implement but isn't done.
         */
        rewriteRun(python("int.bit_length(42)"));
    }

    @Test
    void methodInvocationOnExpressionTarget() {
        rewriteRun(python("list().copy()"));
    }
}
