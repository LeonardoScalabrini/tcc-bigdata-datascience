package ga;

public class Component {

    public final Double min;
    public final Double max;
    public final Double value;

    public Component(Double min, Double max, Double value) {
        this.min = min;
        this.max = max;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Component component = (Component) o;
        return min.equals(component.min) &&
                max.equals(component.max) &&
                value.equals(component.value);
    }
}
