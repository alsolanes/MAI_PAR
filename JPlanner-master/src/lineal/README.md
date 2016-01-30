
    /*
    2-2
    Planificador lineal con pila de objetivos: [http://moodle.urv.cat/moodle/mod/forum/discuss.php?d=300031]
    Strips: Notation
    * precondiciones
    * añadir
    * borrar
    operadores parametrizados
    no existe tabla operadores/diferencias
    */



    /*
    2-3
    -- predicados --
    RobotLocation(o)
    BoxLocation(o)
    Dirty(o)
    Clean(o)
    Empty(o)
    Adjacent(o1, o2)

    -- operadores:parametrizados --
    CleanOffice(o)
    Move(o1, o2)
    Push(b, o1, o2)
     */


    /*
    2-4 : algoritmo lineal con pila de objetivos

    -- estructura de datos --
    PlanActual
    EstadoActual<State> : estado inicial.
    PilaDeObjetivos<Ops, SubObjetivos, ListaSubObjetivos>


    -- algoritmo --

    EstadoActual := EstadoInicial
    PlanActual := []

    CrearPilaVacia(P)
    Apilar(P, EstadoFinal)
    Para TodoElemento E de EstadoFinal
    Apilar(P, E)
    FPara

    Mientras No (PilaVacia?(P)) Hacer
    E := Cima(P); Desapilar(P)
    FMientras

    -- tratamiento individual e independiente de cada uno de los sub-objetivos(E)
    -- casos: 1, 2, 3, 4
    1: E es un operador
        EstadoActual := AplicarOperador(EstadoActual, E)
        Añadir(PlanActual, E)

    2: E es una lista de condiciones
        Si no(CumpleCondiciones?(E, EstadoActual))
        Entonces Apilar(P,E)
            Para CadaCondicion C de E que no se cumpla en EstadoActual:
                Apilar(P,C)
            FPara
        Fsi

    3: E es una condicion parcialmente instanciada
        Buscar en el estado actual una instancia que satisfaga la condicion
        Transmitir esta instancia a todos los elementos de la pila P

    4: E es una condicion totalmente instanciada
        Si no (CumpleCondicion?(E, EstadoActual)) Entonces
        Buscar Operador O que permita obtener E en su lista de añadir
            Apilar(P, O)
            Apilar(P, precondicion(O)
            Para Toda Condicion C de Precondiciones(O)
                Apilar(P,C)
            FPara
        FSi


     */
