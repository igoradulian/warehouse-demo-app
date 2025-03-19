package com.warehouse.model.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

/**
 * @author igoradulyan on 12/13/24
 * @project IntelliJ IDEA
 */
@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {

    @NonNull
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Name must contain only letters")
    private String name;
    private String productId;

    @NonNull
    @Pattern(regexp = "^[ a-zA-Z0-9]*$", message = "Category must contain only letters and numbers")
    private String description;

    @NonNull
    @Pattern(regexp = "^[.0-9]*$", message = "Price must contain only numbers")
    private String price;

    @NonNull
    @Pattern(regexp = "^[0-9]*$", message = "Quantity must contain only numbers")
    private String quantity;

    private boolean isActive;

}
