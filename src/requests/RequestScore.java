package requests;

import java.math.BigInteger;

public class RequestScore
{
    private BigInteger id;
    private int code;
    private int type;

    public RequestScore(BigInteger id, int code, int type)
    {
        this.id = id;
        this.code = code;
        this.type = type;
    }

    public BigInteger getId()
    {
        return id;
    }

    public void setId(BigInteger id)
    {
        this.id = id;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public int getType()
    {
        return type;
    }

    public void setType(int type)
    {
        this.type = type;
    }
}
