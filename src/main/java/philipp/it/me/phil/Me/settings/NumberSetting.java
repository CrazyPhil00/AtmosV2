package philipp.it.me.phil.Me.settings;

public class NumberSetting extends Setting{

    public double value,min,max, increment;

    public NumberSetting(String name, double value, double min , double max  , double increment) {
        this.value = value;
        this.min = min;
        this.max = max;
        this.increment = increment;
        this.name = name;
    }


    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        double presicion = 1 / increment;
        this.value = Math.round(Math.max(min , Math.min(max , value)) * presicion) /presicion;
    }

    public void increment(boolean positive) {
        setValue(getValue() + (positive ? 1 :- 1 ) * increment);
    }

    public double getInciment() {
        return increment;
    }

    public void setInciment(double increment) {
        this.increment = increment;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }
}

