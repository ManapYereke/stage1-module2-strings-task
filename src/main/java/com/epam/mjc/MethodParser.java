package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
    String[] parts = signatureString.split("\\s+"); // Split the signature string by whitespace

    int startIndex = 0;
    String accessModifier = parts[0]; // First part is the access modifier
    if (isAccessModifier(accessModifier)) {
        startIndex = 1;
    } else {
        accessModifier = null;
    }

    String returnType = parts[startIndex]; // Next part is the return type

    int endIndex = signatureString.indexOf('(');
    String methodName = parts[startIndex + 1];
    int index = methodName.indexOf("(");
    methodName = methodName.substring(0, index);
    String argumentsString = signatureString.substring(endIndex + 1, signatureString.indexOf(')')); // Extract the argument string
    List<MethodSignature.Argument> arguments = parseArguments(argumentsString); // Parse the arguments

    MethodSignature methodSignature = new MethodSignature(methodName, arguments);
    methodSignature.setAccessModifier(accessModifier);
    methodSignature.setReturnType(returnType);
    return methodSignature;
}

private List<MethodSignature.Argument> parseArguments(String argumentsString) {
    List<MethodSignature.Argument> arguments = new ArrayList<>();
    if (!argumentsString.isEmpty()) {
        String[] argumentParts = argumentsString.split(",");
        for (String argumentPart : argumentParts) {
            String[] argumentComponents = argumentPart.trim().split("\\s+");
            String argumentType = argumentComponents[0];
            String argumentName = argumentComponents[1];
            MethodSignature.Argument argument = new MethodSignature.Argument(argumentType, argumentName);
            arguments.add(argument);
        }
    }
    return arguments;
}

private boolean isAccessModifier(String accessModifier) {
    return accessModifier.equals("public")
            || accessModifier.equals("protected")
            || accessModifier.equals("private")
            || accessModifier.equals("default");
}

}
