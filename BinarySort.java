import java.util.*;

public class BinarySort
{
    public static ArrayList<Double> sortDouble(ArrayList<Double> d)
    {
        if(d.size() <= 1) return d;
        ArrayList<Double> d1 = sortDouble(new ArrayList<Double>(d.subList(0,(d.size()/2))));
        ArrayList<Double> d2 = sortDouble(new ArrayList<Double>(d.subList(d.size()/2,d.size())));
        ArrayList<Double> dr = new ArrayList<Double>();
        while(!d1.isEmpty() && !d2.isEmpty()) {
            if (d1.get(0) <= d2.get(0)) dr.add(d1.remove(0));
            else dr.add(d2.remove(0));
        }
        while (!d1.isEmpty()) dr.add(d1.remove(0));
        while (!d2.isEmpty()) dr.add(d2.remove(0));
        return dr;
    }
}
