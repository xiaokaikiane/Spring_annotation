package HMX.bean.dao;

import org.springframework.stereotype.Repository;

@Repository
public class bookDao {
    public String lable="1";

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    @Override
    public String toString() {
        return "bookDao{" +
                "lable='" + lable + '\'' +
                '}';
    }
}
