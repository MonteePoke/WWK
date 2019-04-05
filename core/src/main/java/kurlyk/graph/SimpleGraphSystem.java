package kurlyk.graph;

import javafx.util.Pair;
import kurlyk.graph.ComputerSystem.ComputerSystemElement;
import kurlyk.graph.ComputerSystem.SimpleComputerSystemElement;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class SimpleGraphSystem {

    @Getter @Setter private Set<SimpleComputerSystemElement> elementSet;
    @Getter @Setter private Set<Pair<SimpleComputerSystemElement, SimpleComputerSystemElement>> connectionSet;

    public SimpleGraphSystem() {
        this.elementSet = new HashSet<>();
        this.connectionSet = new HashSet<>();
    }

    public SimpleGraphSystem(GraphSystem graphSystem) {
        elementSet = graphSystem.getElementSet()
                .stream()
                .map(SimpleComputerSystemElement::new)
                .collect(Collectors.toSet());
        connectionSet = graphSystem.getConnectionSet()
                .stream()
                .map(pair -> new Pair<>(new SimpleComputerSystemElement(pair.getKey()), new SimpleComputerSystemElement(pair.getValue())))
                .collect(Collectors.toSet());
    }

    public GraphSystem toGraphSystem () {
        GraphSystem graphSystem = new GraphSystem();
        graphSystem.setElementSet(
                elementSet.stream()
                        .map(simpleComputerSystemElement ->
                                new ComputerSystemElement(
                                        simpleComputerSystemElement.getUuid(),
                                        simpleComputerSystemElement.getType(),
                                        simpleComputerSystemElement.getAvailabilityFactor()
                                )
                        ).collect(Collectors.toSet())
        );
        graphSystem.setConnectionSet(
                connectionSet.stream()
                .map(pair ->
                    new Pair<>(
                            new ComputerSystemElement(pair.getKey().getUuid(), pair.getKey().getType(), pair.getKey().getAvailabilityFactor()),
                            new ComputerSystemElement(pair.getValue().getUuid(), pair.getValue().getType(), pair.getValue().getAvailabilityFactor())
                    )
                ).collect(Collectors.toSet())
        );
        return graphSystem;
    }
}
