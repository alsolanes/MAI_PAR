package lineal;

import lineal.stack.*;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Planificador lineal con pila de objetivos
 * Created by j on 16/01/2016.
 */
public class Planner {


    /*
    Estructura de datos
     */
    // State estadoActual;
    State estadoInicial;
    State estadoFinal;
    // List<O> planActual;
    //List<List<E>> pilaDeObjetivos; // each E can be
    // op, subgoal, listsubgoal...
    Setup s;


    public Planner(int lvl){
        this.s = new Setup(lvl);
        this.s.load();
        // generate state,
        this.estadoInicial = this.s.getState("InitialState");
        this.estadoFinal = this.s.getState("GoalState");
    }


    /**
     * plan actual
     * crear pila vacia:
     * apilar Estado final
     * @return
     */
    public boolean resolve(){

        // init static adjacent table...

        Broker broker = new Broker(this.s.getConfig(), this.s.setupOfficeAdjacent(), this.estadoInicial); //
        List<O> planActual; // operadores
        planActual = new LinkedList<>(); // lista de operaciones para alcanzar al goalState
        Deque<E> pila; // admite operadores/predicados/listadepredicados[estado]
        pila = new LinkedList<>(); // pila de transici�n
        State estadoActual = this.estadoInicial;   // estado actual

        pila.add(this.estadoFinal); // apilar estado final
        // para elementos de estado final
        for(P p : this.estadoFinal.getPre()){
            pila.add(p); // apilar cada precondicion
        }


        // mientras la pila no este vacia... implica que no se cumplido todas las condiciones.

        while(pila.size() != 0){
            System.out.println(estadoActual.getPre());
            E e = pila.removeLast(); // E es igual la cima de p
            // luego desapilar P...

            // before apply operation, sort the pred list
            // 1st Clean an office
            // 2nd Move a box
            // 3rd Robot location

            System.out.println(e);
            String str = e.getClass().getSimpleName();
            switch (str){
                case "1":
                case "Push":
                case "Move":
                case "CleanOffice":
                    System.out.println("Case 1");
                    /*
                        1: E es un operador
                            EstadoActual := AplicarOperador(EstadoActual, E)
                            A�adir(PlanActual, E)
                     */
                    estadoActual.applyOp((O) e);
                    planActual.add((O) e);
                    break;
                case "2":
                case "PList":
                    System.out.println("Case 2");
                    // añadir a la pila preds
                    List<E> listNoCumple = estadoActual.hasAllPre((PList) e); // apilar a la pila todos los que no se cumplan
                    if(listNoCumple.size() == 0){
                        // noop
                    }else {
                        pila.add(e); // volver a analizarlo
                        pila.addAll(listNoCumple); // analizar sus differencias
                    }
                    /*
                        2: E es una lista de condiciones -- precondiciones de un operador
                            Si no(CumpleCondiciones?(E, EstadoActual))
                            Entonces Apilar(P,E)
                                Para CadaCondicion C de E que no se cumpla en EstadoActual:
                                    Apilar(P,C)
                                FPara
                            Fsi
                     */
                    break;
                case "3":
                case "BoxLocation":
                case "Clean":
                case "Dirty":
                case "Empty":
                case "RobotLocation":

                    if(((P) e).isParcialDefined()) {
                        System.out.println("Case 3");
                        // estadoActual.fillNull((P) e, broker);

                        // descartar aquest estat



                    /*
                        3: E es una condicion parcialmente instanciada
                            Buscar en el estado actual una instancia que satisfaga la condicion
                            Transmitir esta instancia a todos los elementos de la pila P
                     */

                        break;
                    }else{
                        System.out.println("Case 4");
                            // es caso 4
                    }
                case "4":
                        if(estadoActual.hasCond(e)){
                            // operador O que permita obtener P en su lista de añadir
                            // se añade operador con su lista de pre-condiciones.
                            System.out.print("found!");

                        }else{
                            System.out.print("Not found...");
                            // ask Broker for operator and append Plist
                            O op = broker.getOperator((P)e);

                            PList pl = op.getPredList(); // list of preconditions of a certain operator

                            op.setPredList(pl);
                            pl.setOp(op);

                            pila.add(op);
                            // pila.add(pl);

                            // noop descartar el cond, siguiente: continue;
                        }
                    /*
                        4: E es una condicion totalmente instanciada
                            Si no (CumpleCondicion?(E, EstadoActual)) Entonces
                            Buscar Operador O que permita obtener E en su lista de a�adir
                                Apilar(P, O)
                                Apilar(P, precondicion(O)
                                Para Toda Condicion C de Precondiciones(O)
                                    Apilar(P,C)
                                FPara
                            FSi
                     */


                    break;
                case "5":
                case "State":
                	System.out.println("Plan actual:"+planActual);
                    System.out.println("Solution found!");
                    break;

                case "Adjacent":
                    // noop
                    break;
                default:
                    System.out.println(str); // unhandled case
                    break;
            }

            // break;

            /*
            RobotLocation(o1)
            Empty(o2)
            Empty(o1)
            Clean(o4)
            Clean(o3)
            Clean(o2)
            Clean(o1)
            BoxLocation(B,o4)
            BoxLocation(A,o3)
            split into subproblems
             */

        }







        // una vez tenga implementado todo y llega a este punto implica que se ha encontrado una soluci�n.
        return false;
    }







    public static void main(String[] args){
        Planner lpsg = new Planner(3); // Linear Planer with Stack of Goals
        lpsg.resolve();
    }
}
