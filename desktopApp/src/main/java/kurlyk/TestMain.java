package kurlyk;

import com.google.gson.Gson;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import kurlyk.common.algorithm.graph.ComputerSystem.ComputerSystemElement;
import kurlyk.common.algorithm.graph.ComputerSystem.ComputerSystemElementType;
import kurlyk.common.algorithm.graph.GraphSystem;
import kurlyk.model.Role;
import kurlyk.model.Usver;
import kurlyk.transfer.tasks.ComputerSystemDto;

import java.util.List;

public class TestMain {
    public static void main(String[] args) {
        //        LoginDto loginDto = LoginDto
//                .builder()
//                .login("Montee")
//                .password("Montee")
//                .build();
//        try {
//            if(!communicator.login(loginDto)){
//                System.out.println("Lox");;
//                return; //Не пустить
//            } else {
//                System.out.println("Prohodi");;
//            }
//        } catch (Exception e) {
//            System.out.println("Нет соединения с сервером");
//        }
//        try {
//            System.out.println(communicator.getUsers());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            System.out.println(communicator.getUser(1));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            System.out.println(communicator.getUser());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        GraphSystem graphAnother = new GraphSystem();
//        ComputerSystemElement elementAnother1 = new ComputerSystemElement(ComputerSystemElementType.CPU, 0.1d);
//        ComputerSystemElement elementAnother2 = new ComputerSystemElement(ComputerSystemElementType.RAM, 0d);
//        ComputerSystemElement elementAnother3 = new ComputerSystemElement(ComputerSystemElementType.RAM, 0d);
//        ComputerSystemElement elementAnother4 = new ComputerSystemElement(ComputerSystemElementType.IO, 0d);
//        graphAnother.add(elementAnother1);
//        graphAnother.add(elementAnother2);
//        graphAnother.add(elementAnother3);
//        graphAnother.add(elementAnother4);
//        graphAnother.connect(elementAnother1, elementAnother2);
//        graphAnother.connect(elementAnother1, elementAnother3);
//        graphAnother.connect(elementAnother2, elementAnother4);
//        graphAnother.connect(elementAnother3, elementAnother4);
//
//        DoubleProperty doubleProperty = new SimpleDoubleProperty();
//        Bindings.bindBidirectional(doubleProperty,
//                elementAnother4.getAvailabilityFactor()
//        );
//
//        String content = new Gson().toJson(graphAnother);
//        ComputerSystemDto computerSystemDto = new Gson().fromJson(content, ComputerSystemDto.class);
    }
}
