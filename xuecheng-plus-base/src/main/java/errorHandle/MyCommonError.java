package errorHandle;

import lombok.Data;


public enum MyCommonError {
    UNKOWN_ERROR,OBJECT_ISNULL,ZERO("除数为0");

    private String message;

    MyCommonError(String message){
        this.message=message;
    }

    MyCommonError(){
    }

    String getMessage(){
        return this.message;
    }
}
