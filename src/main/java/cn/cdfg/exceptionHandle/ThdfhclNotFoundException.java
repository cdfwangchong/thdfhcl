package cn.cdfg.exceptionHandle;

public class ThdfhclNotFoundException extends RuntimeException{
    private static final long serialVersionUID=1L;

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private int retCode;
    private String msg;

    public ThdfhclNotFoundException(int retCode,String msg) {
        this.retCode = retCode;
        this.msg = msg;
    }

//    public StudentNotFoundException(String message) {
//        super(message);
//    }
}
