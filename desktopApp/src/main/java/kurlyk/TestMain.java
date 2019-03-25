package kurlyk;

import com.google.gson.Gson;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import kurlyk.graph.ComputerSystem.ComputerSystemElement;
import kurlyk.graph.ComputerSystem.ComputerSystemElementType;
import kurlyk.graph.GraphSystem;
import kurlyk.transfer.tasks.ComputerSystemDto;

public class TestMain {
    public static void main(String[] args) {
        GraphSystem graphAnother = new GraphSystem();
        ComputerSystemElement elementAnother1 = new ComputerSystemElement(ComputerSystemElementType.CPU, 0.1d);
        ComputerSystemElement elementAnother2 = new ComputerSystemElement(ComputerSystemElementType.RAM, 0d);
        ComputerSystemElement elementAnother3 = new ComputerSystemElement(ComputerSystemElementType.RAM, 0d);
        ComputerSystemElement elementAnother4 = new ComputerSystemElement(ComputerSystemElementType.IO, 0d);
        graphAnother.add(elementAnother1);
        graphAnother.add(elementAnother2);
        graphAnother.add(elementAnother3);
        graphAnother.add(elementAnother4);
        graphAnother.connect(elementAnother1, elementAnother2);
        graphAnother.connect(elementAnother1, elementAnother3);
        graphAnother.connect(elementAnother2, elementAnother4);
        graphAnother.connect(elementAnother3, elementAnother4);

        DoubleProperty doubleProperty = new SimpleDoubleProperty();
        Bindings.bindBidirectional(doubleProperty,
                elementAnother4.getAvailabilityFactor()
        );

        String content = new Gson().toJson(graphAnother);
        ComputerSystemDto computerSystemDto = new Gson().fromJson(content, ComputerSystemDto.class);
    }
}
