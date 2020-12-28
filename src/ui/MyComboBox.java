package ui;

public class MyComboBox {
	Object value;//lưu mã
    Object text;// lưu tên

    public MyComboBox(Object value, Object text) {
        this.value = value;
        this.text = text;
    }
    
    
    @Override
    public String toString(){
    return text.toString();
        
    }
}
