package requests;

import java.math.BigInteger;

public class RequestForTransaction
{
    private BigInteger id;
    private int code;
    private int type;
    private double money;
    private BigInteger idRecipient;

    public RequestForTransaction(BigInteger id, int code, int type, double money, BigInteger idRecipient)
    {
        this.id = id;
        this.code = code;
        this.type = type;
        this.money = money;
        this.idRecipient = idRecipient;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public BigInteger getIdRecipient() {
        return idRecipient;
    }

    public void setIdRecipient(BigInteger idRecipient) {
        this.idRecipient = idRecipient;
    }
}
