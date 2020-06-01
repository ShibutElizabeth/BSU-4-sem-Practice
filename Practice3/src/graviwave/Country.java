package graviwave;

import javax.swing.*;

public class Country {
    private String name;
    private String capital;
    private ImageIcon flag;
    Country(String name, String capital) throws MyException {
        if(name == null)
            throw new MyException("Name of country cannot be null!");
        if(capital == null)
            throw new MyException("Name of capital cannot be null!");
        this.name = name;
        this.capital = capital;
        this.flag = getImage();
    }
    Country(String name) {
        this.name = name;
        this.flag = getImage();
    }
    public int compareTo(Country o)
    {
        return this.name.compareTo(o.getName());
    }
    private ImageIcon getImage() {
        String str = this.name;
        str = str.toLowerCase();
        return new ImageIcon("src\\plain\\flag_" + str + ".png");
    }
    Icon getFlagIcon() {
        return this.flag;
    }
    String getName() { return name; }
    String getCapital() {
        return capital;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCapital(String capital) {
        this.capital = capital;
    }
    @Override
    public String toString() {
        return super.toString();
    }
}


