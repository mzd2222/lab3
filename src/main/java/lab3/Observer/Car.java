package lab3.Observer;

import javax.persistence.*;

@Entity
public class Car extends Observer {

    @Id  //标记主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) //设置自增规则
    private Integer id;
    @Column
    private String name;
    @Column
    private Integer position;
    @Column
    private String type = "car";

    public Car() {
    }

//    public Car(String name, Integer position) {
//        this.name = name;
//        this.position = position;
//    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @Override
    public String getName() {
        return name;
    }

    public Integer getPosition() {
        return position;
    }

    @Override
    public String getStringPosition() {
        if (position == 0) {
            return "西";
        }
        if (position == 1) {
            return "南";
        }
        if (position == 2) {
            return "东";
        } else {
            return "北";
        }
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void updateState() {
        if (observable != null) {
            Integer[] states = (Integer[]) observable.getState();
            state = states[position] == 1;
        }
        System.out.println("position:" + position.toString() + " , name:" + getName() + " , state:" + state.toString());
    }


}
