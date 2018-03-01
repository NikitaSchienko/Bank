package requests;

import java.math.BigInteger;

public class RequestForAddMoney
{
    private BigInteger id;
    private int type;
    private int code;
    private double money;

    public RequestForAddMoney(BigInteger id, int type, int code, double money)
    {
        this.id = id;
        this.type = type;
        this.code = code;
        this.money = money;
    }

    public BigInteger getId()
    {
        return id;
    }

    public void setId(BigInteger id)
    {
        this.id = id;
    }

    public int getType()
    {
        return type;
    }

    public void setType(int type)
    {
        this.type = type;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public double getMoney()
    {
        return money;
    }

    public void setMoney(double money)
    {
        this.money = money;
    }
}
