package fun.peri.design.create.singleton;

public enum EnumSingleton {

    uniqueInstance;

    private String obj;

    public String getObj() {
        return obj;
    }

    public void setObj(String obj) {
        this.obj = obj;
    }

    public static void main(String[] args) {
        EnumSingleton first = EnumSingleton.uniqueInstance;
        first.setObj("first");
        System.out.println(first.getObj());
        EnumSingleton second = EnumSingleton.uniqueInstance;
        second.setObj("second");
        System.out.println(first.getObj());
        System.out.println(second.getObj());
        EnumSingleton[] enumSingletons = EnumSingleton.class.getEnumConstants();
        for (EnumSingleton enumSingleton : enumSingletons) {
            System.out.println(enumSingleton.getObj());
        }
    }

}
