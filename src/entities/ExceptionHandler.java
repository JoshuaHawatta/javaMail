package src.entities;

public class ExceptionHandler {
    public ExceptionHandler(String type) {
        this.throwException(type);
    }

    public void throwException(String type) {
        if (type.equals("null")) throw new NullPointerException("Valor nulo!");
        else if (type.equals("array")) throw new ArrayIndexOutOfBoundsException("Lista vazia ou item inexistente.");
    }

    public static void printExceptionData(Exception exception) {
        exception.printStackTrace();
        System.out.println("ERRO - " + exception.getMessage());
    }
}
