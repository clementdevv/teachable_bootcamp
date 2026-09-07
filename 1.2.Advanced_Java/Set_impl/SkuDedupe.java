import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class SkuDedupe {
    static List<String> preserveOrder(List<String> skus) { 
        Set<String> uniqueResult = new LinkedHashSet<>(skus); 

        
        return new ArrayList<>(uniqueResult);
    }
}
