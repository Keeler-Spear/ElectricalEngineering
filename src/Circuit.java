//Idea: Treat the circuit like a linked list. Each element points to a new one and if there are multiple references, the components must be in parallel

import java.util.ArrayList;

public class Circuit {

    private final int IDBASE = 0;
    private int componentID = 0;

    private class CircuitComponent { //A fancy node used to model a circuit

        private int id = componentID++;

        private ArrayList<CircuitComponent> nextComponents = new ArrayList<>();

        private int getID() {
            return id;
        }


        private CircuitComponent getNext(int i) {
            return nextComponents.get(i);
        }

        protected void addNext(CircuitComponent next) {
            nextComponents.add(next);
        }

        private int connectionCount () {
            return nextComponents.size();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }

            if (obj.getClass() != this.getClass()) {
                return false;
            }

            if (id == ((CircuitComponent) obj).getID()) {
                return true;
            }
            else {
                return false;
            }
        }
    }

    private class Resistor extends CircuitComponent {
        private double resistance;

        public Resistor(double resistance) {
            this.resistance = resistance;
        }

        private double getResistance () {
            return resistance;
        }

        @Override
        public String toString() {
            String s = "R"; //ToDo: Use standard symbol
            return s;
        }
    }

    private class Capacitor extends CircuitComponent {
        private double capacitance;

        public Capacitor(double capacitance) {
            this.capacitance = capacitance;
        }

        private double getCapacitance () {
            return capacitance;
        }

        @Override
        public String toString() {
            String s = "C"; //ToDo: Use standard symbol
            return s;
        }
    }

    private class Inductor extends CircuitComponent{
        private double inductance;

        public Inductor(double inductance) {
            this.inductance = inductance;
        }

        private double getInductance () {
            return inductance;
        }

        @Override
        public String toString() {
            String s = "L"; //ToDo: Use standard symbol
            return s;
        }
    }

    private class PowerSource extends CircuitComponent{
        private double voltage;

        public PowerSource(double voltage) {
            this.voltage = voltage;
        }

        private double getVoltage () {
            return voltage;
        }

        @Override
        public String toString() {
            String s = "E"; //ToDo: Use standard symbol
            return s;
        }
    }

    public class Wire extends CircuitComponent{
        @Override
        public String toString() {
            String s = "|"; //ToDo: Use standard symbol
            return s;
        }
    }







    private final int BASE_SIZE = 5;
    private CircuitComponent head;
    private int height;
    private int width;

    public Circuit () {
        buildCircuit();
        //ToDo: Define height and width of the circuit for print purposes
    }

    private void buildCircuit() {
        //Todo
        PowerSource emf = new PowerSource(4);
        head = emf;
        Resistor r1 = new Resistor(1);
        Resistor r2 = new Resistor(1);
        Resistor r3 = new Resistor(1);

        Capacitor c1 = new Capacitor(1);
        Capacitor c2 = new Capacitor(1);

        emf.addNext(r1);
        emf.addNext(r2);
        r1.addNext(r3);
        r2.addNext(r3);
        r2.addNext(c1);
        r3.addNext(emf);
        c1.addNext(emf);
        height = 3;
        width = 2;
    }

    public void addComponent() {

    }


    private boolean visted(CircuitComponent comp, ArrayList<CircuitComponent> visitedComponents) {
        for (int i = 0; i < visitedComponents.size(); i++) {
            if (visitedComponents.get(i).equals(comp)) {
                return true;
            }
        }
        return false;
    }

    //ToDo: Current plan is to model this off of the BST recursive tree
    private String toStringRec(CircuitComponent current, ArrayList<CircuitComponent> visitedComponents) {
        String s = current + "\n";
        if (visted(current, visitedComponents)) { //Base case
            return s;
        }
        for (int i = 0; i < current.connectionCount(); i++) {
            if (!visted(current.getNext(i), visitedComponents)) {
                visitedComponents.add(current.getNext(i));
                s += toStringRec(current.getNext(i), visitedComponents);
            }
        }

        visitedComponents.add(current);
//        for (int i = 0; i < height; i++) {
//            if (!visted(current, visitedComponents)) {
//                System.out.print(current);
//                visitedComponents.add(current);
//            }
//
//        }

        return s;
    }

    private ArrayList<CircuitComponent> addChildren(CircuitComponent current, ArrayList<CircuitComponent> children) {
        for (int i = 0; i < children.size(); i++) {
            children.add(current.getNext(i));
        }
        return children;
    }

//    @Override
//    public String toString() {
//        String s = "";
//        ArrayList<CircuitComponent> childComponents = new ArrayList<>();
//        CircuitComponent<CircuitComponent> current = head;
//        System.out.println(head);
//        for (int i = 0; i < height; i++) {
//            childComponents.clear();
//            for (int j = 0; j < current.connectionCount(); j++) { //Adds all of the current node's children to the list
//                childComponents.add();
//            }
//
//        }
//
//        return s.trim();
//
//    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        //Todo: clone
        Circuit c = new Circuit();
        return c;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        //Todo: Comparison of elements

        return true;
    }

}
