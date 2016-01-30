
Chenglong Zou
Daniel Rodriguez Romeu


## Planning and Approximate Reasoning


Practical Exercise 2: Planner


## Scenario:

[Matrix dimension N]
[Boxes = N x N - 1]
[Rooms = N x N] :: clean|dirty && #boxes
[Robot = move|clean]


### precondition:

ini:Robot(x1,y1)


### postcondition:

fin:Rooms
fin:Robot(x2,y2)

### operators:

* clean-office(0)

| Operator        |      Precondition      |  Add           | Delete           |
|-----------------|:----------------------:|---------------:|-----------------:|
| Clean(O)        | R.O, O.dirty, O.empty  | O.clean        | O.dirty          |
| Move(Oi, Oj)    | R.Oi, Oj.adj.Oi        | R.Oj           | R.Oi             |
| Push(b, Oi, Oj) | R.Oi, B.Oi, Oj.adj.Oi  | B.Oj R.Oj E.Oi | E.Oj, B.Oi, R.Oi |

### predicates:

* Robot-location(office):
* Box-location(box, office)
* Dirty(office)
* Clean(office)
* Empty(office)
* Adjacent(office, office)


#### deliverable:

1. Java code (eclipse) :: MUST BE JAVA...
2. xx.bat files [README]
3. documentation.pdf

## Documentation:


1. Introduction to the problem

El plantejament inicial hauria de ser realitzar un diagrama uml
amb tots els components implicats amb la resolucio del problema proposat

S'ha de resoldre aspectes seguents: s'ha de implementar una estructura de dades per emagatzemar els estats del planificador
aquesta estructura tindria un forma d'arbre on el punt l'estat final seria l'arrel del arbre
i l'estat inicial seria una fulla del arbre.

l'objectiu d'aquesta practica seria realitzar:
 com a primera aproximació: implemntar un x-chrismas broadcast, partint desde el node final retrocedint fins el node inicial
 una segona aproximació: podria implicarse, tractar els estats evitant els cicles i bucles. es a dir no continuar la recursivitat si es detecta que l'estat ja existeix
 per aconseguir aixo podem implementar una taula de hash que indexi els estat tractats.
 una tercera fase de millora podria consistir en: TODO, none lineal amb regression.

etc.etc.



2. Analysis of the problem


THINK RECURSIVELY ...

func recurse(state, [next,...])

  if(state(next).apply) # if next possible -> returns next_state
     recurse(state(next).apply)
  else
     ret false  # --> already exist or impossible


todo logic into the apply


3. Planning

we will implement the Non linear Regression Planner, explained this week


4. Implementation design

todo: eclipse auto

diagrama uml?


5. Testing cases and results & Analysis of the results

todo: how to show the output?


6. Instructions to execute the program

Complete the demo section



## Demo:

#### step by step demonstration




## TODO:

 # demo configurations...