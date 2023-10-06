// Generated with g9.

package com.example.demo.entity.todo;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import jakarta.persistence.*;

//@Entity(name="gamereport")
public class Gamereport implements Serializable {

    /** Primary key. */
    protected static final String PK = "id";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false)
    private int id;
    @Column(length=50)
    private String time;
    @Column(name="event_type", nullable=false, length=100)
    private String eventType;
    @Column(name="team_type", nullable=false, length=45)
    private String teamType;

    /** Default constructor. */
    public Gamereport() {
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
     * Access method for time.
     *
     * @return the current value of time
     */
    public String getTime() {
        return time;
    }

    /**
     * Setter method for time.
     *
     * @param aTime the new value for time
     */
    public void setTime(String aTime) {
        time = aTime;
    }

    /**
     * Access method for eventType.
     *
     * @return the current value of eventType
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * Setter method for eventType.
     *
     * @param aEventType the new value for eventType
     */
    public void setEventType(String aEventType) {
        eventType = aEventType;
    }

    /**
     * Access method for teamType.
     *
     * @return the current value of teamType
     */
    public String getTeamType() {
        return teamType;
    }

    /**
     * Setter method for teamType.
     *
     * @param aTeamType the new value for teamType
     */
    public void setTeamType(String aTeamType) {
        teamType = aTeamType;
    }

    /**
     * Compares the key for this instance with another Gamereport.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Gamereport and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Gamereport)) {
            return false;
        }
        Gamereport that = (Gamereport) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Gamereport.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Gamereport)) return false;
        return this.equalKeys(other) && ((Gamereport)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[Gamereport |");
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
