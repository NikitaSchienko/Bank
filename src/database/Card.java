package database;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


public class Card
{
    private Long number;
    private Integer code;
    private Double money;

//    public Card(Long number, Integer code, Double money)
//    {
//        this.number = number;
//        this.code = code;
//        this.money = money;
//    }

    public Long getNumber()
    {
        return number;
    }

    public void setNumber(Long number)
    {
        this.number = number;
    }

    public Integer getCode()
    {
        return code;
    }

    public void setCode(Integer code)
    {
        this.code = code;
    }

    public Double getMoney()
    {
        return money;
    }

    public void setMoney(Double money)
    {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Card{" +
                "number=" + number +
                ", code=" + code +
                ", money=" + money +
                '}';
    }
}
