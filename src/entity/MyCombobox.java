package entity;

public class MyCombobox {
	Object value;//lưu mã
    Object text;// lưu tên

    public MyCombobox(Object value, Object text) {
        this.value = value;
        this.text = text;
    }
    
    
    @Override
    public String toString(){
    return text.toString();
        
    }
}
