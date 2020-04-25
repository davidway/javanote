package fanshe;

class Building{}
class House extends  Building{}
class FancyToy{};
class Fancy extends FancyToy{};
public class ClassCasts {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Building b = new House();
        Class<House> houseType = House.class;
        House h = houseType.cast(b);
        h  = (House)b;

            Class<FancyToy> ftClass = FancyToy.class;
            FancyToy fancyToy = ftClass.newInstance();
            Class<? super FancyToy> up = ftClass.getSuperclass();
//            Class<Fancy> up2 = ftClass.getSuperclass();
            Object obj = up.getName();
    }
}
