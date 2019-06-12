package kurlyk.common.algorithm.formulaTester;

public class FormulaUtils {
    public static String latexFormulaToAskiiMathFormula(String latexFormula){
        String askiiMathFormula = latexFormula;
        askiiMathFormula = askiiMathFormula.replaceAll("\\\\cdot", "*");
        askiiMathFormula = askiiMathFormula.replaceAll("\\\\left\\(", "(");
        askiiMathFormula = askiiMathFormula.replaceAll("\\\\right\\)", ")");
        askiiMathFormula = askiiMathFormula.replaceAll("\\{", "(");
        askiiMathFormula = askiiMathFormula.replaceAll("}", ")");
        askiiMathFormula = askiiMathFormula.replaceAll("\\\\sum", "sum");

//        System.out.println("Было: " + latexFormula);
//        System.out.println("Стало: " + askiiMathFormula);
        return askiiMathFormula;
    }
}
