package exercise.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import exercise.dto.ProductParamsDTO;
import exercise.model.Product;

// BEGIN
@Component
public class ProductSpecification {

    public Specification<Product> build(ProductParamsDTO productParamsDTO) {
        return withCategoryId(productParamsDTO.getCategoryId())
                .and(withPriceGt(productParamsDTO.getPriceGt()))
                .and(withPriceLt(productParamsDTO.getPriceLt()))
                .and(withRatingGt(productParamsDTO.getRatingGt()))
                .and(withName(productParamsDTO.getTitleCont()));
    }

    private Specification<Product> withCategoryId(Long categoryId) {
        return ((root, query, criteriaBuilder) -> categoryId == null ? criteriaBuilder.conjunction() :
                criteriaBuilder.equal(root.get("category").get("id"), categoryId));
    }

    private Specification<Product> withPriceGt(Integer priceGt) {
        return ((root, query, criteriaBuilder) -> priceGt == null ? criteriaBuilder.conjunction() :
                criteriaBuilder.greaterThan(root.get("price"), priceGt));
    }

    private Specification<Product> withPriceLt(Integer priceLt) {
        return ((root, query, criteriaBuilder) -> priceLt == null ? criteriaBuilder.conjunction() :
                criteriaBuilder.lessThan(root.get("price"), priceLt));
    }

    private Specification<Product> withRatingGt(Double ratingGt) {
        return ((root, query, criteriaBuilder) -> ratingGt == null ? criteriaBuilder.conjunction() :
                criteriaBuilder.greaterThan(root.get("rating"), ratingGt));
    }

    private Specification<Product> withName(String titleCont) {
        return ((root, query, criteriaBuilder) -> titleCont == null ? criteriaBuilder.conjunction() :
                criteriaBuilder.like(criteriaBuilder.lower(root.get("title")),
                        "%" + titleCont.toLowerCase() + "%"));
    }


}
// END
