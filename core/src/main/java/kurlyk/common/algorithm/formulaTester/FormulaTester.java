package kurlyk.common.algorithm.formulaTester;

public class FormulaTester {
    public static boolean test(String formula1, String formula2) {
        return test(formula1, formula2, true);
    }

    public static boolean test(String formula1, String formula2, boolean formulaIsLatex) {
        if(formulaIsLatex){
            formula1 = FormulaUtils.latexFormulaToAskiiMathFormula(formula1);
            formula2 = FormulaUtils.latexFormulaToAskiiMathFormula(formula2);
        }
        CorrectSyntaxTree syntaxTree1 = new CorrectSyntaxTree(formula1);
        CorrectSyntaxTree syntaxTree2 = new CorrectSyntaxTree(formula2);
        return syntaxTree1.equals(syntaxTree2);
    }
}
