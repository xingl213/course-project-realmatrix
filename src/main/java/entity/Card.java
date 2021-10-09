package entity;

/**
 * Card class contains a term and its definition.
 */

public class Card {

    private final String id;
    private String term;
    private String definition;
    private int proficiency;

    /**
      Construct a Card, giving them the given id,
      term, definition, and proficiency.

      @param id ID of the Card
     * @param term Term that Card contains
     * @param definition Definition of the Term that Card contains
     * @param proficiency  Proficiency of the Term that Card contains
     */
    public Card(String id, String term, String definition, int proficiency){
        this.id = id;
        this.term = term;
        this.definition = definition;
        this.proficiency = proficiency;
    }

    /**
     * Getter for the ID of the Cards as a String
     * @return id as a String
     */
    public String getId(){
        return this.id;
    }

    /**
     * Getter for the term of the Cards as a String
     * @return term as a String
     */
    public String getTerm(){
        return this.term;
    }

    /**
     * Getter for the definition of the Cards as a String
     * @return definition as a String
     */
    public String getDefinition(){
        return this.definition;
    }

    /**
     * Change the term to target term
     * @param target_term the target term we want to change to
     */
    public void changeTerm(String target_term){
        this.term = target_term;
    }

    /**
     * Change the definition to target definition
     * @param target_definition the target definition we want to change to
     */
    public void changeDefinition(String target_definition){
        this.definition = target_definition;
    }

    /**
     * Increase the proficiency with increase value
     * @param increase_value the increase value we want to add to the proficiency
     */
    public void changeProficiency(int increase_value){
        this.proficiency += increase_value;
    }


    /**
     * Represents the current Card by its term and definition
     * @return the name and student number separated by a comma
     */
    public String toString() {
        return this.getTerm() + ": " + this.getDefinition();
    }
}
