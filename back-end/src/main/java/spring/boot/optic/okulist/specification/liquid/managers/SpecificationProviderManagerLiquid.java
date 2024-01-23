package spring.boot.optic.okulist.specification.liquid.managers;

import spring.boot.optic.okulist.model.Liquid;
import spring.boot.optic.okulist.specification.liquid.providers.SpecificationProviderLiquid;

public interface SpecificationProviderManagerLiquid<T> {
    SpecificationProviderLiquid<Liquid> getSpecificationProvider(String key);
}
