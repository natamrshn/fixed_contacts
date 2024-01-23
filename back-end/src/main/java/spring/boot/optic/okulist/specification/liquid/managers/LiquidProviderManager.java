package spring.boot.optic.okulist.specification.liquid.managers;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import spring.boot.optic.okulist.model.Liquid;
import spring.boot.optic.okulist.specification.liquid.providers.SpecificationProviderLiquid;

@RequiredArgsConstructor
@Component
public class LiquidProviderManager implements SpecificationProviderManagerLiquid<Liquid> {
    private final List<SpecificationProviderLiquid<Liquid>> specificationProviderLiquids;
    private final Map<String, SpecificationProviderLiquid<Liquid>> providerCache =
            new ConcurrentHashMap<>();

    @Override
    public SpecificationProviderLiquid<Liquid> getSpecificationProvider(String key) {
        return providerCache.computeIfAbsent(key, this::findProviderByKey);
    }

    private SpecificationProviderLiquid<Liquid> findProviderByKey(String key) {
        return Optional.ofNullable(specificationProviderLiquids)
                .orElseThrow(() -> new RuntimeException("glassesSpecificationProviders is null"))
                .stream()
                .filter(p -> p.getKey().equalsIgnoreCase(key))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                        "Can't find correct specifcation provider for key " + key
                ));
    }
}
