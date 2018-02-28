package response;

import java.math.BigInteger;

public class Response
{
    private BigInteger id;
    private double money;
    private int type;
    private int codeException;

    public Response(BigInteger id, double money, int type, int codeException)
    {
        this.id = id;
        this.money = money;
        this.type = type;
        this.codeException = codeException;
    }

    public Response()
    {

    }

    public BigInteger getId()
    {
        return id;
    }

    public void setId(BigInteger id)
    {
        this.id = id;
    }

    public double getMoney()
    {
        return money;
    }

    public void setMoney(double money)
    {
        this.money = money;
    }

    public int getType()
    {
        return type;
    }

    public void setType(int type)
    {
        this.type = type;
    }

    public int getCodeException()
    {
        return codeException;
    }

    public void setCodeException(int codeException)
    {
        this.codeException = codeException;
    }
}
