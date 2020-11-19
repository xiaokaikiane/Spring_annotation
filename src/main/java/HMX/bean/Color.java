package HMX.bean;

import HMX.bean.test.cat;
import HMX.bean.test.duck;

public class Color {
    private duck duck;

    public HMX.bean.test.duck getDuck() {
        return duck;
    }

    public void setDuck(HMX.bean.test.duck duck) {
        this.duck = duck;
    }

    @Override
    public String toString() {
        return "Color{" +
                "duck=" + duck +
                '}';
    }
}
