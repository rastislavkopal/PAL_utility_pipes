package utilities;

import graphs.Graph;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GraphReader {

    /*
    -- first line -> F R values

    -- lines specifying a road e.g.
        0 1 4
        first two are farm labels, third is length of the road

    -- Line with single integer H -> number of HUB Farms
    -- Next line contains H integers of labels of Hub Farms

    It holds, 2 ≤ F ≤ 104, 1 ≤ R ≤ 106, 1 ≤ H ≤ F. All road lengths are positive integers less than 1000.
     */
    public Graph readGraphFromFile(String filepath) {

        return null;
    }

}
