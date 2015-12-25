package none.operator;

import none.predicate._Predicate;
import none.state.Node;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by j on 12/12/2015.
 */
public abstract class _Operator implements _OperatorAction{
     Map<String, _Predicate> precondition;
     //int priority;


     public _Operator(Set<_Predicate> ps){
          this.precondition = new HashMap<>();
          for(_Predicate p : ps){
               this.precondition.put(p.toString(), p);
          }
     }

}
