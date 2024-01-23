package spring.boot.optic.okulist.model.lenses.parameters;

import static java.lang.Double.parseDouble;

import java.util.List;
import java.util.stream.IntStream;

public interface RangeProvider {

    List<Double> getRangeAsList();

    default List<Double> createRange(String min, String max, String step) {
        double doubleMin = parseDouble(min.replaceAll("\\+", ""));
        double doubleMax = parseDouble(max.replaceAll("\\+", ""));
        double doubleStep = parseDouble(step.replaceAll("\\+", ""));

        int stepCount = (int) ((doubleMax - doubleMin) / doubleStep);

        return IntStream.rangeClosed(0, stepCount)
                .mapToDouble(i -> doubleMin + i * doubleStep)
                .boxed()
                .toList();
    }
}
