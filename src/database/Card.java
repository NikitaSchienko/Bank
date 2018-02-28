package database;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigInteger;


public class Card
{
    private BigInteger id;
    private Integer code;
    private Double money;



//    public Card(BigInteger id, Integer code, Double money) {
//        this.id = id;
//        this.code = code;
//        this.money = money;
//    }


    public BigInteger getId()
    {
        return id;
    }

    public void setId(BigInteger id)
    {
        this.id = id;
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
}
