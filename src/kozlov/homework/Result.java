package kozlov.homework;

//Problems - no type parameters
//only
public enum Result {

    OK, NONE;

    public Object value;

    Result() throws RuntimeException, ExceptionInInitializerError{

        System.out.println("\n[Result enum class constructor]: " +
                "Well... now I gonna throw a RuntimeException. Hold fast!");

        throw new RuntimeException("[Result enum class constructor]: throws RuntimeException... \n");

    }

    public Result setValue(String value) {

        this.value = value;
        return this;

    }
}
