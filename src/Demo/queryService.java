package Demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class queryService {

    List<String> queryBean(List<Bean> tmps) {
        return tmps.stream().map(tmp -> compareTemps(tmp)).collect(Collectors.toList());
    }

    Map<String, Integer> compareTemps(Bean b1) {
        Map<String, Integer> map = new HashMap<>();
        Bean source = service.queryBean(b1.getName());
        if(!Objects.equals(source.getName(), b1.getName())) {
            map.put(source.getName(), 1);
        } else {
            map.put(source.getName(), 0);
        }

        if(!Objects.equals(source.getAge(), b1.getAge())) {
            map.put(source.getName(), 1);
        } else {
            map.put(source.getName(), 0);
        }
        return map;
    }
}
