public class RequestInput
{
    private int id;
    private int type;
    private int code;

    public RequestInput(int id, int type, int code)
    {
        this.id = id;
        this.type = type;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
