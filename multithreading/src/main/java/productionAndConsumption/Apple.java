package productionAndConsumption;

public class Apple {
    private Integer appleId;

    public Integer getAppleId() {
        return appleId;
    }

    public void setAppleId(Integer appleId) {
        this.appleId = appleId;
    }

    @Override
    public String toString() {
        return "Apple [appleId = " + appleId + "]";
    }
}
