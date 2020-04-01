package kozlov.homework;

import java.nio.file.AccessDeniedException;

public class Child extends Parent {

    public Child() {}

    // AccessDeniedException is not allowed to be thrown here because it is checked by compiler
    // and the child should not be "more dangerous" than its parent regarding possibility of checked exceptions
    // in overridden methods.
    @Override
    public void throwOnlyOneException() throws Main.LifeCycleActionExecutionException/*, AccessDeniedException*/ {

        super.throwOnlyOneException();

    }

}
