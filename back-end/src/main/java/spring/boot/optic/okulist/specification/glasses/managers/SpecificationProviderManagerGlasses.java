package spring.boot.optic.okulist.specification.glasses.managers;

import spring.boot.optic.okulist.model.Glasses;
import spring.boot.optic.okulist.specification.glasses.providers.SpecificationProviderGlasses;

public interface SpecificationProviderManagerGlasses<T> {
    SpecificationProviderGlasses<Glasses> getSpecificationProvider(String key);
}
