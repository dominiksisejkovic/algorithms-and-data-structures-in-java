package dsisejkovic.structures.heap;

/**
 * Created by dsisejkovic on 13.7.2015..
 */
public class MaxComparingType implements ComparingType<Integer> {
    
    @Override
    public boolean compare(Integer o1, Integer o2) {
        return o1 > o2;
    }
}
