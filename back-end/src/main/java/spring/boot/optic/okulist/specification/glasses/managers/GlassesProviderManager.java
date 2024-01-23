package spring.boot.optic.okulist.specification.glasses.managers;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import spring.boot.optic.okulist.model.Glasses;
import spring.boot.optic.okulist.specification.glasses.providers.SpecificationProviderGlasses;

@RequiredArgsConstructor
@Component
public class GlassesProviderManager implements SpecificationProviderManagerGlasses<Glasses> {
    private final List<SpecificationProviderGlasses<Glasses>> glassesSpecificationProviderGlasses;
    private final Map<String, SpecificationProviderGlasses<Glasses>> providerCache =
            new ConcurrentHashMap<>();

    @Override
    public SpecificationProviderGlasses<Glasses> getSpecificationProvider(String key) {
        return providerCache.computeIfAbsent(key, this::findProviderByKey);
    }

    private SpecificationProviderGlasses<Glasses> findProviderByKey(String key) {
        return Optional.ofNullable(glassesSpecificationProviderGlasses)
                .orElseThrow(() -> new RuntimeException("glassesSpecificationProviders is null"))
                .stream()
                .filter(p -> p.getKey().equalsIgnoreCase(key))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                        "Can't find correct specifcation provider for key " + key
                ));
    }
}
