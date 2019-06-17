package kurlyk.common.algorithm.graph;

import javafx.util.Pair;
import kurlyk.common.Duet;
import kurlyk.common.algorithm.graph.ComputerSystem.ComputerSystemElement;
import kurlyk.common.algorithm.graph.ComputerSystem.SimpleComputerSystemElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class SimpleGraphSystem {

    @Getter @Setter private Set<SimpleComputerSystemElement> elementSet;
    @Getter @Setter private Set<Duet<SimpleComputerSystemElement, SimpleComputerSystemElement>> connectionSet;

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
                .map(pair -> new Duet<>(new SimpleComputerSystemElement(pair.getKey()), new SimpleComputerSystemElement(pair.getValue())))
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
                            new ComputerSystemElement(pair.getA().getUuid(), pair.getA().getType(), pair.getA().getAvailabilityFactor()),
                            new ComputerSystemElement(pair.getB().getUuid(), pair.getB().getType(), pair.getB().getAvailabilityFactor())
                    )
                ).collect(Collectors.toSet())
        );
        return graphSystem;
    }
}
