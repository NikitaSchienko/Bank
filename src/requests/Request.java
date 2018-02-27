package requests;

public class Request
{
    private Long id;
    private int type;
    private int code;

    public Request(Long id, int type, int code)
    {
        this.id = id;
        this.type = type;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
