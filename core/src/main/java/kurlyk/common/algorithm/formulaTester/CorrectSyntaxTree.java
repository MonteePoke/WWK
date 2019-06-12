package kurlyk.common.algorithm.formulaTester;

import org.scijava.parse.ExpressionParser;
import org.scijava.parse.Operator;
import org.scijava.parse.Tokens;

import java.util.*;
import java.util.stream.Collectors;


public class CorrectSyntaxTree implements Iterable<CorrectSyntaxTree> {

    private final Object token;
    private List<CorrectSyntaxTree> children;

    public CorrectSyntaxTree(String formula) {
        this(new ExpressionParser().parsePostfix(formula));
    }

    public CorrectSyntaxTree(final LinkedList<Object> tokens) {
        token = tokens.removeLast();
        if (Tokens.isOperator(token)) {
            final Operator op = (Operator) token;
            final int arity = op.getArity();
            if (arity > 0) children = new ArrayList<>(arity);
            for (int i = arity - 1; i >= 0; i--) {
                children.add(new CorrectSyntaxTree(tokens));
            }
        }
    }

    public Object token() {
        return token;
    }

    public CorrectSyntaxTree child(final int index) {
        return children.get(index);
    }

    public int count() {
        return children == null ? 0 : children.size();
    }

    /** Converts the syntax tree into a token queue in postfix order. */
    public LinkedList<Object> postfix() {
        final LinkedList<Object> queue = new LinkedList<>();
        postfix(queue);
        return queue;
    }

    // -- Object methods --

    @Override
    public String toString() {
        return toString("");
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof CorrectSyntaxTree)) return false;
        final CorrectSyntaxTree tree = (CorrectSyntaxTree) o;
        CorrectSyntaxTree treeSimplifiedThis = simplifyTree(this);
        CorrectSyntaxTree treeSimplifiedThat = simplifyTree(tree);
        HashSet<CorrectSyntaxTree> syntaxTreeSet1 = new HashSet<>(treeSimplifiedThis.children);
        HashSet<CorrectSyntaxTree> syntaxTreeSet2 = new HashSet<>(treeSimplifiedThat.children);
        return token.toString().equals(tree.token.toString()) && childrenEquals(syntaxTreeSet1, syntaxTreeSet2);
    }

    private boolean childrenEquals(HashSet<CorrectSyntaxTree> syntaxTreeSet1, HashSet<CorrectSyntaxTree> syntaxTreeSet2){
        if(syntaxTreeSet1.equals(syntaxTreeSet2)){
            return true;
        } else {
            HashSet<Object> syntaxTreeSetModify1 = (HashSet<Object>) syntaxTreeSet1
                    .stream()
                    .map(syntaxTree -> syntaxTree.children == null ? syntaxTree.token.toString() : syntaxTree)
                    .collect(Collectors.toSet());
            HashSet<Object> syntaxTreeSetModify2 = (HashSet<Object>) syntaxTreeSet2
                    .stream()
                    .map(syntaxTree -> syntaxTree.children == null ? syntaxTree.token.toString() : syntaxTree)
                    .collect(Collectors.toSet());
            return equalsTwoSets(syntaxTreeSetModify1, syntaxTreeSetModify2);
        }
    }

    private boolean equalsTwoSets(HashSet<Object> syntaxTreeSet1, HashSet<Object> syntaxTreeSet2){
        return syntaxTreeSet1
                .stream()
                .map(object1 -> syntaxTreeSet2.stream().anyMatch(object2 -> {
                        if(object1 instanceof CorrectSyntaxTree && object2 instanceof CorrectSyntaxTree){
                            return ((CorrectSyntaxTree) object2).equals(((CorrectSyntaxTree) object1));
                        }
                        return object2.toString().equals(object1.toString());
                }))
                .allMatch(object -> object.equals(true));
    }

    private CorrectSyntaxTree simplifyTree(CorrectSyntaxTree correctSyntaxTree){
        final String MUL_TOKEN = "*";
        final String SUM_TOKEN = "+";
        String token = correctSyntaxTree.token.toString();
        CorrectSyntaxTree correctSyntaxTreeNew = correctSyntaxTree;
        if(token.equals(MUL_TOKEN)){
            correctSyntaxTreeNew = simplifyBranch(correctSyntaxTree, MUL_TOKEN);
        } else if(token.equals(SUM_TOKEN)){
            correctSyntaxTreeNew = simplifyBranch(correctSyntaxTree, SUM_TOKEN);
        }
        return correctSyntaxTreeNew;
    }

    private CorrectSyntaxTree simplifyBranch(CorrectSyntaxTree correctSyntaxTree, String token){
        Optional<CorrectSyntaxTree> optionalCorrectSyntaxTree = correctSyntaxTree.children
                .stream()
                .filter(tree -> tree.token.toString().equals(token))
                .findAny();
        if(optionalCorrectSyntaxTree.isPresent()){
            CorrectSyntaxTree correctSyntaxTreeChild = optionalCorrectSyntaxTree.get();
            correctSyntaxTree.children
                    .stream()
                    .filter(tree -> tree != correctSyntaxTreeChild)
                    .forEach(tree -> correctSyntaxTreeChild.children.add(tree));
            return correctSyntaxTreeChild;
        }
        return correctSyntaxTree;
    }

    @Override
    public int hashCode() {
        return token.hashCode() ^ (children != null ? children.hashCode() : 0);
    }

    // -- Iterable methods --

    @Override
    public Iterator<CorrectSyntaxTree> iterator() {
        return new Iterator<CorrectSyntaxTree>() {

            private int index;

            @Override
            public boolean hasNext() {
                return index < count();
            }

            @Override
            public CorrectSyntaxTree next() {
                return child(index++);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    // -- Helper methods --

    private void postfix(final LinkedList<Object> queue) {
        for (final CorrectSyntaxTree child : this) {
            child.postfix(queue);
        }
        queue.add(token());
    }

    private String toString(final String prefix) {
        final StringBuilder sb = new StringBuilder();
        sb.append(prefix).append(" '").append(token).append("'\n");
        final String deeperPrefix = " " + prefix + "-";
        for (int i = 0; i < count(); i++) {
            sb.append(child(i).toString(deeperPrefix));
        }
        return sb.toString();
    }

}
