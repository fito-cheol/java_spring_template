// Generated with g9.

package com.example.demo.entity.todo;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import jakarta.persistence.*;

//@Entity(name="game")
public class Game implements Serializable {

    /** Primary key. */
    protected static final String PK = "id";


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false)
    private int id;
    @Column(nullable=false)
    private int quarter;
    @Column(name="home_score", nullable=false)
    private int homeScore;
    @Column(name="away_score", nullable=false)
    private int awayScore;
    @Column(nullable=false)
    private int result;
    @Column(name="is_jocker", length=45)
    private String isJocker;

    /** Default constructor. */
    public Game() {
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
     * Access method for quarter.
     *
     * @return the current value of quarter
     */
    public int getQuarter() {
        return quarter;
    }

    /**
     * Setter method for quarter.
     *
     * @param aQuarter the new value for quarter
     */
    public void setQuarter(int aQuarter) {
        quarter = aQuarter;
    }

    /**
     * Access method for homeScore.
     *
     * @return the current value of homeScore
     */
    public int getHomeScore() {
        return homeScore;
    }

    /**
     * Setter method for homeScore.
     *
     * @param aHomeScore the new value for homeScore
     */
    public void setHomeScore(int aHomeScore) {
        homeScore = aHomeScore;
    }

    /**
     * Access method for awayScore.
     *
     * @return the current value of awayScore
     */
    public int getAwayScore() {
        return awayScore;
    }

    /**
     * Setter method for awayScore.
     *
     * @param aAwayScore the new value for awayScore
     */
    public void setAwayScore(int aAwayScore) {
        awayScore = aAwayScore;
    }

    /**
     * Access method for result.
     *
     * @return the current value of result
     */
    public int getResult() {
        return result;
    }

    /**
     * Setter method for result.
     *
     * @param aResult the new value for result
     */
    public void setResult(int aResult) {
        result = aResult;
    }

    /**
     * Access method for isJocker.
     *
     * @return the current value of isJocker
     */
    public String getIsJocker() {
        return isJocker;
    }

    /**
     * Setter method for isJocker.
     *
     * @param aIsJocker the new value for isJocker
     */
    public void setIsJocker(String aIsJocker) {
        isJocker = aIsJocker;
    }

    /**
     * Compares the key for this instance with another Game.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Game and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Game)) {
            return false;
        }
        Game that = (Game) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Game.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Game)) return false;
        return this.equalKeys(other) && ((Game)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[Game |");
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
