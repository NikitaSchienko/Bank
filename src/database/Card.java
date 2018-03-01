package database;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigInteger;
import java.util.Date;
import java.util.Random;


public class Card
{
    private BigInteger id;
    private Integer code;
    private Double money;
    private Random random;


    public Card(BigInteger id, Integer code, Double money)
    {
        this.id = id;
        this.code = code;
        this.money = money;
    }

    public Card()
    {
        random = new Random();

        setId(generateIdCard());
        setCode(generateCode());
        setMoney(generateMoney());
    }

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


    public int generateCode()
    {
        return random.nextInt(8999) + 1000;
    }

    public double generateMoney()
    {
        return random.nextInt(2000);
    }

    public BigInteger generateIdCard()
    {
        Date date = new Date();
        return new BigInteger(String.valueOf(date.getTime()));
    }

}
