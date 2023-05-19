package at.ac.tuwien.sepm.groupphase.backend.datatype;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Enumeration for different price categories
 * used for seats and sectors of event hall
 */
/* TODO: please correct this file for our pricing conditions!

 */
public enum PriceCategory {
    @JsonProperty("Cheap")
    CHEAP,
    @JsonProperty("Average")
    AVERAGE,
    @JsonProperty("Expensive")
    EXPENSIVE
}
