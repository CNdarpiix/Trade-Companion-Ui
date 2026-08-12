package pages.configuration.models.criterion;


import lombok.Data;

@Data
public class CriterionResponse {

    private Long id ;

    private String name ;

    private Double coefficient ;

    private Long table ;
}
