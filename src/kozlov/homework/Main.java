package kozlov.homework;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import java.io.*;
import java.nio.file.AccessDeniedException;

public class Main {

    public static void main(String[] args){

        { /*** CUSTOM IMPLEMENTATION OF AUTOCLOSEABLE INTERFACE ***/

            System.out.println("\n*** CUSTOM IMPLEMENTATION OF AUTOCLOSEABLE INTERFACE ***");


            System.out.println("\nRunning JUnit test with custom autocloseable class instance to be checked...");

            JUnitCore junit = new JUnitCore();

            junit.addListener(new TextListener(System.out));

            junit.run(MyAutoCloseableClassTest.class);


            System.out.println("*** END OF CUSTOM IMPLEMENTATION OF AUTOCLOSEABLE INTERFACE ***\n");

        } /*** END OF CUSTOM IMPLEMENTATION OF AUTOCLOSEABLE INTERFACE ***/


        { /*** USING CONSTRUCTOR THROWING AN EXCEPTION ***/

            System.out.println("\n*** USING CONSTRUCTOR THROWING AN EXCEPTION ***");


            exceptionVsResult();

            System.out.println("RESUME: Before assigning the constant from Enum class to a variable, JVM encounters" +
                    "\nthe RuntimeException thrown from inside the enum object constructor and throws as a result" +
                    "\nExceptionInInitializerError exception. So JVM cannot finish its task of creating instance " +
                    "\nof the enum class and the variable still keeps its initial value.\n");


            System.out.println("*** END OF USING CONSTRUCTOR THROWING AN EXCEPTION ***\n");

        } /*** END OF USING CONSTRUCTOR THROWING AN EXCEPTION ***/


        { /*** READING CHARACTERS FROM TEXT FILE USING ARGS[] ARRAY ***/

            System.out.println("\n*** READING CHARACTERS FROM TEXT FILE USING ARGS[] ARRAY ***");


            String rootPath = System.getProperty("user.dir");

            String fileName = rootPath + "\\src\\kozlov\\homework\\justAFileWithALine.txt";

            String[] myArgs = new String[1];

            args = myArgs;

            // Getting the file name as if it was input as an argument from command line: java Main ...\justAFileWithALine.txt
            args[0] = fileName;

            try (FileInputStream fileInputStream = new FileInputStream(args[0])) {

                char character = (char)fileInputStream.read();

                System.out.print("\n[justAFileWithALine.txt]: ");

                while(character != (char)-1) {

                    System.out.print(character);

                    character = (char)fileInputStream.read();

                }

                System.out.println();

            } catch (FileNotFoundException e) {

                e.printStackTrace();

            } catch (IOException e) {

                e.printStackTrace();
            }


            System.out.println("\n*** END OF READING CHARACTERS FROM TEXT FILE ***\n");

        } /*** END OF READING CHARACTERS FROM TEXT FILE ***/


        { /*** THROWING EXCEPTION FROM LifeCycleAction.execute() METHOD  ***/

            System.out.println("\n*** THROWING EXCEPTION FROM LifeCycleAction.execute() METHOD  ***");


            try {

            new LifeCycleAction().execute();

            } catch (LifeCycleActionExecutionException | AccessDeniedException e) {

                System.err.println(e.getLocalizedMessage());

            } catch (Exception e) {

                throw new RuntimeException(e);
            }


            System.out.println("\n*** END OF THROWING EXCEPTION FROM LifeCycleAction.execute() METHOD  ***\n");

        } /*** END OF THROWING EXCEPTION FROM LifeCycleAction.execute() METHOD  ***/


    }


    public static class LifeCycleAction {

        public void execute() throws LifeCycleActionExecutionException, AccessDeniedException, CustomCheckedException {

            System.out.println("\n[Main.LifeCycleAction]: inside execute() method...");

            System.out.println("[Main.LifeCycleAction]: now LifeCycleActionExecutionException will be thrown...");

            throw new LifeCycleActionExecutionException("[Main.LifeCycleAction] -> LifeCycleActionExecutionException is now working!\n");
        }
    }


    public static class LifeCycleActionExecutionException extends Exception {

        /**
         * Constructs a new exception with the specified detail message.  The
         * cause is not initialized, and may subsequently be initialized by
         * a call to {@link #initCause}.
         *
         * @param message the detail message. The detail message is saved for
         *                later retrieval by the {@link #getMessage()} method.
         */
        public LifeCycleActionExecutionException(String message) {
            super(message);
        }
    }


    public static void exceptionVsResult() {

        String result = "The very first value - before trying to change it with the help of instantiating Result Enum class.";

        try {

            result = returnValueOrThrowException();

        } catch (Exception e) {

            System.out.println();

            System.out.println("[Main.exceptionVsResult()]:");
            System.out.println("- Exception is caught: [" +
                    e.getClass().getSimpleName() + "] extends --> [" +
                    e.getClass().getSuperclass().getSimpleName() + "]");

            System.out.println("- The cause of the above exception is: [" +
                    e.getCause().getClass().getSimpleName() + "] extends --> [" +
                    e.getCause().getClass().getSuperclass().getSimpleName() + "] extends --> [" +
                    e.getCause().getClass().getSuperclass().getSuperclass().getSimpleName() + "]");

            System.out.println("- The cause of the above exception is: [" +
                    e.getCause().getCause().getClass().getSimpleName() + "] extends --> [" +
                    e.getCause().getCause().getClass().getSuperclass().getSimpleName() + "]");

            System.out.println();

            e.printStackTrace();

        }

        System.out.println("--> result = '" + result + "'\n");
    }


    private static String returnValueOrThrowException() throws CustomCheckedException {

        try{

            return Result.OK.name();

        }catch (Exception | Error e){

            throw new CustomCheckedException("Custom Exception is here!", e);

        }

    }
}
