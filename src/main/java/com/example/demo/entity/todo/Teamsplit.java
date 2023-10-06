// Generated with g9.

package com.example.demo.entity.todo;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import jakarta.persistence.*;

//@Entity
//@Table(name="teamsplit", indexes={@Index(name="teamsplit_team_split_index_IX", columnList="team_split_index", unique=true), @Index(name="teamsplitCombinedKey", columnList="team_split_index", unique=true)})
public class Teamsplit implements Serializable {

    /** Primary key. */
    protected static final String PK = "id";


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false)
    private int id;
    @Column(name="team_split_index", unique=true, nullable=false)
    private int teamSplitIndex;
    @Column(name="team_number", nullable=false)
    private int teamNumber;

    /** Default constructor. */
    public Teamsplit() {
        super();
    }

    /**
     * Access method for id.
     *
     * @return the current value of id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter method for id.
     *
     * @param aId the new value for id
     */
    public void setId(int aId) {
        id = aId;
    }

    /**
     * Access method for teamSplitIndex.
     *
     * @return the current value of teamSplitIndex
     */
    public int getTeamSplitIndex() {
        return teamSplitIndex;
    }

    /**
     * Setter method for teamSplitIndex.
     *
     * @param aTeamSplitIndex the new value for teamSplitIndex
     */
    public void setTeamSplitIndex(int aTeamSplitIndex) {
        teamSplitIndex = aTeamSplitIndex;
    }

    /**
     * Access method for teamNumber.
     *
     * @return the current value of teamNumber
     */
    public int getTeamNumber() {
        return teamNumber;
    }

    /**
     * Setter method for teamNumber.
     *
     * @param aTeamNumber the new value for teamNumber
     */
    public void setTeamNumber(int aTeamNumber) {
        teamNumber = aTeamNumber;
    }

    /**
     * Compares the key for this instance with another Teamsplit.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Teamsplit and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Teamsplit)) {
            return false;
        }
        Teamsplit that = (Teamsplit) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Teamsplit.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Teamsplit)) return false;
        return this.equalKeys(other) && ((Teamsplit)other).equalKeys(this);
    }

    /**
     * Returns a hash code for this instance.
     *
     * @return Hash code
     */
    @Override
    public int hashCode() {
        int i;
        int result = 17;
        i = getId();
        result = 37*result + i;
        return result;
    }

    /**
     * Returns a debug-friendly String representation of this instance.
     *
     * @return String representation of this instance
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("[Teamsplit |");
        sb.append(" id=").append(getId());
        sb.append("]");
        return sb.toString();
    }

    /**
     * Return all elements of the primary key.
     *
     * @return Map of key names to values
     */
    public Map<String, Object> getPrimaryKey() {
        Map<String, Object> ret = new LinkedHashMap<String, Object>(6);
        ret.put("id", Integer.valueOf(getId()));
        return ret;
    }

}
