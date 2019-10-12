package jaccard;

import java.util.HashSet;
import java.util.Set;

public class Jaccard {

    private static final double ZERO = 0.0;

    public <T> Double index(Set<T> a, Set<T> b){

        if(a == null || a.isEmpty())
            return ZERO;

        if(b == null || b.isEmpty())
            return ZERO;

        Set<T> intersection = new HashSet<T>(a);
        intersection.retainAll(b);

        Set<T> union = new HashSet<T>(a);
        union.addAll(b);

        Double intersectionSize = new Double(intersection.size());
        Double unionSize = new Double(union.size());
        return intersectionSize / unionSize;
    }
}
