package pages.configuration.models.criterion;

import lombok.Data;

@Data
public class CreateCriterion {

    private String name ;

    private Double coefficient ;

    private Long tableId ;
}
